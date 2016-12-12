package com.wipro.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.wipro.crawler.LinkType.*;
import static com.wipro.crawler.WebUtils.*;

public class LinkExtractor {
    public static LinkExtractor forUrl(String url) {
        return new LinkExtractor(Jsoup.connect(url));
    }

    private final Connection connection;

    public LinkExtractor(Connection connection) {
        this.connection = connection;
    }

    public Collection<Link> getLinks() {
        Document doc = null;
        try {
            doc = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Link> links = new ArrayList<>();
        links.addAll(anchors(doc));

        return links;
    }

    private Collection<Link> anchors(Document doc) {
        return doc.getElementsByTag("a").stream()
                .map((e) -> e.attr("href"))
                .map((url) -> linkToSameDomain(doc.baseUri(), url) ? Link.from(url, INTERNAL) : Link.from(url, EXTERNAL))
                .collect(Collectors.toList());
    }
}
