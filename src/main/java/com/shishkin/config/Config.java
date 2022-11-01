package com.shishkin.config;

import com.shishkin.enums.ErrorMessage;
import lombok.Value;

@Value
public class Config {
    String inputFile;
    double repulsion;

    public Config(String[] args) throws NumberFormatException, InvalidCountArgsException {
        checkCountArgs(args);
        this.inputFile = args[0];
        this.repulsion = getRepulsion(args[1]);
    }

    private void checkCountArgs(String[] args) throws InvalidCountArgsException {
        if (args.length != 2) throw new InvalidCountArgsException(ErrorMessage.INVALID_COUNT_CONFIG_ARGS.getMessage());
    }

    private double getRepulsion(String value) throws NumberFormatException{
        double result;
        try {
            result = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.INVALID_FORMAT_SECOND_CONFIG_ARG.getMessage());
        }
        if (result <= 1) {
            throw new NumberFormatException(ErrorMessage.INVALID_VALUE_SECOND_CONFIG_ARG.getMessage());
        }
        return result;
    }
}
