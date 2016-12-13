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

    public boolean isInternalLink() {
        return type == INTERNAL;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        return url.equals(link.url);
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
