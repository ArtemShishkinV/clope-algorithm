package com.shishkin.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = {"id"})
public class Cluster {
    private int id;
    private int width;
    private int area;
    private Map<String, Integer> chart;

    private final List<Transaction> transactions = new ArrayList<>();

    public Cluster(int id, Transaction transaction) {
        this.id = id;
        this.transactions.add(transaction);
        this.area = transaction.getItems().size();
        this.chart = getClustersChart();
        this.width = this.chart.size();
        transaction.setClusterId(id);
    }

    public void add(Transaction transaction) {
        addItemsToChart(transaction);

        transaction.setClusterId(this.id);
        transaction.setClusterPosition(this.transactions.size());

        transactions.add(transaction);
        updateAreaAndWidth(transaction.getItems().size());
    }

    public void remove(Transaction transaction) {
        delItemsFromChart(transaction);

        this.transactions.remove(transaction);
        transaction.setClusterId(-1);

        updateAreaAndWidth(-transaction.getItems().size());
    }

    private void updateAreaAndWidth(int changeArea) {
        this.area += changeArea;
        this.width = this.chart.size();
    }

    private void delItemsFromChart(Transaction transaction) {
        for (String item : transaction.getItems()) {
            if (!(this.chart.remove(item, 1)) && this.chart.containsKey(item)) {
                this.chart.put(item, this.getChart().get(item) - 1);
            }
        }
    }

    private void addItemsToChart(Transaction transaction) {
        for (String item : transaction.getItems()) {
            this.chart.merge(item, 1, Integer::sum);
        }
    }

    private Map<String, Integer> getClustersChart() {
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
                "S: " + getArea() + "\t" +
                "W: " + getWidth() + "\n";
    }
}
