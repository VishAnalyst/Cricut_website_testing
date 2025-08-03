Feature: Main Navigation Menu

  As a user
  I want to verify that the main navigation and its sub-links are present
  So that I can access all the major sections of the website

  Scenario: Verify main navigation links are displayed
    Given User is on Cricut homepage
    Then The main navigation bar should be visible
    And The main navigation should have the following items:
      | Discover             |
      | Sale                 |
      | Cutting Machines     |
      | Heat Presses         |
      | Tools & Accessories  |
      | Materials            |
      | Join Cricut Access™  |

  Scenario: Hover over 'Discover' and verify menu headings and sub-links
    When I hover over the main navigation item "Discover"
    Then The following headings and sub-links should be visible:
      | Heading           | Sub-Link              |
      | Learn About Cricut | What Is Cricut?       |
      | Our Design App     | What Is Design Space? |
      | Resources          | Machine Quiz          |
      | Resources          | Comparison Chart      |
      | Inspiration        | Teachers              |
      | Inspiration        | Blog                  |


