package com.wipro.crawler.jsoup;

import com.wipro.crawler.WebUtils;
import com.wipro.crawler.sitemap.Link;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.wipro.crawler.sitemap.LinkType.EXTERNAL;
import static com.wipro.crawler.sitemap.LinkType.INTERNAL;
import static com.wipro.crawler.sitemap.LinkType.RESOURCE;
import static java.util.stream.Collectors.toList;

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
                .filter((e) -> !e.attr("href").isEmpty())
                .map((e) -> e.absUrl("href"))
                .map((url) -> WebUtils.linkToSameDomain(doc.baseUri(), url) ? Link.from(url, INTERNAL) : Link.from(url, EXTERNAL))
                .collect(toList());
    }

    private Collection<Link> imagesFrom(Document doc) {
        return doc.getElementsByTag("img").stream()
                .filter((e) -> !e.attr("src").isEmpty())
                .map((e) -> e.absUrl("src"))
                .map((url) -> Link.from(url, RESOURCE))
                .collect(toList());
    }

    private Collection<Link> scriptsFrom(Document doc) {
        return doc.getElementsByTag("script").stream()
                .filter((e) -> !e.attr("src").isEmpty())
                .map((e) -> e.absUrl("src"))
                .map((url) -> Link.from(url, RESOURCE))
                .collect(toList());
    }

    private Collection<Link> resourceLinksFrom(Document doc) {
        return doc.getElementsByTag("link").stream()
                .filter((e) -> !e.attr("href").isEmpty())
                .map((e) -> e.absUrl("href"))
                .map((url) -> Link.from(url, RESOURCE))
                .collect(toList());
    }
}
