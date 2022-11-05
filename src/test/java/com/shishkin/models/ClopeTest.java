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
    void testClustering(double repulsion) throws InterruptedException {
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
        transactions.add(new Transaction(List.of("l", "l", "o", "g", "g")));
        transactions.add(new Transaction(List.of("l", "l", "o")));
        transactions.add(new Transaction(List.of("g", "g")));

        return transactions;
    }

    private static Map<Double, List<Cluster>> getResultsDifferentRepulsion(List<Transaction> transactions) {
        Map<Double, List<Cluster>> map = new HashMap<>();
        Cluster cluster1 = new Cluster(0, transactions.get(0));
        cluster1.add(transactions.get(1));
        cluster1.add(transactions.get(2));
        cluster1.add(transactions.get(3));
        cluster1.add(transactions.get(4));
        Cluster cluster2 = new Cluster(1, transactions.get(5));
        cluster2.add(transactions.get(6));
        cluster2.add(transactions.get(7));
        map.put(1.0, new ArrayList<>(List.of(cluster1, cluster2)));

        Cluster cluster3 = new Cluster(0, transactions.get(0));
        cluster3.add(transactions.get(1));
        cluster3.add(transactions.get(2));
        Cluster cluster4 = new Cluster(1, transactions.get(3));
        cluster4.add(transactions.get(4));
        Cluster cluster5 = new Cluster(2, transactions.get(5));
        cluster5.add(transactions.get(6));
        cluster5.add(transactions.get(7));

        map.put(2.0, new ArrayList<>(List.of(cluster3, cluster4, cluster5)));

        cluster1 = new Cluster(0, transactions.get(0));
        cluster1.add(transactions.get(1));
        cluster2 = new Cluster(1, transactions.get(2));
        cluster3 = new Cluster(2, transactions.get(3));
        cluster3.add(transactions.get(4));
        cluster4 = new Cluster(3, transactions.get(5));
        cluster4.add(transactions.get(6));
        cluster4.add(transactions.get(7));

        map.put(3.0, new ArrayList<>(List.of(
                cluster1, cluster2, cluster3, cluster4
        )));

        return map;
    }


}