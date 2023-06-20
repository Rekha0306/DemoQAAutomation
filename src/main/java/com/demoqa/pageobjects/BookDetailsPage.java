package com.demoqa.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demoqa.utils.ElementWaitUtils;

public class BookDetailsPage extends ElementWaitUtils{

	public BookDetailsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "title-wrapper")
	private WebElement bookTitle;
	
	
	@FindBy(xpath = "//button[text()=\"Add To Your Collection\"]")
	private WebElement addBookButton;
	
	@FindBy(id = "fixedban")
	private WebElement pageBottomAd;
	
	@FindBy(tagName = "footer")
	private WebElement footer;
	
	@FindBy(id = "addNewRecordButton")
	private WebElement backToBookStore;
	
	
	public String getBookTitle() {
		return waitForElement(bookTitle).findElement(By.id("userName-value")).getText();
	}
	
	public void clickAddBook() {
		javaExecutorScrollIntoView(addBookButton);
		clickOnElement(addBookButton);
	}
	
	public void clickBackToBookStore() {
		javaExecutorScrollIntoView(backToBookStore);
		clickOnElement(backToBookStore);
	}
	
	public void pageSetUp()	{
		javaExecutorHideElem(pageBottomAd);
		javaExecutorUpdateFooterStyle(footer);
	}
	
}
