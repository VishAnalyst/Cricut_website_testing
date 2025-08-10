Feature: Verify "What is Cricut?" section under the banner

  As a user
  I want to see the "What is Cricut?" heading and description
  So that I can understand the purpose of Cricut products

  Scenario: Verify heading and description text under the banner
    Given I am on the Cricut homepage
    When I scroll to the "What is Cricut?" section under the banner
    Then I should see the heading "What is Cricut?"
    And I should see the description text:
      """
      Cricut® makes smart cutting machines that work with an easy-to-learn design app, so you can express your creativity and make personalized items for any and every occasion.
      """