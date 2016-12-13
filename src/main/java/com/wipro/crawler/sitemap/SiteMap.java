package com.wipro.crawler.sitemap;

import java.util.HashMap;
import java.util.Map;

class SiteMap {
    private Map<String, Page> pages = new HashMap<>();
    private final String domain;

    SiteMap(String domain) {
        this.domain = domain;
    }

    void addPage(Page page) {
        pages.put(page.getUrl(), page);
    }

    boolean hasPageFor(Link link) {
        return pages.containsKey(link.getUrl());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Siptmap for: ").append(domain).append("\n");
        for (Page p : pages.values()) {
            sb.append("\t").append(p).append("\n");
        }

        return sb.toString();
    }
}
