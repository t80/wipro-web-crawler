package com.wipro.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.wipro.crawler.LinkType.EXTERNAL;
import static com.wipro.crawler.LinkType.INTERNAL;
import static com.wipro.crawler.LinkType.RESOURCE;
import static com.wipro.crawler.WebUtils.linkToSameDomain;

public class LinkExtractor {

    public Collection<Link> getLinksFrom(Document doc) {
        List<Link> links = new ArrayList<>();
        links.addAll(anchorsFrom(doc));
        links.addAll(imagesFrom(doc));
        links.addAll(scriptsFrom(doc));
        links.addAll(resourceLinksFrom(doc));

        return links;
    }

    private Collection<Link> anchorsFrom(Document doc) {
        return doc.getElementsByTag("a").stream()
                .map((e) -> e.absUrl("href"))
                .map((url) -> linkToSameDomain(doc.baseUri(), url) ? Link.from(url, INTERNAL) : Link.from(url, EXTERNAL))
                .collect(Collectors.toList());
    }

    private Collection<Link> imagesFrom(Document doc) {
        return doc.getElementsByTag("img").stream()
                .map((e) -> e.absUrl("src"))
                .map((url) -> Link.from(url, RESOURCE))
                .collect(Collectors.toList());
    }

    private Collection<Link> scriptsFrom(Document doc) {
        return doc.getElementsByTag("script").stream()
                .map((e) -> e.absUrl("src"))
                .map((url) -> Link.from(url, RESOURCE))
                .collect(Collectors.toList());
    }

    private Collection<Link> resourceLinksFrom(Document doc) {
        return doc.getElementsByTag("link").stream()
                .map((e) -> e.absUrl("href"))
                .map((url) -> Link.from(url, RESOURCE))
                .collect(Collectors.toList());
    }
}
