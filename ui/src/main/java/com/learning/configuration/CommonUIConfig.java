package com.learning.configuration;

import lombok.Getter;
import utils.ConfigReader;

import java.util.Map;


public class CommonUIConfig {
    //Using Lombok instead of a static variable just because I can :)
    private static final String UI_PROPERTIES = "src/main/resources/common.properties";

    @Getter
    private final Map<String, String> config = new ConfigReader(UI_PROPERTIES).getConfig();
}
