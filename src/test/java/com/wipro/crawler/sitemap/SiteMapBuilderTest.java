package com.wipro.crawler.sitemap;

import com.wipro.crawler.jsoup.DocumentFactory;
import com.wipro.crawler.jsoup.LinkExtractor;
import com.wipro.crawler.sitemap.Link;
import com.wipro.crawler.sitemap.SiteMapBuilder;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static com.wipro.crawler.sitemap.Link.from;
import static com.wipro.crawler.sitemap.LinkType.EXTERNAL;
import static com.wipro.crawler.sitemap.LinkType.INTERNAL;
import static com.wipro.crawler.sitemap.LinkType.RESOURCE;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SiteMapBuilderTest {
    private final DocumentFactory documentFactory = mock(DocumentFactory.class);
    private final Document mockHomePage = mock(Document.class);
    private final Document mockChildPage = mock(Document.class);
    private final LinkExtractor linkExtractor = mock(LinkExtractor.class);
    private SiteMapBuilder siteMapBuilder;

    @Before
    public void setUp() throws Exception {
        siteMapBuilder = new SiteMapBuilder(documentFactory, linkExtractor);
    }

    @Test
    public void returnsSiteMapForDomainWithCyclicLinks() {
        when(documentFactory.documentFor("home-page")).thenReturn(mockHomePage);
        when(linkExtractor.getLinksFrom(mockHomePage)).thenReturn(homePageLinks());
        when(mockHomePage.baseUri()).thenReturn("www.abc.com");

        when(documentFactory.documentFor("child-page")).thenReturn(mockChildPage);
        when(linkExtractor.getLinksFrom(mockChildPage)).thenReturn(childPagesLinks());
        when(mockChildPage.baseUri()).thenReturn("www.abc.com");

        SiteMap siteMap = siteMapBuilder.siteMapFor("home-page");

        assertThat(siteMap, isA(SiteMap.class));
    }

    private Collection<Link> childPagesLinks() {
        Collection<Link> links = new ArrayList<>(5);
        links.add(from("home-page", INTERNAL));
        links.add(from("a", EXTERNAL));
        links.add(from("b", RESOURCE));
        return links;
    }

    private Collection<Link> homePageLinks() {
        Collection<Link> links = new ArrayList<>(5);
        links.add(from("child-page", INTERNAL));
        links.add(from("b", EXTERNAL));
        links.add(from("c", EXTERNAL));
        links.add(from("d", RESOURCE));
        links.add(from("e", RESOURCE));
        return links;
    }
}
