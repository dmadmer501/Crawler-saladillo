package es.uca.saladillo.service;

import es.uca.saladillo.util.LoggerUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class PageFetcher {
    public Document fetchPage(String url) {
        try {
            return Jsoup.connect(url).get();

        } catch (IOException e) {
            LoggerUtil.logError("Failed to load the page: ", e);
            return null;
        }
    }
}
