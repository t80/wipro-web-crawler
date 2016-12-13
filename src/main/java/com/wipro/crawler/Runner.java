package com.wipro.crawler;

import java.io.Console;

public class Runner {

    public static void main(String[] args) {
        SiteMapBuilder siteMapBuilder = new SiteMapBuilder(
                new DocumentFactory(new WebPageConnectionFactory()),
                new LinkExtractor());

        Console console  = System.console();
        if (console != null) {
            String url = "";
            while (!url.equals("exit")) {
                url = console.readLine("Enter url: ");
                System.out.println(siteMapBuilder.siteMapFor(url));
            }
        }
    }
}
