package com.shishkin.config.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Config {
    private ConfigType configType;
    protected double repulsion;
}
