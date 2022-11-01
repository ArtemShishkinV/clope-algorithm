package com.shishkin.handler;

import com.shishkin.enums.ErrorMessage;
import com.shishkin.io.ConsoleReader;
import com.shishkin.io.ConsoleWriter;
import com.shishkin.io.FileReader;
import com.shishkin.models.Clope;
import com.shishkin.models.Cluster;
import com.shishkin.models.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClopeAlgorithmHandler {
    private static final double REPULSION = 2.0;

    public static String start(String[] args) {
        try {
//            Config config = new Config(args);

            List<Transaction> transactionList = readTransactions();
            List<Cluster> clusters = clusteringClope(transactionList);
            outputClusters(clusters);

        } catch (Exception e) {
            return e.getMessage();
        }
        return "success!";
    }

    private static List<Transaction> readTransactions() throws IOException {
        return new ConsoleReader().read();
    }

    private static List<Transaction> readTransactions(String filename) throws IOException {
        return new FileReader(filename).read();
    }

    private static List<Cluster> clusteringClope(List<Transaction> transactions) throws IllegalArgumentException {
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_COUNT_SOURCE_TRANSACTIONS.getMessage());
        }
        return new Clope().clustering(transactions, ClopeAlgorithmHandler.REPULSION);
    }

    private static void outputClusters(List<Cluster> clusters) {

        new ConsoleWriter().write(clusters);
    }
}
