package com.learning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardsPage extends BasePage{

    public DashboardsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@title='All Dashboards']")
    private WebElement loginField;

    public boolean isPageLoaded(){
        return loginField.isDisplayed();
    }
}
