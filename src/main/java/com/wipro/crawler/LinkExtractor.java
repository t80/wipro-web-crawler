package com.wipro.crawler;

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
        links.addAll(anchors(doc));
        links.addAll(images(doc));
        links.addAll(scripts(doc));
        links.addAll(resourceLinks(doc));

        return links;
    }

    private Collection<Link> anchors(Document doc) {
        return doc.getElementsByTag("a").stream()
                .map((e) -> e.attr("href"))
                .map((url) -> linkToSameDomain(doc.baseUri(), url) ? Link.from(url, INTERNAL) : Link.from(url, EXTERNAL))
                .collect(Collectors.toList());
    }

    private Collection<Link> images(Document doc) {
        return doc.getElementsByTag("img").stream()
                .map((e) -> e.attr("src"))
                .map((url) -> Link.from(url, RESOURCE))
                .collect(Collectors.toList());
    }

    private Collection<Link> scripts(Document doc) {
        return doc.getElementsByTag("script").stream()
                .map((e) -> e.attr("src"))
                .map((url) -> Link.from(url, RESOURCE))
                .collect(Collectors.toList());
    }

    private Collection<Link> resourceLinks(Document doc) {
        return doc.getElementsByTag("link").stream()
                .map((e) -> e.attr("href"))
                .map((url) -> Link.from(url, RESOURCE))
                .collect(Collectors.toList());
    }
}
