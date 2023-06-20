@Regression
Feature: Feature to validate DemoQA BookStore Application Login test scenerios

  @loginScenerios
  Scenario: Successful Login
    Given User navigates to DemoQA BookStore Application Login screen
    When User enters valid username "demoqa_testuser" and valid password "Te$t1234"
    And User clicks on submit button
    Then User should be able to login and view Profile page


  @loginScenerios
  Scenario: Invalid Username Login
    Given User navigates to DemoQA BookStore Application Login screen
    When User enters invalid username "dummy" and valid password "Te$t1234"
    And User clicks on submit button
    Then Error message should be displayed to the user
    

  @loginScenerios
  Scenario: Invalid Password Login
    Given User navigates to DemoQA BookStore Application Login screen
    When User enters valid username "demoqa_testuser" and invalid password "dummy"
    And User clicks on submit button
    Then Error message should be displayed to the user

    
 @loginScenerios
  Scenario: Mandatory Field checks
    Given User navigates to DemoQA BookStore Application Login screen
    When User enters blank username and password
    And User clicks on submit button
    Then Mandatory fields should be highlited.  