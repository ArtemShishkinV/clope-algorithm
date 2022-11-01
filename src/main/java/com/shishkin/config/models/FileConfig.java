package com.shishkin.config.models;

import com.shishkin.config.ConfigUtils;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class FileConfig extends Config {
    String inputFile;

    public FileConfig(ConfigType configType, String[] args) throws NumberFormatException {
        super(configType, ConfigUtils.getRepulsion(args[2]));
        this.inputFile = args[1];
    }

}
