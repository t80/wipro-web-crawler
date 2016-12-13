package com.wipro.crawler.sitemap;

import java.util.HashMap;
import java.util.Map;

public class SiteMap {
    private Map<String, Page> pages = new HashMap<>();
    private final String domain;

    public SiteMap(String domain) {
        this.domain = domain;
    }

    public void addPage(Page page) {
        pages.put(page.getUrl(), page);
    }

    public boolean hasPageFor(Link link) {
        return pages.containsKey(link.getUrl());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Siptmap for: " + domain + "\n");
        for (Page p : pages.values()) {
            sb.append("\t"+ p + "\n");
        }

        return sb.toString();
    }
}
