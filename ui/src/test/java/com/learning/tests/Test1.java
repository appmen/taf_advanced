package com.learning.tests;

import com.learning.driver.DriverManager;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {

    @Test
    void test1(){
        System.out.println("myVariableValue = " + System.getProperty("abcd"));
        WebDriver driver = DriverManager.getDriver();

        // Navigate to the demoqa website
        driver.get("https://reportportal.epam.com/ui");

        driver.quit();
    }
}
