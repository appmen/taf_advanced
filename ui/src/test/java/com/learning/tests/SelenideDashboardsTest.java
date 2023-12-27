package com.learning.tests;

import com.codeborne.selenide.Condition;
import com.learning.pages.BaseSelenidePage;
import com.learning.pages.SelenideDashboardPage;
import com.learning.pages.SelenideLoginPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SelenideDashboardsTest extends BaseSelenidePage {
    private static final String WIDGET_1 = "LAUNCH STATISTICS AREA";
    private static final String WIDGET_2 = "LAUNCH STATISTICS BAR";
    private static final String DEMO_DASHBOARD = "DEMO DASHBOARD";

    private final SelenideLoginPage selenideLoginPage = new SelenideLoginPage();
    private final SelenideDashboardPage selenideDashboardPage = new SelenideDashboardPage();

    @Test
    public void dragAndDropWidgetJavaTest() {
        selenideLoginPage.login(USER_NAME, PASSWORD);
        selenideDashboardPage.isPageLoaded();

        selenideDashboardPage.openDashboardByName(DEMO_DASHBOARD);
        Map<String, Point> widgetCoords = selenideDashboardPage.getWidgetsCoords();
        Point widgetNewCoords = selenideDashboardPage.dragDashboardJava(WIDGET_1, WIDGET_2, widgetCoords);
        Point widgetOldCoords = widgetCoords.get(WIDGET_1);
        assertNotEquals(widgetOldCoords, widgetNewCoords);
    }

    @Test
    public void widgetVisibilityJsTest() {
        selenideLoginPage.login(USER_NAME, PASSWORD);
        selenideDashboardPage.isPageLoaded();

        selenideDashboardPage.openDashboardByName(DEMO_DASHBOARD);
        selenideDashboardPage.scrollToWidget(WIDGET_2);

        System.out.println(selenideDashboardPage.isInViewJs(WIDGET_2));
    }

    @Test
    public void updateDashboardJsTest() {
        String descriptionText = "testy123";
        selenideLoginPage.login(USER_NAME, PASSWORD);
        selenideDashboardPage.isPageLoaded();

        selenideDashboardPage.openDashboardByName(DEMO_DASHBOARD);
        selenideDashboardPage.editDashboardDescription(descriptionText);
        selenideDashboardPage.goToAllDashboards();
        assertEquals(descriptionText, selenideDashboardPage.getDashboardDescription());
    }

    @Test
    public void createDeleteDashboardTest(){
        String dashboardName = "Test";
        selenideLoginPage.login(USER_NAME, PASSWORD);
        selenideDashboardPage.isPageLoaded();
        getPopUpTextAndDismiss();

        selenideDashboardPage.createDashboard(dashboardName, "dshb");
        getPopUpTextAndDismiss();
        selenideDashboardPage.goToAllDashboards();
        selenideDashboardPage.getDashboardByName(dashboardName).get(0).shouldHave(Condition.text(dashboardName));
        selenideDashboardPage.deleteDashboard(dashboardName);
        assertEquals("Dashboard has been deleted", getPopUpTextAndDismiss());
    }

    @Test
    public void updateDashboard(){
        String dashboardName = "TestUpdate";
        String filterName = "DEMO_FILTER";
        selenideLoginPage.login(USER_NAME, PASSWORD);
        selenideDashboardPage.isPageLoaded();
        getPopUpTextAndDismiss();
        selenideDashboardPage.createDashboard(dashboardName, "dshb");
        selenideDashboardPage.addWidget(dashboardName, "Overall statistics", filterName);
        Set<String> widgests = selenideDashboardPage.getWidgetsCoords().keySet();
        selenideDashboardPage.deleteDashboard(dashboardName);
        assertTrue(widgests.stream().anyMatch(it -> it.contains(filterName)));
    }

}
