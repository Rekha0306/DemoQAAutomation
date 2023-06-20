package com.demoqa.context;

import com.demoqa.driver.DriverManager;
import com.demoqa.pageobjects.PageObjectManager;

/**
 * 
 * This class is used as TestContext and used for
 *pass the objects and data across the scenerios and steps.
 */
public class TestContext {


   private final ScenarioContext scenarioContext;
   private final PageObjectManager pageObjectManager;
   private final DriverManager driverManager;
   

    public TestContext() {
    	driverManager = new DriverManager();
        scenarioContext = new ScenarioContext();
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
    
       
    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
    
    public DriverManager getDriverManager() {
        return driverManager;
    }
}
