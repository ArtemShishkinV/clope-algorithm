package com.shishkin.handler;

import com.shishkin.config.Config;
import com.shishkin.io.FileReader;
import com.shishkin.io.Reader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClopeAlgorithmHandler {
    public static String start(String[] args) {
        try {
            Config config = new Config(args);
            Reader reader = new FileReader(config.getInputFile());
            reader.read().forEach(System.out::println);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "completed successfully!";
    }
}
