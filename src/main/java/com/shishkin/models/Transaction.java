package com.shishkin.models;

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
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Transaction))
            return false;
        Transaction fc = (Transaction) other;
        return items.equals(fc.getItems());
    }

    @Override
    public int hashCode() {
        return items.hashCode ();
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
