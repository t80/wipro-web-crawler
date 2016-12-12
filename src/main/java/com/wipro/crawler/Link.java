package com.wipro.crawler;

import static com.wipro.crawler.LinkType.*;

public class Link {
    public static Link from(String url, LinkType type) {
        return new Link(url, type);
    }

    private final String url;
    private LinkType type;

    public Link(String url, LinkType type) {
        this.url = url;
        this.type = type;
    }

    public boolean pageWithSameDomain() {
        return type == INTERNAL;
    }

    public String getUrl() {
        return url;
    }
}
