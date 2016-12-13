package com.wipro.crawler.sitemap;

import com.wipro.crawler.sitemap.Link;
import org.junit.Before;
import org.junit.Test;

import static com.wipro.crawler.sitemap.LinkType.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SiteMapTest {
    private SiteMap siteMap;

    @Before
    public void setUp() throws Exception {
        siteMap = new SiteMap();
    }

    @Test
    public void returnsTrueWhenPageIsListed() {
        Link mappedPage = Link.from("some-page-url", INTERNAL);
        siteMap.addPage(new Page(mappedPage.getUrl()));

        assertThat(siteMap.hasPageFor(mappedPage), is(true));
    }

    @Test
    public void returnsFalseWhenPageNotListed() {
        Link mappedPage = Link.from("some-page-url", INTERNAL);
        siteMap.addPage(new Page(mappedPage.getUrl()));

        Link unmappedPage = Link.from("some-other-page-url", INTERNAL);

        assertThat(siteMap.hasPageFor(unmappedPage), is(false));
    }
}
