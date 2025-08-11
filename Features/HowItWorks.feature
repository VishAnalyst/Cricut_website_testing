Feature: Verify "How it works" horizontal scroll functionality

  As a user visiting the Cricut website
  I want to view the "How it works" section
  So that I can scroll through all the available items horizontally

  Background:
    Given I am on the Cricut homepage

  Scenario: Verify the "How it works" heading is displayed
    Then I should see the section heading "How it works."

  Scenario: Verify horizontal scroll navigation works
    When I scroll to the "How it works." section
    And I click the "Next" scroll button
    Then more content should be visible in the "How it works." section
    When I click the "Previous" scroll button
    Then the earlier content should be visible again

  Scenario Outline: Verify each item under "How it works." is displayed
    When I scroll to the "How it works." section
    Then I should see the item "<ItemName>" in the gallery
    Examples:
      | ItemName                  |
      | Step 1: Choose your design|
      | Step 2: Cut your material |
      | Step 3: Assemble & enjoy  |