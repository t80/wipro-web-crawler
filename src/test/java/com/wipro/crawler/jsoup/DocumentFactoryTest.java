package com.wipro.crawler.jsoup;

import com.wipro.crawler.jsoup.WebPageConnectionFactory;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DocumentFactoryTest {
    private final WebPageConnectionFactory connectionFactory = mock(WebPageConnectionFactory.class);
    private final DocumentFactory documentFactory = new DocumentFactory(connectionFactory);
    private final Connection connection = mock(Connection.class);

    @Test
    public void returnsNullOnFailedDocumentRetrieval() throws IOException {
        when(connectionFactory.connectionFor("some-url")).thenReturn(connection);
        when(connection.get()).thenThrow(IOException.class);

        Document document = documentFactory.documentFor("some-url");

        assertThat(document, is(nullValue()));
    }

    @Test
    public void returnsConnections() throws IOException {
        when(connectionFactory.connectionFor("some-url")).thenReturn(connection);
        when(connection.get()).thenReturn(mock(Document.class));

        assertThat(documentFactory.documentFor("some-url"), isA(Document.class));
    }

}