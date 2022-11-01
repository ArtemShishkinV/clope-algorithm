package com.shishkin.models;

import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Clope {
    private final List<Cluster> clusters = new ArrayList<>();

    @Value
    private static class ProfitPosition {
        double value;
        int position;
    }

    public List<Cluster> clustering(List<Transaction> transactions, double repulsion) {
        transactions.forEach(transaction -> {
            if (!clusters.isEmpty()) {
                double profitNewCluster = getProfitWithNewCluster(transaction, repulsion);
                ProfitPosition profitCurrentClusters = getMaxProfitWithCurrentClusters(transaction, repulsion);
                addTransactionInClusterByMaxProfit(transaction, profitNewCluster, profitCurrentClusters);
            } else {
                clusters.add(new Cluster(transaction));
            }
        });
        return clusters;
    }

    private void addTransactionInClusterByMaxProfit(Transaction transaction,
                                                    double profitNew,
                                                    ProfitPosition profitPosition) {
        if (profitNew > profitPosition.value) {
            clusters.add(new Cluster(transaction));
        } else {
            clusters.get(profitPosition.getPosition()).add(transaction);
        }
    }

    private double getProfitWithNewCluster(Transaction transaction, double repulsion) {
        Cluster clusterNew = new Cluster(transaction);
        clusters.add(clusterNew);
        double profit = getProfit(clusters, repulsion);
        clusters.remove(clusterNew);
        return profit;
    }

    private ProfitPosition getMaxProfitWithCurrentClusters(Transaction transaction, double repulsion) {
        List<Double> profits = new ArrayList<>();
        this.clusters.forEach(cluster -> {
            cluster.add(transaction);
            profits.add(getProfit(clusters, repulsion));
            cluster.remove(transaction);
        });
        double max = Collections.max(profits);
        return new ProfitPosition(max, profits.indexOf(max));
    }

    private double getProfit(List<Cluster> clusters, double repulsion) {
        double profitNumerator = 0;
        double profitDenominator = 0;

        for (Cluster cluster :
                clusters) {
            profitNumerator += calculateNumeratorProfit(cluster, repulsion);
            profitDenominator += cluster.size();
        }

        return getResultProfit(profitNumerator, profitDenominator);
    }

    private double calculateNumeratorProfit(Cluster cluster, double repulsion) {
        return cluster.getAreaChart() / Math.pow(cluster.getWidth(), repulsion) * cluster.size();
    }

    private double getResultProfit(double numerator, double denominator) {
        if (denominator == 0.0) {
            return 0.0;
        }
        return numerator / denominator;
    }

}
