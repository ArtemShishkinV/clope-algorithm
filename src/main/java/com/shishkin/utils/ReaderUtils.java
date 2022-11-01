package com.shishkin.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReaderUtils {
    public static List<String> getItemsFromLine(String line, String delimiter) {
        return Arrays.asList(line.split(delimiter));
    }
}
