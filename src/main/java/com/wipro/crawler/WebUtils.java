package com.wipro.crawler;

import java.net.URI;

public class WebUtils {

    public static boolean linkToSameDomain(String domainLink, String url) {
        URI uri = URI.create(domainLink);
        return url.startsWith(uri.getScheme() + "://" + uri.getHost());
    }
}
