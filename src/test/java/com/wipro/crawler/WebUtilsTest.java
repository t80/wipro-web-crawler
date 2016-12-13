package com.wipro.crawler;

import org.junit.Test;

import static com.wipro.crawler.WebUtils.linkToSameDomain;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WebUtilsTest {

    @Test
    public void detectsLinkToSameDomain() {
        String doc = "http://www.abc.com";

        assertThat(linkToSameDomain(doc, "http://www.abc.com/services"), is(true));

        assertThat(linkToSameDomain(doc, "http://subdomain.abc.com"), is(false));
        assertThat(linkToSameDomain(doc, "http://amazon.com"), is(false));
    }

    @Test
    public void detectsLinkToSameDomainWhenTestingSiblingPages() {
        String doc = "http://www.abc.com/contact";

        assertThat(linkToSameDomain(doc, "http://www.abc.com/services"), is(true));
        assertThat(linkToSameDomain(doc, "http://www.abc.com"), is(true));

        assertThat(linkToSameDomain(doc, "http://subdomain.abc.com"), is(false));
        assertThat(linkToSameDomain(doc, "http://amazon.com"), is(false));
    }
}