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
        return findLinks_dfs(rootPage, new SiteMap());
    }

    private SiteMap findLinks_dfs(Page page, SiteMap siteMap) {
        siteMap.addPage(page);
        Document document = documentFactory.documentFor(page.getUrl());
        if(null != document) {
            for (Link link : linkExtractor.getLinksFrom(document)) {
                page.addLink(link);
                if (link.pageWithSameDomain() && !siteMap.hasPageFor(link)) {
                    findLinks_dfs(new Page(link.getUrl()), siteMap);
                }
            }
        }
        return siteMap;
    }
}