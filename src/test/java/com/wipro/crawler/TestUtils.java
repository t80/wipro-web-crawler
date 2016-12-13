package com.wipro.crawler;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class TestUtils {

    public static final String MOCK_BASE_URI = "http://www.abc.com";
    public static final String MOCK_EXTERNAL_URL = "http://www.xyz.com";

    public static String resourceFileToString(String filename) {
        URL resource = TestUtils.class.getClassLoader().getResource(filename);
        String string = null;
        try {
            string = new String(readAllBytes(get(resource.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }

    public static Element imageWithSrc(String src) {
        Element element = new Element(Tag.valueOf("img"), MOCK_BASE_URI);
        element.attr("src", src);
        return element;
    }

    public static Element scriptWithSrc(String src) {
        Element element = new Element(Tag.valueOf("script"), MOCK_BASE_URI);
        element.attr("src", src);
        return element;
    }

    public static Element linkWithHref(String href) {
        Element element = new Element(Tag.valueOf("link"), MOCK_BASE_URI);
        element.attr("href", href);
        return element;
    }

    public static Element internalAnchorFor(String url) {
        Element element = new Element(Tag.valueOf("a"), MOCK_BASE_URI);
        element.attr("href", url);
        return element;
    }

    public static Element externalAnchorFor(String externalUrl) {
        Element element = new Element(Tag.valueOf("a"), MOCK_BASE_URI);
        element.attr("href", externalUrl);
        return element;
    }

    public static Elements singleAnchorWithNoHref() {
        return new Elements(new Element(Tag.valueOf("a"), MOCK_BASE_URI));
    }

    public static Elements singleLinkWithNoHref() {
        return new Elements(new Element(Tag.valueOf("link"), MOCK_BASE_URI));
    }

    public static Elements singleImageWithNoSrc() {
        return new Elements(new Element(Tag.valueOf("img"), MOCK_BASE_URI));
    }

    public static Elements singleScriptWithNoSrc() {
        return new Elements(new Element(Tag.valueOf("script"), MOCK_BASE_URI));
    }
}
