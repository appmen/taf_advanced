package com.learning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardsPage extends BasePage{

    public DashboardsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@title='All Dashboards']")
    private WebElement dashboardsLink;

    @FindBy(xpath = "//a[contains(@class, 'dashboardTable')]")
    private List<WebElement> dashboards;

    public boolean isPageLoaded(){
        return dashboardsLink.isDisplayed();
    }

    public WebElement getDashboardByName(String dashboardName){
        return dashboards.stream().
                filter(it -> it.getText().equals(dashboardName))
                .findFirst().orElse(null);
    }
}
