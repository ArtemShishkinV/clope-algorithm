package com.shishkin;

import com.shishkin.models.Cluster;
import com.shishkin.models.Transaction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running...");
        Transaction transaction = new Transaction("a", "b");
        Cluster cluster = new Cluster(transaction);
        cluster.getTransactions().add(new Transaction("a", "b", "c"));
        cluster.getTransactions().add(new Transaction("a", "c", "d"));
        cluster.getClustersChart().forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println(cluster.getHeight());
    }
}
