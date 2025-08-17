Feature: Verify "How it works" section on Cricut website

  As a Cricut website visitor
  I want to verify that the "How it works" section is displayed correctly
  So that I can ensure its content and scrolling functionality works as expected

  Scenario: Verify that the "How it works" section exist
    Given I open the Cricut website
    And I scroll down to the "How it works" section
    Then I should see the "How it works" section on the page
    Then I should see exactly 4 items in the "How it works" section
    And the items should have the following content:
      | Title                 | Description                                                                                                                               |
      | Get inspired          | Design your idea from scratch or find inspiration in the Cricut design library.                                                          |
      | Make it personal      | Customize your design by adding a name or a note, experiment with colors, fonts, effects & more.                                         |
      | Cut your design       | Let your Cricut machine work its magic, cutting every piece of your project with intricacy & precision.                                   |
      | Put it all together   | Assemble the pieces or apply your design to almost anything — from notebooks to night lights, T-shirts to totes.                         |

  Scenario: Verify horizontal scroll reveals the 5th item
    When I click the "Next" scroll button in the "How it works" section
    Then I should see a new item with the title "Admire your work"
    And its description should be "You did it! Now comes the hardest part: Decide to keep it for yourself or gift it to someone you love."