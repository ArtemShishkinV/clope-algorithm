package com.shishkin.config;

import com.shishkin.config.models.Config;
import com.shishkin.config.models.ConfigType;
import com.shishkin.config.models.ConsoleConfig;
import com.shishkin.config.models.FileConfig;
import com.shishkin.errors.ErrorMessage;
import com.shishkin.errors.InvalidCountConfigArgsException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigFactory {
    public static Config createConfig(String[] args) throws InvalidCountConfigArgsException, IllegalArgumentException {
        if (args.length == 0) {
            throw new InvalidCountConfigArgsException(ErrorMessage.INVALID_CONFIG_MODE.getMessage());
        }
        ConfigType configType = ConfigType.getConfigType(args[0]);
        if (configType == ConfigType.UNDEFINED) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CONFIG_MODE.getMessage());
        }
        checkCountArgs(configType, args);

        return getConfig(configType, args);
    }

    private static Config getConfig(ConfigType configType, String[] args) {
        if (configType.equals(ConfigType.FILE)) {
            return new FileConfig(configType, args);
        } else {
            return new ConsoleConfig(configType, args);
        }
    }

    private static void checkCountArgs(ConfigType configType, String[] args) throws InvalidCountConfigArgsException {
        if (configType.equals(ConfigType.FILE) && args.length != 3) {
            throw new InvalidCountConfigArgsException(ErrorMessage.INVALID_FILE_CONFIG_COUNT_ARGS.getMessage());
        } else if (configType.equals(ConfigType.CONSOLE) && args.length != 2) {
            throw new InvalidCountConfigArgsException(ErrorMessage.INVALID_CONSOLE_CONFIG_COUNT_ARGS.getMessage());
        }
    }
}
