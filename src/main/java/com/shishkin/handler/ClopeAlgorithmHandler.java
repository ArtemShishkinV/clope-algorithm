package com.shishkin.handler;

import com.shishkin.config.Config;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClopeAlgorithmHandler {
    public static String start(String[] args) {
        try {
            new Config(args);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "completed successfully!";
    }
}
