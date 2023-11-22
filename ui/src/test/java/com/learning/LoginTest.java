package com.learning;

import com.learning.configuration.UIConfig;
import com.learning.driver.DriverManager;
import com.learning.pages.DashboardsPage;
import com.learning.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {
    private static final Map<String, String> uiConfig = new UIConfig().getConfig();
    private static final String USER_NAME = uiConfig.get("user");
    private static final String PASSWORD = uiConfig.get("password");
    private static ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();
    private static ThreadLocal<DashboardsPage> dashboardsPage = new ThreadLocal<>();

    private static ThreadLocal<WebDriver> currentDriver = new ThreadLocal<>();
    private static List<WebDriver> driversToCleanup = Collections.synchronizedList(new ArrayList<>());


    @AfterAll
    public static void tearDown() {
        Iterator<WebDriver> iterator = driversToCleanup.iterator();
        while (iterator.hasNext()) {
            WebDriver driver = iterator.next();
            driver.quit();
            iterator.remove();
        }
    }

    public static Object[][] wrongUsers() {
        return new Object[][]{
                {"1", "1"},
                {"advanced", "1"},
                {"1", "advanced"},
                {"Qwerty123456", "1"},
        };
    }

    @BeforeEach
    public void initDrivers(){
        if (currentDriver.get()==null) {
            final WebDriver driver = DriverManager.getDriver();
            driversToCleanup.add(driver);
            currentDriver.set(driver);
        }
        loginPage.set(new LoginPage(currentDriver.get()));

    }

    @Test()
    void testSuccessfulLogin() {

        dashboardsPage.set(new DashboardsPage(currentDriver.get()));
        loginPage.get().login(USER_NAME, PASSWORD);
        assertTrue(dashboardsPage.get().isPageLoaded(), "Dashboards page is not loaded");
    }

    @ParameterizedTest
    @MethodSource("wrongUsers")
    void testWrongCredsLogin(String userName, String password) {

        loginPage.get().login(userName, password);
        loginPage.get().waitForRedirection();
        assertTrue(loginPage.get().isOnPage(), "Login page is not loaded anymore!");

    }
}
