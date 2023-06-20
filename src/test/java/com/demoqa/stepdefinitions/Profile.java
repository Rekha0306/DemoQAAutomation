package com.demoqa.stepdefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.demoqa.context.TestContext;
import com.demoqa.pageobjects.LoginPage;
import com.demoqa.pageobjects.ProfilePage;
import com.demoqa.pageobjects.BookStorePage;
import com.demoqa.pageobjects.BookDetailsPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Profile {

	
	TestContext testContext;
	private LoginPage loginPage;
	private ProfilePage profilePage;
	private BookStorePage bookStorePage;
	private BookDetailsPage bookDetailsPage;
	
	public Profile(TestContext context) {
		testContext = context;
		loginPage=testContext.getPageObjectManager().getLoginPage();
		profilePage = testContext.getPageObjectManager().getProfilePage();
		bookStorePage =  testContext.getPageObjectManager().getBookStorePage();
		bookDetailsPage =  testContext.getPageObjectManager().getBookDetailsPage();
	}
	

	@Given("^User successfully logged into BookStore Application with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void user_successfully_logged_into_book_store_application_with_username_and_password(String userName, String password) {
		Assert.assertTrue(loginPage.isLoginPage());
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		String logedInUser = profilePage.getLoggedInUserName();
		String pageTitle = profilePage.getPageTitle();
		Assert.assertEquals(pageTitle, "Profile");
		Assert.assertEquals(logedInUser, userName);
		
		testContext.getScenarioContext().setContext("userName",userName);
		testContext.getScenarioContext().setContext("password",password);
				
		profilePage.pageSetUp();	   
	}
	
	
	@Given("User checks for number of existing books added to profile")
	public void user_checks_for_number_of_existing_books_added_to_profile() {
	    
	    testContext.getScenarioContext().setContext("NoOfBooksInProfile",profilePage.getAddedBookCountToProfile());
	}

	@Given("User clicks on BookStore button")
	public void user_clicks_on_book_store_button() {
		profilePage.clickBookStore();
	}

	@When("^User selects \"([^\"]*)\" book$")
	public void user_selects_book(String bookToBeAdded) {
		bookStorePage.clickOnSelectedBook(bookToBeAdded);
		Assert.assertEquals(bookDetailsPage.getBookTitle(),bookToBeAdded);
		bookDetailsPage.pageSetUp();
		testContext.getScenarioContext().setContext("bookToBeAdded",bookToBeAdded);
	}

	@When("navigate to bookDetails page and clicks on add button")
	public void navigate_to_book_details_page_and_clicks_on_add_button() throws InterruptedException {
		bookDetailsPage.waitTime();
		bookDetailsPage.clickAddBook();
		bookDetailsPage.waitTime();
		
	}

	@Then("^Window alert should be displayed with \"([^\"]*)\" message$")
	public void window_alert_should_be_displayed_with_message(String message) {
		bookDetailsPage.alertIsPresent();
		Assert.assertEquals(bookDetailsPage.getAlertText(), message);
	}

	@Then("User should accept the alert")
	public void user_should_accept_the_alert() {
		bookDetailsPage.acceptAlert();
	}

	@Then("User navigates to profile page by menu")
	public void user_navigates_to_profile_page_by_menu() {
		loginPage.clickMenu("Profile");
		String pageTitle = profilePage.getPageTitle();
		Assert.assertEquals(pageTitle, "Profile");
	}

	@Then("User should verify book name added to the profile")
	public void user_should_verify_book_name_added_to_the_profile() {
		String bookToBeAdded=testContext.getScenarioContext().getContext("bookToBeAdded").toString();
		Assert.assertTrue(profilePage.bookFoundInTheList(bookToBeAdded));
		Assert.assertTrue(profilePage.getAddedBookCountToProfile() > (Integer)testContext.getScenarioContext().getContext("NoOfBooksInProfile"));
		profilePage.clickLogout();
	}
	
	@When("^User clicks delete button for \"([^\"]*)\" book$")
	public void user_clicks_delete_button_for_book(String bookName) {
	    profilePage.clickDeleteIcon(bookName);
	    testContext.getScenarioContext().setContext("bookToBeDeleted",bookName);
	}
	@Then("User prompted with modal window and click on OK button")
	public void user_prompted_with_modal_window_click_ok() {
	   profilePage.clickOnModalOk();
	   profilePage.waitTime();
	}
	
	@Then("User should verify book is deleted")
	public void user_should_verify_book_is_deleted() {
		if(profilePage.getAddedBookCountToProfile() == 0)
		Assert.assertTrue(profilePage.noBookRecordsExist());
		else {
			String bookToBeDeleted=testContext.getScenarioContext().getContext("bookToBeDeleted").toString();
			Assert.assertFalse(profilePage.bookFoundInTheList(bookToBeDeleted));
		}
			
		profilePage.clickLogout();
	}
	
	@When("User selects each book and adds to profile")
	public void user_selects_each_book_and_adds_to_profile() {
	    
		List<WebElement> bookStoreList =  bookStorePage.getBookList();
		
		for(int i=0; i<bookStoreList.size();i++) {
			WebElement eachRec =  bookStorePage.getBookList().get(i);
			bookStorePage.javaExecutorScrollIntoView(eachRec);
			bookDetailsPage.waitTime();
			eachRec.click();
			
			bookDetailsPage.waitTime();
			//bookDetailsPage.pageSetUp();
			bookDetailsPage.clickAddBook();
			bookDetailsPage.waitTime();
			bookDetailsPage.alertIsPresent();
			bookDetailsPage.acceptAlert();
			bookDetailsPage.waitTime();
			bookDetailsPage.clickBackToBookStore();
			
		}
	}
	@Then("User should verify all books added to the profile")
	public void user_should_verify_all_books_added_to_the_profile() {
		 Assert.assertTrue( profilePage.getTotalPages() > 1 );
		 profilePage.clickLogout();
	}

	@When("User has multiple page data")
	public void user_has_multiple_page_data() {
	   Assert.assertTrue( profilePage.getTotalPages() > 1 );
	}
	@Then("Validate next and previous buttons")
	public void validate_next_and_previous_buttons() {

		Assert.assertFalse(profilePage.isPreviousButtonEnabled());
		Assert.assertTrue(profilePage.isNextButtonEnabled());
		
		profilePage.clickOnNextPage();
		profilePage.waitTime();
		
		Assert.assertTrue(profilePage.isPreviousButtonEnabled());
		Assert.assertFalse(profilePage.isNextButtonEnabled());
		
		profilePage.clickOnPreviousPage();
		profilePage.waitTime();
		
	}
	@Then("update no of rows per page")
	public void update_no_of_rows_per_page() {
		profilePage.updateNoOfRowsCount();
	}
	@Then("all records should be displayed in single page")
	public void all_records_should_be_displayed_in_single_page() {
		Assert.assertTrue( profilePage.getTotalPages() == 1 );
		Assert.assertFalse(profilePage.isNextButtonEnabled());
		Assert.assertFalse(profilePage.isPreviousButtonEnabled());
		profilePage.clickLogout();
	}
	
	@When("User clicks on Author column")
	public void user_clicks_on_author_column() {
		profilePage.updateNoOfRowsCount();
		profilePage.clickOnTableHeaderByIndex(2);
	}
	@Then("Books data should be in sorted order by Author.")
	public void books_data_should_be_in_sorted_order_by_author() {
		List<WebElement> bookList = profilePage.getTableRows();
		boolean ordered =true;
		
		for(int i=1;i<bookList.size();i++) {
			String cat1 = bookList.get(i-1).findElements(By.className("rt-td")).get(2).getText();
			String cat2 = bookList.get(i).findElements(By.className("rt-td")).get(2).getText();
			
			if(!cat2.trim().equalsIgnoreCase("") && cat2.compareTo(cat1)<0)
			{
				ordered = false;
				break;
			}
			
		}
		Assert.assertTrue(ordered);
		profilePage.clickLogout();
	}
	

	@When("^User searches for \"([^\"]*)\" book$")
	public void user_searches_for_book(String searchBook) {
		profilePage.enterSearchBox(searchBook);
		testContext.getScenarioContext().setContext("searchBook",searchBook);
	}

	@Then("Books with search criteria needs to be displayed")
	public void books_with_search_criteria_needs_to_be_displayed() {
		String searchText=testContext.getScenarioContext().getContext("searchBook").toString();
		List<WebElement> bookList = profilePage.getBookListRows();
		for(int i=0;i<bookList.size();i++) {
			String bookName = bookList.get(i).findElement(By.tagName("a")).getText();
			Assert.assertTrue(bookName.contains(searchText));
		}
		profilePage.clickLogout();
	}
	
	@When("User clicks on Delete All books button")
	public void user_clicks_on_delete_all_books_button() {
	    profilePage.clickOnDeleteAllBook();
	}
	@Then("User should verify all books deleted")
	public void user_should_verify_all_books_deleted() {
	   Assert.assertTrue(profilePage.noBookRecordsExist());
	   profilePage.clickLogout();
	}
	
	@When("User clicks on Delete Account button")
	public void user_clicks_on_delete_account_button() {

		profilePage.clickOnDeleteAccount();
	}
	@Then("User should redirect to loginPage")
	public void user_should_redirect_to_login_page() {
		loginPage.waitTime();
		Assert.assertTrue(loginPage.isLoginPage());

	}
	@Then("User should not be able to login again")
	public void user_should_not_be_able_to_login_again() {
		loginPage.enterUsername(testContext.getScenarioContext().getContext("userName").toString());
		loginPage.enterPassword(testContext.getScenarioContext().getContext("password").toString());
		loginPage.clickLogin();
		Assert.assertTrue(loginPage.isErrorMsgDisplayed());
	}
	
	

}
