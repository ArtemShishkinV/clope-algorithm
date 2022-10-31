package com.shishkin.io;

import com.shishkin.models.Transaction;

import java.util.List;

public interface Reader {
    List<Transaction> read();
}
