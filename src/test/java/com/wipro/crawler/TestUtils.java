package com.wipro.crawler;

import java.io.IOException;
import java.net.URL;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class TestUtils {

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
}
