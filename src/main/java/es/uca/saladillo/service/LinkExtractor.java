package es.uca.saladillo.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class LinkExtractor {

    public List<String> extractLinks(Document doc) {
        List<String> links = new ArrayList<>();

        if (doc == null) {
            return links;
        }

        Elements elements = doc.select("a[href]");

        for (Element element : elements) {
            String url = element.absUrl("href");

            if (!url.isEmpty()) {
                links.add(url);
            }
        }
        return links;
    }
}
