Feature: : Verify the fucntionality of the search bar
  The feature file contains the testing of various scenarios of SearchBar

  Scenario: Search for an item using Cricut search bar
    Given User is on Cricut homepage
    When User enters "vinyl" into the search field
    And User should be seeing suggestions for search item "vinyl"
    Then User clicks the search submit button
    And User should be navigated to the correct PGW (Product Grid Page)