package com.demoqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demoqa.utils.ElementWaitUtils;

public class LoginPage extends ElementWaitUtils{

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName = "h5")
	private WebElement welcomeText;
	
	@FindBy(id = "userName")
	private WebElement userName;
	
	@FindBy(id = "password")
	private WebElement password;
	
	@FindBy(id = "login")
	private WebElement loginButton;
	
	@FindBy(className =  "main-header")
	private WebElement pageTitle;
	
	@FindBy(id =  "userName-value")
	private WebElement loggedInUser;
	
	@FindBy(id =  "name")
	private WebElement errorMsg;
	
	//Menus
	@FindBy(xpath =  "//div[contains(@class,'show')]/ul/li/span[text()='Profile']")
	private WebElement profileMenu;
	
	@FindBy(xpath =  "//div[contains(@class,'show')]/ul/li/span[text()='Login']")
	private WebElement loginMenu;
	
	@FindBy(xpath =  "//div[contains(@class,'show')]/ul/li/span[text()='Book Store']")
	private WebElement bookStoreMenu;
	
	public void enterUsername(String userNameValue) {
		typeTextIntoElement(userName,userNameValue);
	}

	public void enterPassword(String passwordValue) {
		typeTextIntoElement(password,passwordValue);
	}
	 
	public void clickLogin() {
		clickOnElement(loginButton);
	}
	
	public boolean isLoginPage() {
		return displayStatusOfElement(welcomeText);
	}
	
	public boolean isErrorMsgDisplayed() {
		return displayStatusOfElement(errorMsg);
	}
	 
	public String getUserNameStyle() {
		return waitForElement(userName).getAttribute("class");
	}
	
	public String getPasswordStyle() {
		return waitForElement(password).getAttribute("class");
	}
	
	public void clickMenu(String menuOption) {
		switch (menuOption.toLowerCase()) {
		case "profile":
			javaExecutorScrollIntoView(profileMenu);
			clickOnElement(profileMenu);
			break;
		case "login":
			javaExecutorScrollIntoView(loginMenu);
			clickOnElement(loginMenu);
			break;
		case "bookStore":
			javaExecutorScrollIntoView(bookStoreMenu);
			clickOnElement(bookStoreMenu);	
			break;
		default:
			javaExecutorScrollIntoView(loginMenu);
			clickOnElement(loginMenu);
		}
	}

}
