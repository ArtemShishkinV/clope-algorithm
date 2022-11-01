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

class ClopeTest {
    private static List<Transaction> transactions;
    private static Map<Double, List<Cluster>> results;



    @BeforeAll
    static void init() {
        transactions = getTransactionsDifferentRepulsion();
        results = getResultsDifferentRepulsion(transactions);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0, 3.0})
    void testClustering(double repulsion) {
        Clope clope = new Clope();

        List<Cluster> expected = results.get(repulsion);
        List<Cluster> actual = clope.clustering(transactions, repulsion);

        Assertions.assertEquals(expected, actual);
    }

    private static List<Transaction> getTransactionsDifferentRepulsion() {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(List.of("a", "b")));
        transactions.add(new Transaction(List.of("b", "a", "c")));
        transactions.add(new Transaction(List.of("a", "c", "d")));
        transactions.add(new Transaction(List.of("d", "e")));
        transactions.add(new Transaction(List.of("d", "e", "f")));
        transactions.add(new Transaction(List.of("l", "l", "o", "+", "g", "g")));
        transactions.add(new Transaction(List.of("l", "l", "o")));
        transactions.add(new Transaction(List.of("g", "g")));

        return transactions;
    }

    private static Map<Double, List<Cluster>> getResultsDifferentRepulsion(List<Transaction> transactions) {
        Map<Double, List<Cluster>> map = new HashMap<>();
        map.put(1.1, new ArrayList<>(List.of(
                new Cluster(transactions.get(0), transactions.get(1), transactions.get(2), transactions.get(3), transactions.get(4)),
                new Cluster(transactions.get(5), transactions.get(6), transactions.get(7))
        )));

        map.put(2.0, new ArrayList<>(List.of(
                new Cluster(transactions.get(0), transactions.get(1), transactions.get(2)),
                new Cluster(transactions.get(3), transactions.get(4)),
                new Cluster(transactions.get(5), transactions.get(6)),
                new Cluster(transactions.get(7))
        )));

        map.put(3.0, new ArrayList<>(List.of(
                new Cluster(transactions.get(0), transactions.get(1)),
                new Cluster(transactions.get(2)),
                new Cluster(transactions.get(3), transactions.get(4)),
                new Cluster(transactions.get(5)),
                new Cluster(transactions.get(6)),
                new Cluster(transactions.get(7))
        )));

        return map;
    }


}