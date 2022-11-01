package com.shishkin.io.reader;

import com.shishkin.app.models.Transaction;
import com.shishkin.io.utils.ReaderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader implements Reader {
    private static final String DELIMITER_ITEMS_IN_TRANSACTION_LINE = ",";

    @Override
    public List<Transaction> read() throws IOException {
        List<Transaction> transactions;
        System.out.println("Enter transactions line by line in the following format - a,b,c");

        try (Scanner scanner = new Scanner(System.in)) {
            int countTransactions = inputCountTransactions(scanner);
            transactions = inputTransactions(scanner, countTransactions);
        }

        return transactions;
    }

    private int inputCountTransactions(Scanner scanner) {
        System.out.println("Enter the number of transactions: ");
        int count = 0;
        if(scanner.hasNextInt()) {
            count = scanner.nextInt();
        }
        return count;
    }

    private List<Transaction> inputTransactions(Scanner scanner, int count) {
        List<Transaction> transactions = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < count; i++) {
            if (scanner.hasNextLine()) {
                List<String> items = ReaderUtils.getItemsFromLine(
                        scanner.nextLine(),
                        DELIMITER_ITEMS_IN_TRANSACTION_LINE);
                transactions.add(new Transaction(items));
            }
        }
        return transactions;
    }


}
