package com.learning.steps;

import com.learning.driver.DriverManager;
import com.learning.pages.LoginPage;
import io.cucumber.java.en.Given;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps{
    private final ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();

    public LoginSteps(){
        loginPage.set(new LoginPage(DriverManager.getDriver()));
    }

    @Given("I attempt to login with user {string} and {string}")
    public void iAttemptToLoginWithUserAnd(String user, String password) {
        loginPage.get().login(user, password);
    }

    @Given("I wait until user is redirected to the Dashboard page")
    public void waitUntilUserRedirectedToDashboard(){
        loginPage.get().waitForRedirection();
    }

    @Given("Im still on login page")
    public void iOnLoginPage(){
        assertTrue(loginPage.get().isOnPage());
    }
}
