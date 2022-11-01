package com.shishkin.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
    INVALID_CONFIG_MODE("invalid mode. check correct first arg."),
    INVALID_CONSOLE_CONFIG_COUNT_ARGS("invalid count args. should be 2."),
    INVALID_FILE_CONFIG_COUNT_ARGS("invalid count args. should be 3."),
    INVALID_FORMAT_REPULSION_CONFIG_ARG("repulsion must be a number."),
    INVALID_VALUE_REPULSION_CONFIG_ARG("repulsion must be greater or equal than 1."),
    INVALID_COUNT_SOURCE_TRANSACTIONS("invalid count transactions. must be greater than 0");

    private final String message;

    @Override
    public String toString() {
        return message;
    }
}
