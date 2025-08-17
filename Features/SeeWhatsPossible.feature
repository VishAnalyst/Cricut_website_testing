Feature: Verify "See what's possible with Cricut" carousel section

  As a Cricut website visitor
  I want to verify that the "How it works" section is displayed correctly
  So that I can ensure its content and scrolling functionality works as expected


  Scenario: Verify section heading and subheading exist
    Given I open the Cricut website for See what's possible with Cricut
    And I scroll down to the "See what's possible with Cricut" section for See what's possible with Cricut
    Then I should see the heading "See what's possible with Cricut." for See what's possible with Cricut
    And I should see the subheading "Discover perfectly personalized projects made by Cricut customers & get inspired for your making journey."

  Scenario: Verify initial 4 tabs load correctly
    Then I should see exactly 4 items in the "See what's possible with Cricut" carousel
    And the items should have the following content for whats possible:
      | Title (Alt text)                                                | Description                                                                                                                             |
      | katharinaregenbogen's instagram image                            | cricut #plotterliebe #cricut #zitrone #sauermachtlustig                                                                                 |
      | marthe_et_maurice's instagram image                              | Toutes les occasions sont bonnes pour sortir la belle vaisselle : à 6 ou à 80, l'effet WHAOU est toujours au rendez-vous. On innove ... |
      | shop.littlenoah's instagram image                                | ‘DANDY’ THE LION ACTIVITY PACK AVAILABLE 🦁 Our Autumn lion activity has had a summer glow up 🌼 Give this sunny lion the mane he ...     |
      | emilyscraftingstudio's instagram image                           | kindle sticker bundle ✨✨ #crafting #stickers #cricut #crafty #cute #custom                                                             |

  Scenario: Verify carousel navigation (next and previous arrows)
    When I click the "Next" arrow button in the "See what's possible with Cricut" carousel
    Then I should see a new set of items in the carousel including:
      | Title (Alt text)                 | Description (starts with)                                                                                 |
      | misshusteddiy's instagram image  | AKTIVITETS T-SHIRT 🐥🐣🐣 TIL BØRN OG BARNLIGE SJÆLE 😃😃 Trykket er et vinyltryk (HTV)...                 |
      | derienzo_designs's instagram image | Bridal Denim Jacket for my Best Friend 🤍                                                                 |
      | shop.packandwander's instagram image | Party ready 🎉 with personalized zip top favor bags!                                                      |
      | harah1998's instagram image      | Let's customize some dog tags using cricut #dogtags #cooldogtags                                          |

    When I click the "Previous" arrow button in the "See what's possible with Cricut" carousel
    Then I should see the initial 4 items load again