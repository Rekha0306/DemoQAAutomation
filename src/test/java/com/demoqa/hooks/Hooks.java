package com.demoqa.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.demoqa.context.TestContext;
import com.demoqa.driver.DriverManager;
import com.demoqa.utils.PropertyFileReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	WebDriver driver;
	TestContext testContext;

	public Hooks(TestContext context) {
		testContext = context;
	}
	
	@Before
	public void setup() {

		driver = testContext.getDriverManager().getDriver();
		driver.get(PropertyFileReader.getProperty("url"));

	}

	@After
	public void tearDown(Scenario scenario) {

		String scenarioName = scenario.getName().replaceAll(" ", "_");
		
		boolean takeScreenshot = Boolean.valueOf(PropertyFileReader.getProperty("takeScreenshotforFailures"));
		
		if (takeScreenshot && scenario.isFailed() || !takeScreenshot) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", scenarioName);
			} catch (WebDriverException noSupportScreenshot) {
				System.err.println(noSupportScreenshot.getMessage());
			}
		}
		driver.close();
	}


}
