@deleteloginUser 
Feature: Feature to validate delete BookStore Application username

  @deleteAccount
  Scenario: Delete Account  
  	Given User successfully logged into BookStore Application with username "demoqa_testuser1" and password "Te$t1234"
    When User clicks on Delete Account button
    Then User prompted with modal window and click on OK button
    And Window alert should be displayed with "User Deleted." message
    And User should accept the alert
    And User should redirect to loginPage
    And User should not be able to login again
  
  
    