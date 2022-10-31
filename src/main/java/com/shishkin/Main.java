package com.shishkin;

import com.shishkin.handler.ClopeAlgorithmHandler;
import com.shishkin.models.Cluster;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Running...");
        System.out.println(ClopeAlgorithmHandler.start(args));
    }

    private static void outputCluster(Cluster cluster) {
        System.out.println("Cluster:" + cluster.getTransactions());
        cluster.getClustersChart().forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("Height: " + cluster.getHeight());
        System.out.println("Width: " + cluster.getWidth());
    }
}
