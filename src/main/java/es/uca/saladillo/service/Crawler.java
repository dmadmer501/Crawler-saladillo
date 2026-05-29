package es.uca.saladillo.service;

import es.uca.saladillo.config.ConfigManager;
import es.uca.saladillo.model.PageMetrics;
import es.uca.saladillo.util.LoggerUtil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Queue;

public class Crawler {
    private final ConfigManager config;
    private final PageFetcher fetcher;
    private final LinkExtractor extractor;
    private final ResultExporter exporter;

    private final Set<String> visitedUrls;
    private final Queue<String> urlQueue;

    public Crawler(ConfigManager config) {
        this.config = config;
        this.fetcher = new PageFetcher();
        this.extractor = new LinkExtractor();
        this.exporter = new ResultExporter(config.getOutputFile());

        this.visitedUrls = new HashSet<>();
        this.urlQueue = new LinkedList<>();
    }

    public void start() {
        exporter.startExport();

        String seedUrl = config.getSeedUrl();
        urlQueue.add(seedUrl);

        while(!urlQueue.isEmpty() && visitedUrls.size() < config.getMaxPages()) {
            String currentUrl = urlQueue.poll();

            if (visitedUrls.contains(currentUrl)) {
                continue;
            }

            LoggerUtil.logInfo("Analyzing page " + (visitedUrls.size() + 1) + ": " + currentUrl);

            visitedUrls.add(currentUrl);

            long startTime = System.currentTimeMillis();
            org.jsoup.nodes.Document doc = fetcher.fetchPage(currentUrl);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            if (doc == null) {
                continue;
            }

            java.util.List<String> discoveredLinks = extractor.extractLinks(doc);
            String title = doc.title();
            int linkCount = discoveredLinks.size();

            PageMetrics metrics = new PageMetrics(currentUrl, title, linkCount, executionTime);
            exporter.exportResult(metrics);

            for (String link : discoveredLinks) {
                if (!visitedUrls.contains(link)) {
                    urlQueue.add(link);
                }
            }
        }

        exporter.endExport();
        LoggerUtil.logInfo("Crawling finished succesfully. Total pages visited: " + visitedUrls.size());
    }
}
