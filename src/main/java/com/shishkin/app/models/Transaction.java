package com.shishkin.app.models;

import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class Transaction {
    List<String> items = new ArrayList<>();

    public Transaction(List<String> items) {
        this.items.addAll(items);
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
