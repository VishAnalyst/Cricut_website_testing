Feature: Main Navigation Menu

  As a user
  I want to verify that the main navigation and its sub-links are present
  So that I can access all the major sections of the website

  Given User is on Cricut homepage

  Scenario: Verify main navigation links are displayed
    Then The main navigation bar should be visible
    And The following main menu items should be present for "Discover":
      | Learn About Cricut    |
      | What Is Cricut?       |
      | Our Design App        |
      | What Is Design Space? |
      | Resources             |
      | Machine Quiz          |
      | Comparison Chart      |
      | Inspiration           |
      | Teachers              |
      | Blog                  |
      | Community             |
      | Projects              |