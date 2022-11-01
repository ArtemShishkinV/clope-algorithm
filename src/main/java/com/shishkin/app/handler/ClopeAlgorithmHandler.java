package com.shishkin.app.handler;

import com.shishkin.config.models.Config;
import com.shishkin.config.models.ConfigType;
import com.shishkin.config.models.FileConfig;
import com.shishkin.errors.ErrorMessage;
import com.shishkin.io.reader.ConsoleReader;
import com.shishkin.io.writer.ConsoleWriter;
import com.shishkin.io.reader.FileReader;
import com.shishkin.app.models.Clope;
import com.shishkin.app.models.Cluster;
import com.shishkin.app.models.Transaction;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class ClopeAlgorithmHandler {
    private final Config config;

    public void start() throws IOException {
        List<Transaction> transactionList = readTransactions();
        List<Cluster> clusters = clusteringClope(transactionList);
        outputClusters(clusters);
    }

    private List<Transaction> readTransactions() throws IOException {
        if (this.config.getConfigType() == ConfigType.FILE) {
            return readTransactions(((FileConfig) config).getInputFile());
        }
        return new ConsoleReader().read();
    }

    private List<Transaction> readTransactions(String filename) throws IOException {
        return new FileReader(filename).read();
    }

    private List<Cluster> clusteringClope(List<Transaction> transactions) throws IllegalArgumentException {
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_COUNT_SOURCE_TRANSACTIONS.getMessage());
        }
        return new Clope().clustering(transactions, this.config.getRepulsion());
    }

    private void outputClusters(List<Cluster> clusters) {
        new ConsoleWriter().write(clusters);
    }
}
