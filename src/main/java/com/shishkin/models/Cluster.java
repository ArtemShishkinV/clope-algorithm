package com.shishkin.models;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@ToString
public class Cluster {
    private final List<Transaction> transactions = new ArrayList<>();

    public Cluster(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public double getHeight() {
        return getAreaChart() / (double) getClustersChart().size();
    }

    public int getAreaChart() {
        return getClustersChart().values().stream().mapToInt(Integer::valueOf).sum();
    }

    public Map<String, Integer> getClustersChart() {
        return getAllTransactionsItems().collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    private Stream<String> getAllTransactionsItems() {
        return transactions.stream().map(Transaction::getItems).flatMap(Collection::stream);
    }

}
