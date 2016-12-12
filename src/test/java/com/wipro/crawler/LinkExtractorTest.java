package com.wipro.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkExtractorTest {

    private final Connection con = mock(Connection.class);

    @Before
    public void setUp() throws Exception {
        Document document = Jsoup.parse(resourceFileToString("wipro.html"));
        document.setBaseUri("http://www.wipro.com");
        when(con.get()).thenReturn(document);
    }

    @Test
    public void returnsAllLinksFromPage() {
        LinkExtractor linkExtractor = new LinkExtractor(con);
        Collection<Link> links = linkExtractor.getLinks();

        assertThat(links.size(), is(7));
    }

    private String resourceFileToString(String filename) {
        URL resource = getClass().getClassLoader().getResource(filename);
        String string = null;
        try {
            string = new String(readAllBytes(get(resource.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }

}