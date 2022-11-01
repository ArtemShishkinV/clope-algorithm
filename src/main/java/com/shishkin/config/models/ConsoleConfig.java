package com.shishkin.config.models;

import com.shishkin.config.ConfigUtils;

public class ConsoleConfig extends Config {
    public ConsoleConfig(ConfigType configType, String[] args) {
        super(configType, ConfigUtils.getRepulsion(args[1]));
    }
}
