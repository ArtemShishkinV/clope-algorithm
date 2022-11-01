package com.shishkin;

import com.shishkin.config.ConfigFactory;
import com.shishkin.errors.InvalidCountConfigArgsException;
import com.shishkin.config.models.Config;
import com.shishkin.app.handler.ClopeAlgorithmHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Config config = ConfigFactory.createConfig(args);
            ClopeAlgorithmHandler clopeAlgorithmHandler = new ClopeAlgorithmHandler(config);
            clopeAlgorithmHandler.start();
        } catch (InvalidCountConfigArgsException | IOException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
