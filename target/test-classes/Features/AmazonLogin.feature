Feature: Test login functionality

  Scenario: Check login is successfull with valid credentials
    Given browser is open
    And user is on login page
    When user enters username and password
    And user clicks on login button
    Then user navigated to the home page
    When user search the product
    And select the valid product from search results
    And user clicks on add to cart button
    Then verify add to card with selected product
    When user clicks on signout button
    Then user navigates to login page
