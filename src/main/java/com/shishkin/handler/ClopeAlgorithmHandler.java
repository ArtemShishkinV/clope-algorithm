package com.shishkin.handler;

import com.shishkin.config.Config;
import com.shishkin.io.FileReader;
import com.shishkin.io.Reader;
import com.shishkin.models.Clope;
import com.shishkin.models.Cluster;
import com.shishkin.models.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClopeAlgorithmHandler {
    public static String start(String[] args) {
        try {
            Config config = new Config(args);
            Reader reader = new FileReader(config.getInputFile());
            Clope clope = new Clope();
            List<Transaction> transactionList = reader.read();
            clope.clustering(transactionList, 2.0).forEach(ClopeAlgorithmHandler::outputCluster);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "completed successfully!";
    }

    private static void outputCluster(Cluster cluster) {
        System.out.println("Cluster:" + cluster.getTransactions());
        cluster.getClustersChart().forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("Height: " + cluster.getHeight());
        System.out.println("Width: " + cluster.getWidth());
    }
}
