Feature: Home page loading
  This feature file contains test scenarios to verify the login functionality.

  Scenario: To test the Home page is loading or not
    Given I am on the Login page
    When Scrolling to wards the botton on the page
    Then Close the browser

  Scenario: To test the 20%off marketing banner
    Given I am on the Cricut Home page
    When I enter incorrect emailid
    When I enter Incorrect DOB
    When I enter Correct email and password