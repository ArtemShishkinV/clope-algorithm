package com.shishkin.config;

import com.shishkin.enums.Errors;
import lombok.Value;

@Value
public class Config {
    String inputFile;
    String outputFile;
    double repulsion;

    public Config(String[] args) throws NumberFormatException, NullPointerException, InvalidCountArgsException {
        checkCountArgs(args);
        this.inputFile = args[0];
        this.outputFile = args[1];
        this.repulsion = getRepulsion(args[2]);
    }

    private void checkCountArgs(String[] args) throws InvalidCountArgsException {
        if (args.length != 3) throw new InvalidCountArgsException(Errors.INVALID_COUNT_CONFIG_ARGS.getMessage());
    }

    private double getRepulsion(String value) throws NumberFormatException, NullPointerException {
        double result;
        try {
            result = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(Errors.INVALID_FORMAT_THIRD_CONFIG_ARG.getMessage());
        }
        if (result <= 1) {
            throw new NumberFormatException(Errors.INVALID_VALUE_THIRD_CONFIG_ARG.getMessage());
        }
        return result;
    }
}
