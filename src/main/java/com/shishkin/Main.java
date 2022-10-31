package com.shishkin;

import com.shishkin.config.Config;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running...");
        System.out.println(Main.start(args));
    }

    private static String start(String[] args) {
        try {
            Config config = new Config(args);
            System.out.println(config);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "completed successfully!";
    }
}
