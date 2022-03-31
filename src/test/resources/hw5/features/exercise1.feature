Feature: Select different elements on the Different Elements Page

  Scenario: Select checkboxes, a radio button and an option in the dropdown list
    Given I open JDI GitHub site
    And I login as user "Roman Iovlev"
    And I click on "Service" button in Header
    And I click on "Different Elements" button in Service dropdown

    When I select checkboxes "Water, Wind" in the row of checkboxes on the Different Elements Page
    And I select radio button "Selen" in the row of radio buttons on the Different Elements Page
    And I select option "Yellow" in the dropdown list on the Different Elements Page

    Then log rows for checkboxes "Water, Wind" and checkboxes status "true" should be displayed in the logs panel on the Different Elements Page
    And the log row for radio button "Selen" should be displayed in the logs panel on the Different Elements Page
    And the log row for the dropdown option "Yellow" should be displayed in the logs panel on the Different Elements Page
