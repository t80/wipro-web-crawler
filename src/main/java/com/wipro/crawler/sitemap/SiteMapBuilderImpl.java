package com.wipro.crawler.sitemap;

import com.wipro.crawler.jsoup.DocumentFactory;
import com.wipro.crawler.jsoup.LinkExtractor;
import org.jsoup.nodes.Document;

public class SiteMapBuilderImpl implements SiteMapBuilder {
    private final DocumentFactory documentFactory;
    private final LinkExtractor linkExtractor;

    public SiteMapBuilderImpl(DocumentFactory documentFactory, LinkExtractor linkExtractor) {
        this.documentFactory = documentFactory;
        this.linkExtractor = linkExtractor;
    }

    @Override
    public SiteMap siteMapFor(String rootUrl) {
        Page rootPage = new Page(rootUrl);
        return findLinks(rootPage, new SiteMap(rootUrl));
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