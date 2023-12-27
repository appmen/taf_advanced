package com.learning.pages;

import com.codeborne.selenide.SelenideElement;
import com.learning.configuration.UIConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideLoginPage {
    private static final Map<String, String> uiConfig = new UIConfig().getConfig();
    private static final String URL = uiConfig.get("url");
    private static final String LOGIN_URL = URL + "/ui/#login";

    private static final Logger LOG = LogManager.getRootLogger();

    private final SelenideElement loginField = $(By.name("login"));
    private final SelenideElement passwordField = $(By.name("password"));
    private final SelenideElement loginButton = $(By.xpath("//button[contains(text(), 'Login')]"));


    public void login(String userName, String password) {
        open(LOGIN_URL);
        try {
            loginField.clear();
        } catch (StaleElementReferenceException ex) {
            loginField.clear();
        }
        LOG.info("Logging in with user " + userName);
        loginField.sendKeys(userName);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void isOnPage() {
        LOG.info("Checking if user is on a login page");
        loginButton.shouldBe(visible);
    }


}
