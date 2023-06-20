@Regression @Profile
Feature: Feature to validate BookStore Application Profile scenerios

  @addBook
  Scenario: Add Book to the profile
    Given User successfully logged into BookStore Application with username "demoqa_testuser" and password "Te$t1234"
    And User checks for number of existing books added to profile
    And User clicks on BookStore button
    When User selects "You Don't Know JS" book 
    And navigate to bookDetails page and clicks on add button
    Then Window alert should be displayed with "Book added to your collection." message
    And User should accept the alert
    And User navigates to profile page by menu
    And User should verify book name added to the profile 

  @deleteBook
  Scenario: Delete Book from the profile
 	 	Given User successfully logged into BookStore Application with username "demoqa_testuser" and password "Te$t1234"
 	 	When User clicks delete button for "You Don't Know JS" book 
 	 	Then User prompted with modal window and click on OK button
    And Window alert should be displayed with "Book deleted." message
    And User should accept the alert
    And User should verify book is deleted 
  
  @addAllBooks
  Scenario: Add all the books to the profile  
  	Given User successfully logged into BookStore Application with username "demoqa_testuser" and password "Te$t1234"
    And User clicks on BookStore button
    When User selects each book and adds to profile 
    Then User navigates to profile page by menu
    And User should verify all books added to the profile 
  
  
  
  @validateSearchBox
  Scenario: Validate Search box
 	 Given User successfully logged into BookStore Application with username "demoqa_testuser" and password "Te$t1234"
 	 When User searches for "JavaScript" book
 	 Then Books with search criteria needs to be displayed
  
 
  @validatePagination
  Scenario: Validate Pagination 
    Given User successfully logged into BookStore Application with username "demoqa_testuser" and password "Te$t1234"
    When User has multiple page data
    Then Validate next and previous buttons
    And update no of rows per page 
    And all records should be displayed in single page
  

  @validateSorting
  Scenario: Validate Ascending Sort
  	Given User successfully logged into BookStore Application with username "demoqa_testuser" and password "Te$t1234"
    When User clicks on Author column
    Then Books data should be in sorted order by Author.
  
    
  @deleteAllBooks
  Scenario: Delete All books from the profile
  	Given User successfully logged into BookStore Application with username "demoqa_testuser" and password "Te$t1234"
    When User clicks on Delete All books button
    Then User prompted with modal window and click on OK button
    And Window alert should be displayed with "All Books deleted." message
    And User should accept the alert
    And User should verify all books deleted

  
  
    