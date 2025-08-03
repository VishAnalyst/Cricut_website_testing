Feature: Main Banner Verification

  As a user
  I want to ensure the main banner is visible on the homepage
  So that I can interact with the promotional "Shop Now" link or button

  Scenario: Verify main banner and its Shop Now CTA
    Given User is on Cricut homepage
    Then The main banner should be visible
    And The main banner should contain a "Shop Now" link or button