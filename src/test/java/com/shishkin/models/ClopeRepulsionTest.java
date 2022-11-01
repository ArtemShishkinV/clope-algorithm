package com.shishkin.models;

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
    @ValueSource(doubles = {1.0, 2.0})
    void testRepulsionClustering(double repulsion) {
        Clope clope = new Clope();

        List<Cluster> expected = results.get(repulsion);
        List<Cluster> actual = clope.clustering(transactions, repulsion);

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
        map.put(1.0, new ArrayList<>(List.of(
                new Cluster(transactions.get(0), transactions.get(1))
        )));

        map.put(2.0, new ArrayList<>(List.of(
                new Cluster(transactions.get(0)),
                new Cluster(transactions.get(1))
        )));

        return map;
    }

}
