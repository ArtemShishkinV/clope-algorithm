package com.shishkin.config;

import com.shishkin.errors.ErrorMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigUtils {
    public static double getRepulsion(String value) throws NumberFormatException {
        double result;
        try {
            result = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.INVALID_FORMAT_REPULSION_CONFIG_ARG.getMessage());
        }
        if (result < 1) {
            throw new NumberFormatException(ErrorMessage.INVALID_VALUE_REPULSION_CONFIG_ARG.getMessage());
        }
        return result;
    }
}
