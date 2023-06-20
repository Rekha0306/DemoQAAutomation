package com.demoqa.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


import com.demoqa.utils.CommonUtils;
import com.demoqa.utils.PropertyFileReader;



/**
 * This class is used to initiate the requested browser.
 * If no browser is passed, then Chrome is considered as 
 * default browser.
 *
 */
public class DriverManager {

	private WebDriver webDriver = null;

	private void initializeBrowser() {
		String browserType = System.getProperty("browser");
		if (browserType == null)
			browserType = PropertyFileReader.getProperty("browser");

		switch (browserType.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				webDriver = new ChromeDriver(chromeOptions);
				break;
			case "firefox":
				webDriver = new FirefoxDriver();
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--remote-allow-origins=*");
				webDriver = new EdgeDriver(edgeOptions);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + browserType);
		}

		webDriver.manage().deleteAllCookies();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CommonUtils.PAGE_LOAD_TIME));
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonUtils.IMPLICIT_WAIT_TIME));
	}

	public WebDriver getDriver() {
		if (webDriver == null)
			initializeBrowser();
		return webDriver;

	}
	
	public void closeDriver() {
		webDriver.close();
		webDriver.quit();
	}

}
