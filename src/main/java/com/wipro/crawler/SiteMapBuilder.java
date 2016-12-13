package com.wipro.crawler;

import org.jsoup.nodes.Document;

public class SiteMapBuilder {
    private final DocumentFactory documentFactory;
    private final LinkExtractor linkExtractor;

    public SiteMapBuilder(DocumentFactory documentFactory, LinkExtractor linkExtractor) {
        this.documentFactory = documentFactory;
        this.linkExtractor = linkExtractor;
    }

    public SiteMap siteMapFor(String rootUrl) {
        Page rootPage = new Page(rootUrl);
        return findLinks(rootPage, new SiteMap());
    }

    private SiteMap findLinks(Page page, SiteMap siteMap) {
        siteMap.addPage(page);
        Document document = documentFactory.documentFor(page.getUrl());
        if(null != document) {
            for (Link link : linkExtractor.getLinksFrom(document)) {
                page.addLink(link);
                if (link.isInternalLink() && !siteMap.hasPageFor(link)) {
                    findLinks(new Page(link.getUrl()), siteMap);
                }
            }
        }
        return siteMap;
    }
}