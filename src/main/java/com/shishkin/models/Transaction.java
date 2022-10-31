package com.shishkin.models;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Transaction {
    private final List<String> items = new ArrayList<>();

    public Transaction(String... items) {
        this.items.addAll(List.of(items));
    }
}
