package com.demoqa.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import com.demoqa.utils.ElementWaitUtils;

public class ProfilePage extends ElementWaitUtils{

	public ProfilePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className =  "main-header")
	private WebElement pageTitle;
	
	@FindBy(id =  "userName-value")
	private WebElement loggedInUser;
	
	@FindBy(xpath = "//button[text()=\"Log out\"]")
	private WebElement logOutButton;
	
	@FindBy(id = "fixedban")
	private WebElement pageBottomAd;
	
	@FindBy(tagName = "footer")
	private WebElement footer;
	
	@FindBy(className = "rt-noData")
	private WebElement noBooksToProfile;
	
	@FindBy(className = "mr-2")
	private List<WebElement> bookList;
	
	@FindBy(id = "delete-record-undefined")
	private List<WebElement> bookListDeleteIcons;
	
	@FindBy(id = "gotoStore")
	private WebElement bookStoreButton;
	
	@FindBy(className = "modal-dialog")
	private WebElement modalWindow;
	
	@FindBy(id = "closeSmallModal-cancel")
	private WebElement modalCancelButton;
	
	@FindBy(id = "closeSmallModal-ok")
	private WebElement modalOkButton;
	
	@FindBy(className = "pagination-bottom")
	private WebElement paginationSection;
	
	@FindBy(xpath = "//button[text()=\"Previous\"]")
	private WebElement previousButton;
	
	@FindBy(xpath = "//button[text()=\"Next\"]")
	private WebElement nextButton;
	
	@FindBy(xpath = "//span[contains(@class,'select-wrap')]/select")
	private WebElement paginationDropDown;
	
	@FindBy(className = "-totalPages")
	private WebElement totalPages;
	
	@FindBy(xpath = "//div[contains(@class,' rt-resizable-header ')]")
	private List<WebElement> tableHeader;
	
	@FindBy(className = "rt-tr-group")
	private List<WebElement> tableRows;
	
	@FindBy(xpath = "//div[(contains(@class,'rt-tr -padRow'))]")
	private List<WebElement> paddedRows;
	
	@FindBy(id = "searchBox")
	private WebElement searchBox;
	
	@FindBy(xpath = "//button[text()=\"Delete All Books\"]")
	private WebElement deleteAllBook;
	
	@FindBy(xpath = "//button[text()=\"Delete Account\"]")
	private WebElement deleteAccount;
	
	
	
	
	public String getPageTitle() {
		return getTextFromElement(pageTitle);
	}
	
	public String getLoggedInUserName() {
		return getTextFromElement(loggedInUser);
	}
	
	public void clickLogout() {
		clickOnElement(logOutButton);
	}
	
	public void pageSetUp()	{
		javaExecutorHideElem(pageBottomAd);
		javaExecutorUpdateFooterStyle(footer);
	}
	
	public int getAddedBookCountToProfile() {
		return waitForElement(tableRows).size() - waitForElement(paddedRows).size();
		}
	
	public void clickDeleteIcon(String bookName) {
		int bookToBeDeletedIndex = getRequiredBookIndex(bookName);
		waitForElement(bookListDeleteIcons).get(bookToBeDeletedIndex).click();
	}
	
	public int getRequiredBookIndex(String bookToBeselected) {
		List<WebElement> bookListItems= waitForElement(bookList);
		int index=0;
		for(WebElement bookList : bookListItems) { 
			
			String bookName = bookList.findElement(By.tagName("a")).getText();
			if(bookName.equalsIgnoreCase(bookToBeselected))
			{
				break;
			}
			index++;
		}
		
		return index;
		
	}
		
	
	public void clickBookStore() {
		javaExecutorScrollIntoView(bookStoreButton);
		clickOnElement(bookStoreButton);
	}
	
	public void clickOnModalOk() {
		waitForVisibilityOfElement(modalWindow);
		clickOnElement(modalOkButton);
	}
	
	public int getTotalPages(){
		javaExecutorScrollIntoView(paginationSection);
		return Integer.parseInt(getTextFromElement(totalPages));
	}

	public boolean isNextButtonEnabled(){
		
		return waitForVisibilityOfElement(nextButton).isEnabled();
	}
	
	public boolean isPreviousButtonEnabled(){
		
		return waitForVisibilityOfElement(previousButton).isEnabled();
	}
	
	public void clickOnNextPage() {
		clickOnElement(nextButton);
	}
	
	public void clickOnPreviousPage() {
		clickOnElement(previousButton);
	}
	
	public void updateNoOfRowsCount(){
		javaExecutorScrollIntoView(paginationSection);
		Select pageinationdropdown = new Select(waitForVisibilityOfElement(paginationDropDown));
		pageinationdropdown.selectByIndex(1);
		
		javaExecutorScrollIntoView(paginationSection);
	}
	
	public void clickOnTableHeaderByIndex(int index) {
		javaExecutorScrollIntoView(pageTitle);
		waitForElement(tableHeader).get(index).click();
	}
	
	public List<WebElement> getTableRows() {
		return waitForElement(tableRows);
	}
	
	public List<WebElement> getBookListRows() {
		return waitForElement(bookList);
	}
	
	public void enterSearchBox(String searchText) {
		typeTextIntoElement(searchBox,searchText);
	}
	
	public void clickOnDeleteAllBook() {
		javaExecutorScrollIntoView(deleteAllBook);
		clickOnElement(deleteAllBook);
	}
	
	public boolean noBookRecordsExist() {
		javaExecutorScrollIntoView(pageTitle);
		return displayStatusOfElement(noBooksToProfile);
	}
	
	public void clickOnDeleteAccount() {
		javaExecutorScrollIntoView(deleteAccount);
		clickOnElement(deleteAccount);
	}
	
	public boolean bookFoundInTheList(String bookToBeFound) {
		boolean bookfound=false;
		List<WebElement> bookList = getBookListRows();
		for(int i=0;i<bookList.size();i++) {
			String bookName = bookList.get(i).findElement(By.tagName("a")).getText();
			if(bookName.equalsIgnoreCase(bookToBeFound)) {
				bookfound=true;
				break;
			}
		}
		return bookfound;
	}
}
