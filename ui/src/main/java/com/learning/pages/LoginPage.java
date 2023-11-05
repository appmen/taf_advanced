package com.learning.pages;

import com.learning.configuration.UIConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    private static final UIConfig uiConfig = new UIConfig();
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
        openPage(uiConfig.url());
        loginField.sendKeys(uiConfig.user());
        passwordField.sendKeys(uiConfig.password());
        loginButton.click();
        LOG.info("Logging in with user " + uiConfig.user());
    }

    public boolean isPageLoaded(){
        return loginButton.isDisplayed();
    }
}
