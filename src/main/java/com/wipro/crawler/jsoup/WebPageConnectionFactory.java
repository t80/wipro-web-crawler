package com.wipro.crawler.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class WebPageConnectionFactory {
    public Connection connectionFor(String url) {
        return Jsoup.connect(url).timeout(30000).userAgent("Mozilla/17.0");
    }
}
