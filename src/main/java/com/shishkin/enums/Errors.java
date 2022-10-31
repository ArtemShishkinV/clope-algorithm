package com.shishkin.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Errors {
    INVALID_COUNT_CONFIG_ARGS("invalid count args. should be 3."),
    INVALID_FORMAT_THIRD_CONFIG_ARG("invalid third arg. must be a number."),
    INVALID_VALUE_THIRD_CONFIG_ARG("invalid third arg. must be greater than 1.");

    private final String message;

    @Override
    public String toString() {
        return message;
    }
}
