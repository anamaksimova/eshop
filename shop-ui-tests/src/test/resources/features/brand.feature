Feature: Brand

  Scenario Outline: Successful Login to the page, adding new Brand, deleting new Brand and logout after
    Given I open web browser
    When I navigate to login.html page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    And I click on brand button
    Then I click on add brand button
    And I provide name as "<name>"
    And I click on submit button
    And I click on delete button to delete name as "<name>"
    And click logout button
    Then user logged out

    Examples:
      | username | password | name
      | admin | admin | apple