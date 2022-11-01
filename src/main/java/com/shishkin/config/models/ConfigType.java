package com.shishkin.config.models;

import java.util.Locale;

public enum ConfigType {
    CONSOLE, FILE, UNDEFINED;

    public static ConfigType getConfigType(String type) {
        ConfigType configType = UNDEFINED;
        try {
            configType = ConfigType.valueOf(type.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            return configType;
        }
        return configType;
    }
}
