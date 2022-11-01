package com.shishkin.app.models;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
public class Cluster {
    List<Transaction> transactions = new ArrayList<>();

    public Cluster(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public Cluster(Transaction... transactions) {
        this.transactions.addAll(List.of(transactions));
    }

    public double getHeight() {
        return getAreaChart() / (double) getClustersChart().size();
    }

    public int getWidth() {
        return getClustersChart().size();
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public void remove(Transaction transaction) {
        transactions.remove(transaction);
    }

    public int size() {
        return transactions.size();
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

    @Override
    public String toString() {
        return "Transactions:" + getTransactions() + "\n" +
                this.getClustersChart().entrySet().stream()
                        .map(k -> (k.getKey() + ": " + k.getValue()))
                        .collect(Collectors.joining("\n")) + "\n\n" +
                "H: " + Math.round(getHeight() * 100.0) / 100.0 + "\t" +
                "W: " + getWidth() + "\n";
    }
}