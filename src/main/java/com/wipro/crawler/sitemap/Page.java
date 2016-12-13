package com.wipro.crawler.sitemap;

import java.util.ArrayList;
import java.util.List;

class Page {
    private final String url;
    private List<Link> links = new ArrayList<>();

    Page(String url) {
        this.url = url;
    }

    void addLink(Link l) {
        links.add(l);
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Page: " + url + "\n");
        for (Link l : links) {
            sb.append("\t\t"+ l + "\n");
        }

        return sb.toString();
    }
}
