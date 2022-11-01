package com.shishkin.io.reader;

import com.shishkin.app.models.Transaction;
import com.shishkin.io.utils.FileIOUtils;
import com.shishkin.io.utils.ReaderUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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



    private List<List<String>> getTransactionStrings(File file) throws FileNotFoundException {
        List<List<String>> items = new ArrayList<>();
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                items.add(ReaderUtils.getItemsFromLine(
                        myReader.nextLine(),
                        DELIMITER_ITEMS_IN_TRANSACTION_LINE));
            }
        }
        return items;
    }


}
