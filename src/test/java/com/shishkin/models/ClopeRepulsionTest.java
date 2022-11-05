package com.shishkin.models;

import com.shishkin.app.models.Clope;
import com.shishkin.app.models.Cluster;
import com.shishkin.app.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ClopeRepulsionTest {
    private static List<Transaction> transactions;
    private static Map<Double, List<Cluster>> results;


    @BeforeAll
    static void init() {
        transactions = getTransactionsDifferentRepulsion();
        results = getResultsDifferentRepulsion(transactions);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.1})
    void testRepulsionClustering(double repulsion) throws InterruptedException {
        Clope clope = new Clope();

        List<Cluster> expected = results.get(repulsion);
        List<Cluster> actual = clope.clustering(transactions, repulsion);
        System.out.println(expected);
        System.out.println(actual);

        Assertions.assertEquals(expected, actual);
    }

    private static List<Transaction> getTransactionsDifferentRepulsion() {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(List.of("a", "b", "c")));
        transactions.add(new Transaction(List.of("d", "e", "f")));

        return transactions;
    }

    private static Map<Double, List<Cluster>> getResultsDifferentRepulsion(List<Transaction> transactions) {
        Map<Double, List<Cluster>> map = new HashMap<>();
        Cluster cluster = new Cluster(0, transactions.get(0));
        cluster.add(transactions.get(1));
        map.put(1.0, List.of(cluster));

        Cluster cluster1 = new Cluster(0, transactions.get(0));
        Cluster cluster2 = new Cluster(1, transactions.get(1));
        map.put(2.1, List.of(cluster1, cluster2));

        return map;
    }

}
