package com.wipro.crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;

public class DocumentFactory {
    private final WebPageConnectionFactory pageConFactory;

    public DocumentFactory(WebPageConnectionFactory pageConFactory) {
        this.pageConFactory = pageConFactory;
    }

    public Document documentFor(String url) {
        try {
            return pageConFactory.connectionFor(url).get();
        } catch (IOException e) {
            return null;
        }
    }
}
