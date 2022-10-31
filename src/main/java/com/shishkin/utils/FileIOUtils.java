package com.shishkin.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileIOUtils {
    public static File getResourceFile(String fileName) {
        URL url = Thread.currentThread()
                .getContextClassLoader()
                .getResource(fileName);

        if (url == null) {
            throw new IllegalArgumentException(fileName + " is not found.");
        }

        return new File(url.getFile());
    }
}
