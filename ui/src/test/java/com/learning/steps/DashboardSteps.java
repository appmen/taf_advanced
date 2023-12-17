package com.learning.steps;

import com.learning.driver.DriverManager;
import com.learning.pages.DashboardsPage;
import io.cucumber.java.en.Given;

import static org.junit.Assert.assertTrue;

public class DashboardSteps {
    private final ThreadLocal<DashboardsPage> dashboardsPage = new ThreadLocal<>();

    public DashboardSteps() {
        dashboardsPage.set(new DashboardsPage(DriverManager.getDriver()));
    }

    @Given("^I see a Dashboards page$")
    public void iSeeDashboardsPage() {
        assertTrue(dashboardsPage.get().isPageLoaded());
    }

    @Given("I see a dashboard with name {string}")
    public void iSeeDashboardWithName(String dashboardName) {
        assertTrue(dashboardsPage.get().getDashboardByName(dashboardName).isDisplayed());
    }
}
