package com.wipro.crawler.sitemap;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private final String url;
    private List<Link> links = new ArrayList<>();

    public Page(String url) {
        this.url = url;
    }

    public void addLink(Link l) {
        links.add(l);
    }

    public String getUrl() {
        return url;
    }
}
