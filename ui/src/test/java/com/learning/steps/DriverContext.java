package com.learning.steps;

import com.learning.driver.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class DriverContext {
    public WebDriver driver = null;

    @Before
    public void initDriver(){
        driver = DriverManager.getDriver();
    }

    @After
    public void closeDriver(){
        DriverManager.closeDriver();
    }
}
