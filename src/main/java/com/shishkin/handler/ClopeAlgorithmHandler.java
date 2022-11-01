package com.shishkin.handler;

import com.shishkin.config.Config;
import com.shishkin.config.FileConfig;
import com.shishkin.enums.ErrorMessage;
import com.shishkin.io.ConsoleReader;
import com.shishkin.io.ConsoleWriter;
import com.shishkin.io.FileReader;
import com.shishkin.models.Clope;
import com.shishkin.models.Cluster;
import com.shishkin.models.Transaction;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class ClopeAlgorithmHandler {
    private final Config config;

    public String start(String[] args) {
        try {
            List<Transaction> transactionList = readTransactions();
            List<Cluster> clusters = clusteringClope(transactionList);
            outputClusters(clusters);

        } catch (Exception e) {
            return e.getMessage();
        }
        return "success!";
    }

    private List<Transaction> readTransactions() throws IOException {
        return new ConsoleReader().read();
    }

    private List<Transaction> readTransactions(String filename) throws IOException {
        return new FileReader(filename).read();
    }

    private List<Cluster> clusteringClope(List<Transaction> transactions) throws IllegalArgumentException {
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_COUNT_SOURCE_TRANSACTIONS.getMessage());
        }
        return new Clope().clustering(transactions, 1.1);
    }

    private void outputClusters(List<Cluster> clusters) {
        new ConsoleWriter().write(clusters);
    }
}
