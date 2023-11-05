package com.learning;

import com.learning.driver.DriverManager;
import com.learning.pages.DashboardsPage;
import com.learning.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import utils.TestListener;


@Listeners({TestListener.class})
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardsPage dashboardsPage;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        dashboardsPage = new DashboardsPage(driver);
    }

    @AfterClass
    public void tearDown() {
        DriverManager.closeDriver();
    }
    @Test(testName = "Test successful login")
    void testSuccessfulLogin(){
        loginPage.login();
        Assert.assertTrue(dashboardsPage.isPageLoaded(), "Dashboards page is not loaded");
    }
}
