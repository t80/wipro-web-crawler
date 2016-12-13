package com.wipro.crawler.jsoup;

import com.wipro.crawler.sitemap.Link;
import com.wipro.crawler.sitemap.LinkType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static com.wipro.crawler.TestUtils.MOCK_BASE_URI;
import static com.wipro.crawler.TestUtils.MOCK_EXTERNAL_URL;
import static com.wipro.crawler.TestUtils.externalAnchorFor;
import static com.wipro.crawler.TestUtils.imageWithSrc;
import static com.wipro.crawler.TestUtils.internalAnchorFor;
import static com.wipro.crawler.TestUtils.linkWithHref;
import static com.wipro.crawler.TestUtils.scriptWithSrc;
import static com.wipro.crawler.TestUtils.singleAnchorWithNoHref;
import static com.wipro.crawler.TestUtils.singleImageWithNoSrc;
import static com.wipro.crawler.TestUtils.singleLinkWithNoHref;
import static com.wipro.crawler.TestUtils.singleScriptWithNoSrc;
import static com.wipro.crawler.sitemap.LinkType.EXTERNAL;
import static com.wipro.crawler.sitemap.LinkType.INTERNAL;
import static com.wipro.crawler.sitemap.LinkType.RESOURCE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkExtractorTest {
    private final LinkExtractor linkExtractor = new LinkExtractor();
    private final Document document = mock(Document.class);

    @Before
    public void setUp() throws Exception {
        when(document.baseUri()).thenReturn(MOCK_BASE_URI);
        when(document.getElementsByTag(anyString())).thenReturn(new Elements());
    }

    @Test
    public void extractsAnchors() {
        when(document.getElementsByTag("a")).thenReturn(new Elements(
                internalAnchorFor("/index"),
                internalAnchorFor(MOCK_BASE_URI + "/contact"),
                externalAnchorFor(MOCK_EXTERNAL_URL)
        ));

        Collection<Link> links = linkExtractor.getLinksFrom(document);

        assertThat(links.size(), is(3));
        assertThat(links, containsLinkWithUrl(MOCK_BASE_URI + "/index", INTERNAL));
        assertThat(links, containsLinkWithUrl(MOCK_BASE_URI + "/contact", INTERNAL));
        assertThat(links, containsLinkWithUrl(MOCK_EXTERNAL_URL, EXTERNAL));
    }

    @Test
    public void extractsImages() {
        when(document.getElementsByTag("img")).thenReturn(new Elements(
                imageWithSrc("/pic.jpg"),
                imageWithSrc(MOCK_BASE_URI+"/pic.jpg"),
                imageWithSrc(MOCK_EXTERNAL_URL +"/pic.jpg")
        ));

        Collection<Link> links = linkExtractor.getLinksFrom(document);

        assertThat(links.size(), is(3));
        assertThat(links, containsLinkWithUrl(MOCK_BASE_URI + "/pic.jpg", RESOURCE));
        assertThat(links, containsLinkWithUrl(MOCK_BASE_URI + "/pic.jpg", RESOURCE));
        assertThat(links, containsLinkWithUrl(MOCK_EXTERNAL_URL + "/pic.jpg", RESOURCE));
    }

    @Test
    public void extractsLinks() {
        when(document.getElementsByTag("link")).thenReturn(new Elements(
                linkWithHref("/index.css"),
                linkWithHref(MOCK_BASE_URI + "/style.css"),
                linkWithHref(MOCK_EXTERNAL_URL + "/style.css")
        ));

        Collection<Link> links = linkExtractor.getLinksFrom(document);

        assertThat(links.size(), is(3));
        assertThat(links, containsLinkWithUrl(MOCK_BASE_URI + "/index.css", RESOURCE));
        assertThat(links, containsLinkWithUrl(MOCK_BASE_URI + "/style.css", RESOURCE));
        assertThat(links, containsLinkWithUrl(MOCK_EXTERNAL_URL + "/style.css", RESOURCE));
    }

    @Test
    public void extractsScripts() {
        when(document.getElementsByTag("script")).thenReturn(new Elements(
                scriptWithSrc("/index.js"),
                scriptWithSrc(MOCK_BASE_URI + "/script.js"),
                scriptWithSrc(MOCK_EXTERNAL_URL + "/script.js")
        ));

        Collection<Link> links = linkExtractor.getLinksFrom(document);

        assertThat(links.size(), is(3));
        assertThat(links, containsLinkWithUrl(MOCK_BASE_URI + "/index.js", RESOURCE));
        assertThat(links, containsLinkWithUrl(MOCK_BASE_URI + "/script.js", RESOURCE));
        assertThat(links, containsLinkWithUrl(MOCK_EXTERNAL_URL + "/script.js", RESOURCE));
    }

    @Test
    public void ignoresAnchorsWithoutHref() {
        when(document.getElementsByTag("a")).thenReturn(singleAnchorWithNoHref());

        Collection<Link> links = linkExtractor.getLinksFrom(document);

        assertThat(links.size(), is(0));
    }

    @Test
    public void ignoresLinkWithoutHref() {
        when(document.getElementsByTag("link")).thenReturn(singleLinkWithNoHref());

        Collection<Link> links = linkExtractor.getLinksFrom(document);

        assertThat(links.size(), is(0));
    }

    @Test
    public void ignoresImagesWithoutSrc() {
        when(document.getElementsByTag("img")).thenReturn(singleImageWithNoSrc());

        Collection<Link> links = linkExtractor.getLinksFrom(document);

        assertThat(links.size(), is(0));
    }

    @Test
    public void ignoresScriptWithoutSrc() {
        when(document.getElementsByTag("script")).thenReturn(singleScriptWithNoSrc());

        Collection<Link> links = linkExtractor.getLinksFrom(document);

        assertThat(links.size(), is(0));
    }

    private TypeSafeMatcher<Collection<Link>> containsLinkWithUrl(String url, LinkType type) {
        return new TypeSafeMatcher<Collection<Link>>() {
            @Override
            protected boolean matchesSafely(Collection<Link> links) {
                return links.stream()
                        .anyMatch((link) -> link.getUrl().equals(url) && link.getType() == type);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a " + type + " link for url: " + url);
            }
        };
    }
}