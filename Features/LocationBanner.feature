Feature: : Verify location update banner and interaction
  This feature file contains Location banner and its interaction

  Scenario: Verify location update banner and interaction
    Given I am on the Cricut website homepage
    Then I should see the location banner with text "You are currently viewing Cricut United States"
    And I should see a link to update the location
    When I click the "Update your location?" link
    Then I should be navigated to the country selection page or see location update options