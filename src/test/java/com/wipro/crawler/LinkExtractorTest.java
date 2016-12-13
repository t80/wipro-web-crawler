package com.wipro.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static com.wipro.crawler.TestUtils.resourceFileToString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LinkExtractorTest {

    private Document document;

    @Before
    public void setUp() throws Exception {
        document = Jsoup.parse(resourceFileToString("wipro.html"));
        document.setBaseUri("http://www.wipro.com");
    }

    @Test
    public void returnsAllLinksFromPage() {
        LinkExtractor linkExtractor = new LinkExtractor();
        Collection<Link> links = linkExtractor.getLinksFrom(document);

        assertThat(links.size(), is(13));
    }
}