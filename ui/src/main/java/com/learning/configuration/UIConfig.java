package com.learning.configuration;

import utils.ConfigReader;

import java.util.Properties;

public class UIConfig {
    private static String CONFIG_NAME = System.getProperty("config") + ".properties";
    private static String UI_PROPERTIES = String.format("src/main/resources/" + CONFIG_NAME);

    private final Properties config = new ConfigReader(UI_PROPERTIES).getProperties();

    public String url() {
        return config.getProperty("url");
    }
    public String user() {
        return config.getProperty("user");
    }
    public String password() {
        return config.getProperty("password");
    }
    public String browser() {
        return config.getProperty("browser");
    }
    public String timeout() {
        return config.getProperty("timeout");
    }
}
