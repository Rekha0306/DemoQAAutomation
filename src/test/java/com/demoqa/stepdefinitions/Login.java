package com.demoqa.stepdefinitions;



import org.testng.Assert;
import com.demoqa.context.TestContext;
import com.demoqa.pageobjects.LoginPage;
import com.demoqa.pageobjects.ProfilePage;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
	
	TestContext testContext;
	private LoginPage loginPage;
	private ProfilePage profilePage;
	
	public Login(TestContext context) {
		testContext = context;
		loginPage=testContext.getPageObjectManager().getLoginPage();
		profilePage = testContext.getPageObjectManager().getProfilePage();
	}
	
	
	@Given("User navigates to DemoQA BookStore Application Login screen")
	public void user_navigates_to_demo_qa_book_store_application_login_screen() {
		Assert.assertTrue(loginPage.isLoginPage());
	}

	@When("^User enters valid username \"([^\"]*)\" and valid password \"([^\"]*)\"$")
	public void user_enters_valid_username_and_valid_password(String userName, String password) {
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
		testContext.getScenarioContext().setContext("userName",userName);
	}

	@When("User clicks on submit button")
	public void user_clicks_on_submit_button() {
		loginPage.clickLogin();
	}

	@Then("User should be able to login and view Profile page")
	public void user_should_be_able_to_login_and_view_profile_page() {
		
		String logedInUser = profilePage.getLoggedInUserName();
		String pageTitle = profilePage.getPageTitle();
		Assert.assertEquals(pageTitle, "Profile");
		Assert.assertEquals(logedInUser, testContext.getScenarioContext().getContext("userName").toString());
		
		profilePage.clickLogout();
	}

	@When("^User enters invalid username \"([^\"]*)\" and valid password \"([^\"]*)\"$")
	public void user_enters_invalid_username_and_valid_password(String userName, String password) {
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
	}

	@Then("Error message should be displayed to the user")
	public void error_message_should_be_displayed_to_the_user() {
		Assert.assertTrue(loginPage.isErrorMsgDisplayed());
	
	}

	@When("^User enters valid username \"([^\"]*)\" and invalid password \"([^\"]*)\"$")
	public void user_enters_valid_username_and_invalid_password(String userName, String password) {
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
	}

	@When("User enters blank username and password")
	public void user_enters_blank_username_and_password() {
		loginPage.enterUsername("");
		loginPage.enterPassword("");
	}

	@Then("Mandatory fields should be highlited.")
	public void mandatory_fields_should_be_highlited() {
		String userNameStyle = loginPage.getUserNameStyle();
		String passwordStyle = loginPage.getPasswordStyle();
		
		Assert.assertTrue(userNameStyle.contains("is-invalid"));
		Assert.assertTrue(passwordStyle.contains("is-invalid"));
	}

}
