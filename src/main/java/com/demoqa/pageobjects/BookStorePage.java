package com.demoqa.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demoqa.utils.ElementWaitUtils;

public class BookStorePage extends ElementWaitUtils{

	public BookStorePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "mr-2")
	private List<WebElement> bookList;
	
	
	public void clickOnSelectedBook(String bookToBeAdded) {
		List<WebElement> bookListItems= waitForElement(bookList);
		WebElement requiredBookElem = null;
		for(WebElement bookList : bookListItems) { 
			
			String bookName = bookList.findElement(By.tagName("a")).getText();
			if(bookName.equalsIgnoreCase(bookToBeAdded))
			{
				requiredBookElem = bookList;
				break;
			}
		}
		
		javaExecutorScrollIntoView(requiredBookElem);
		clickOnElement(requiredBookElem);
		
	}
	
	public List<WebElement> getBookList() {
		
		return waitForElement(bookList);
		
	}

}
