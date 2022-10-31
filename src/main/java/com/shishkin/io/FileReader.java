package com.shishkin.io;

import com.shishkin.models.Transaction;
import com.shishkin.utils.FileIOUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class FileReader implements Reader {
    private static final String DELIMITER_ITEMS_IN_TRANSACTION_LINE = ",";
    private String inputFileName;

    @Override
    public List<Transaction> read() throws IOException {
        File file = FileIOUtils.getResourceFile(inputFileName);
        return getTransactions(getTransactionStrings(file));
    }

    private List<Transaction> getTransactions(List<List<String>> transactions) {
        return transactions.stream().map(Transaction::new).collect(Collectors.toList());
    }

    private List<String> getItemsFromLine(String line) {
        return Arrays.asList(line.split(","));
    }

    private List<List<String>> getTransactionStrings(File file) throws FileNotFoundException {
        List<List<String>> items = new ArrayList<>();
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                items.add(getItemsFromLine(myReader.nextLine()));
            }
        }
        return items;
    }


}
