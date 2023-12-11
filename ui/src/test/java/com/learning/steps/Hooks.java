package com.learning.steps;

import com.learning.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger LOG = LogManager.getRootLogger();


    @Before
    public void beforeScenario(Scenario scenario){
        LOG.info(scenario.getName());
    }

    @After
    public void tearDown() {
        DriverManager.closeDriver();
    }


}
