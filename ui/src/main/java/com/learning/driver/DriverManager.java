package com.learning.driver;

import com.learning.configuration.UIConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static final Logger LOG = LogManager.getRootLogger();
    private static final UIConfig uiConfig = new UIConfig();
    private static WebDriver driver;

    private DriverManager(){}

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private static WebDriver createDriver(){
        String driverType = uiConfig.browser().toLowerCase();
        switch (driverType){
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {}
            case "ie" -> {}
            default -> throw new IllegalArgumentException("Incompatible type " + driverType);
        }

        LOG.info("Driver is created");
        return driver;
    }
}
