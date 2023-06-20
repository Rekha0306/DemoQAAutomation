package com.demoqa.utils;



import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is defined to have methods 
 * to use selenium driver wait till
 * the elements are found. 
 */

public class ElementWaitUtils {

	public WebDriver driver;
	WebDriverWait wait;

	public ElementWaitUtils(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	 public void waitTime() {
		 try {
			Thread.sleep(4000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	/**
	 * This method will be used to click on an button or href links
	 * @param element
	 * @param durationInSeconds
	 */
	public void clickOnElement(WebElement element) {

		WebElement webElement = waitForElement(element);
		webElement.click();

	}

	/**
	 * This method will be used to type text in any form field via sendKeys.
	 * @param element
	 * @param textToBeTyped
	 * @param durationInSeconds
	 */
	public void typeTextIntoElement(WebElement element, String textToBeTyped) {

		WebElement webElement = waitForElement(element);
		webElement.click();
		webElement.clear();
		webElement.sendKeys(textToBeTyped);

	}

	/**
	 * This method will wait for the specific time till the requested element is found and can be clickable on the screen
	 * @param element
	 * @param durationInSeconds
	 * @return
	 */
	public WebElement waitForElement(WebElement element) {

		WebElement webElement = null;

		try {
			webElement  = wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return webElement;

	}

	/**
	 * This method will wait for the specific time till the requested element list is found on the screen
	 * @param elementList
	 * @param durationInSeconds
	 * @return
	 */
	public List<WebElement> waitForElement(List<WebElement> elementList) {

		List<WebElement> webElementList = null;

		try {
			webElementList  = wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return webElementList;

	}

	/**
	 * This method will wait for the specific time till the requested element is visible on the screen
	 * @param element
	 * @param durationInSeconds
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(WebElement element) {

		WebElement webElement = null;

		try {
			webElement  = wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return webElement;

	}
	
	public WebElement waitForVisibilityOfElement(By element) {

		WebElement webElement = null;

		try {
			webElement  = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return webElement;

	}
	
	/**
	 * This method will wait for the specific time till the requested element invisibility on the screen.
	 * @param element
	 * @param durationInSeconds
	 * @return
	 */
	public boolean waitForInVisibilityOfElement(WebElement element) {

		return wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * This method will wait for the specific time to find the element and fetch the text.
	 * @param element
	 * @param durationInSeconds
	 * @return
	 */
	public String getTextFromElement(WebElement element) {

		WebElement webElement = waitForVisibilityOfElement(element);
		return webElement.getText();

	}

	/**
	 * This method will wait for the specific time and checks the status of the element.
	 * @param element
	 * @param durationInSeconds
	 * @return
	 */
	public boolean displayStatusOfElement(WebElement element) {

		try {
			WebElement webElement = waitForVisibilityOfElement(element);
			return webElement.isDisplayed();
		} catch (Throwable e) {
			return false;
		}

	}

	/**
	 * This method will be used to click on an button or href links using mouse actions
	 * @param element
	 * @param durationInSeconds
	 */
	public void mouseHoverAndClick(WebElement element) {

		WebElement webElement = waitForVisibilityOfElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).click().build().perform();

	}
	
	public void javaExecutorScrollIntoView(WebElement element) {
	        JavascriptExecutor jse = (JavascriptExecutor) driver;
	        jse.executeScript("arguments[0].scrollIntoView()", waitForVisibilityOfElement(element));
	 }
	
	public void javaExecutorScrollDown(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void javaExecutorClick(WebElement element) {
	        JavascriptExecutor executor = (JavascriptExecutor) driver;
	        executor.executeScript("arguments[0].click();", waitForVisibilityOfElement(element));
	}
	
	public void javaExecutorHideElem(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style.visibility='hidden'", waitForVisibilityOfElement(element));
	}
	
	public void javaExecutorUpdateFooterStyle(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style.position='relative'", waitForVisibilityOfElement(element));
	}
	
	 public WebDriver.TargetLocator switchTo() {
	        return driver.switchTo();
	   }

	 public Alert switchToAlert() {
	        return switchTo().alert();
	  }

	 public void acceptAlert() {
	      switchToAlert().accept();
	 }
	 
		
	 public String getAlertText() {
		 return switchToAlert().getText(); 
	}
		 

	 public void alertIsPresent() {
	        wait.until(ExpectedConditions.alertIsPresent());
	  }

}
