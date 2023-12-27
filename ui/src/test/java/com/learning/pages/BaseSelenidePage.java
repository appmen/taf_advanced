package com.learning.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.learning.configuration.CommonUIConfig;
import com.learning.configuration.UIConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;

import java.util.Map;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BaseSelenidePage {
    private static final Logger LOG = LogManager.getRootLogger();

    private static final Map<String, String> COMMON_CONFIG = new CommonUIConfig().getConfig();
    private static final Map<String, String> UI_CONFIG = new CommonUIConfig().getConfig();
    private static final String DRIVER_TYPE = COMMON_CONFIG.get("browser").toLowerCase();
    private static final String TIMEOUT = COMMON_CONFIG.get("timeout");
    private static final Map<String, String> uiConfig = new UIConfig().getConfig();
    protected static final String PASSWORD = uiConfig.get("password");
    protected static final String USER_NAME = uiConfig.get("user");

    private final SelenideElement popUpMessage = $(By.xpath("//div[@class='notification-transition-enter-done']/div/p"));

    public String getPopUpTextAndDismiss(){
        LOG.info("Waiting for popUp");
        String popUpText = popUpMessage.text();
        popUpMessage.click();
        popUpMessage.shouldNotBe(visible);
        return popUpText;
    }

    @BeforeAll
    public static void init(){
        Configuration.browser = DRIVER_TYPE;
        Configuration.pageLoadTimeout = Long.parseLong(TIMEOUT);
    }

    @AfterEach
    public void closeBrowser(){
        Selenide.closeWebDriver();
    }
}
