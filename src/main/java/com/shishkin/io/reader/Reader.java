package com.shishkin.io.reader;

import com.shishkin.app.models.Transaction;

import java.io.IOException;
import java.util.List;

public interface Reader {
    List<Transaction> read() throws IOException;
}
