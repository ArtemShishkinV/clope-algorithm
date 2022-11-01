package com.shishkin.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
    INVALID_COUNT_CONFIG_ARGS("invalid count args. should be 2."),
    INVALID_FORMAT_SECOND_CONFIG_ARG("invalid third arg. must be a number."),
    INVALID_VALUE_SECOND_CONFIG_ARG("invalid third arg. must be greater than 1."),
    INVALID_COUNT_SOURCE_TRANSACTIONS("invalid count transactions. must be greater than 0");

    private final String message;

    @Override
    public String toString() {
        return message;
    }
}
