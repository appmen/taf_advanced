package com.learning.pages;

import com.learning.configuration.UIConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class LoginPage extends BasePage {
    private static final Map<String, String> uiConfig = new UIConfig().getConfig();
    private static final String URL = uiConfig.get("url");
    private static final String LOGIN_URL = URL + "/ui/#login";
    WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));

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

    public void login(String userName, String password) {
        openPage(URL);
        try {
            loginField.clear();
        } catch (StaleElementReferenceException ex) {
            loginField.clear();
        }
        loginField.sendKeys(userName);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
        LOG.info("Logging in with user " + userName);
    }

    public boolean isOnPage() {
        return loginButton.isDisplayed();
    }

    public void waitForRedirection() {
        try {
            wait.until(ExpectedConditions.not(urlContains(LOGIN_URL)));
        } catch (TimeoutException ignored) {
        }
    }
}
