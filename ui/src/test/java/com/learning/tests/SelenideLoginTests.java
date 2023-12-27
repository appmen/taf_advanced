package com.learning.tests;

import com.learning.pages.BaseSelenidePage;
import com.learning.pages.SelenideDashboardPage;
import com.learning.pages.SelenideLoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.Condition.visible;

public class SelenideLoginTests extends BaseSelenidePage {
    SelenideLoginPage selenideLoginPage = new SelenideLoginPage();
    SelenideDashboardPage selenideDashboardPage = new SelenideDashboardPage();

    public static Object[][] wrongUsers() {
        return new Object[][]{
                {"1", "12345678910"},
                {"advanced", "qwertyuiop"},
                {"1", "advanced"},
                {"Qwerty123456", "advanced"},
        };
    }

    @Test
    void testLogin() {
        selenideLoginPage.login(USER_NAME, PASSWORD);
        selenideDashboardPage.isPageLoaded();
    }

    @ParameterizedTest
    @MethodSource("wrongUsers")
    void testWrongCredsLogin(String userName, String password) {
        selenideLoginPage.login(userName, password);
        getPopUpTextAndDismiss();
    }
}
