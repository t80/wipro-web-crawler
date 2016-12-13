package com.wipro.crawler.sitemap;

import static com.wipro.crawler.sitemap.LinkType.INTERNAL;

public class Link {
    public static Link from(String url, LinkType type) {
        return new Link(url, type);
    }

    private final String url;
    private LinkType type;

    private Link(String url, LinkType type) {
        this.url = url;
        this.type = type;
    }

    public boolean isInternalLink() {
        return type == INTERNAL;
    }

    public LinkType getType() {
        return type;
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

    @Override
    public String toString() {
        return type + " link for url: " + url;
    }
}
