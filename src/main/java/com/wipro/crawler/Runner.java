package com.wipro.crawler;

import com.wipro.crawler.jsoup.DocumentFactory;
import com.wipro.crawler.jsoup.LinkExtractor;
import com.wipro.crawler.jsoup.WebPageConnectionFactory;
import com.wipro.crawler.sitemap.SiteMapBuilder;
import com.wipro.crawler.sitemap.SiteMapBuilderImpl;

import java.io.Console;

public class Runner {

    public static void main(String[] args) {
        SiteMapBuilder siteMapBuilder = new SiteMapBuilderImpl(
                new DocumentFactory(new WebPageConnectionFactory()),
                new LinkExtractor());

        Console console = System.console();
        if (null != console) {
            String url = console.readLine("Enter url including protocol: ");
            System.out.println(siteMapBuilder.siteMapFor(url));
        }
    }
}
