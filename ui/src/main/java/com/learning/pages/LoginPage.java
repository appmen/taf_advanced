package com.learning.pages;

import com.learning.configuration.CommonUIConfig;
import com.learning.configuration.UIConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class LoginPage extends BasePage{
    private static final Map<String, String> uiConfig = new UIConfig().getConfig();
    private static final String URL = uiConfig.get("url");
    private static final String USER_NAME = uiConfig.get("user");
    private static final String PASSWORD = uiConfig.get("password");

    private static final Logger LOG = LogManager.getRootLogger();

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "login")
    private WebElement loginField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    private WebElement loginButton;

    public void login(){
        openPage(URL);
        loginField.sendKeys(USER_NAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
        LOG.info("Logging in with user " + USER_NAME);
    }

    public boolean isPageLoaded(){
        return loginButton.isDisplayed();
    }
}
