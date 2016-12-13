package com.wipro.crawler;

public class WebUtils {

    public static boolean linkToSameDomain(String domain, String url) {
        return url.startsWith(domain);
    }
}
