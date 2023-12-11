package com.learning.configuration;

import lombok.Getter;
import utils.ConfigReader;

import java.util.Map;


public class UIConfig {
    //Using Lombok instead of a static variable just because I can :)
    private static final String CONFIG_NAME = System.getProperty("config") + ".properties";
    private static final String UI_PROPERTIES = String.format("src/main/resources/" + CONFIG_NAME);

    @Getter
    private final Map<String, String> config = new ConfigReader(UI_PROPERTIES).getConfig();

}
