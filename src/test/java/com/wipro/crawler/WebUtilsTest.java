package com.wipro.crawler;

import org.junit.Test;
import org.mockito.Mock;

import static com.wipro.crawler.WebUtils.linkToSameDomain;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WebUtilsTest {

    @Mock
    private String doc = "http://www.wipro.com";

    @Test
    public void detectsLinkToSameDomain() {
        assertThat(linkToSameDomain(doc, "/xxx"), is(true));
        assertThat(linkToSameDomain(doc, "../xxx"), is(true));
        assertThat(linkToSameDomain(doc, "../xxx"), is(true));
        assertThat(linkToSameDomain(doc, "#xxx"), is(true));
        assertThat(linkToSameDomain(doc, "http://www.wipro.com/services"), is(true));

        assertThat(linkToSameDomain(doc, "http://subdomain.wipro.com"), is(false));
        assertThat(linkToSameDomain(doc, "http://amazon.com"), is(false));
    }
}