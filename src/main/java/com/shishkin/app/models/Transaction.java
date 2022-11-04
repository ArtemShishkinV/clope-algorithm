package com.shishkin.app.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Transaction {
    private int clusterPosition;
    private int clusterId;
    private List<String> items = new ArrayList<>();

    public Transaction(List<String> items) {
        this.clusterPosition = 0;
        this.clusterId = 0;
        this.items.addAll(items);
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
