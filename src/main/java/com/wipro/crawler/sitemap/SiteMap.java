package com.wipro.crawler.sitemap;

import java.util.HashMap;
import java.util.Map;

public class SiteMap {
    private Map<String, Page> pages = new HashMap<>();

    public void addPage(Page page) {
        pages.put(page.getUrl(), page);
    }

    public boolean hasPageFor(Link link) {
        return pages.containsKey(link.getUrl());
    }
}
