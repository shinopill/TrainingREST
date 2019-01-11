Feature: Managing rules

  Background:
    Given there is a gamification server

  Scenario: Create a rule
    Given I have a rule payload
    When I POST it to the /rules endpoint
    Then I receive a 201 status code

  Scenario: Create an already existing rule
    Given I have a rule payload
    When I POST it to the /rules endpoint
    Then I receive a 201 status code
    When I POST it to the /rules endpoint
    Then I receive a 400 status code

  Scenario: Check the rule has been created
    Given I have a rule payload
    When I GET it to the /rules/ruleName endpoint
    Then I receive a 200 status code
    And response body should contain the asked rule data

  Scenario: Get rules for my application
    Given I have an API key
    When I GET it to the /rules endpoint
    Then I receive a 200 status code
    And Response Body should contain rules data

  Scenario: Modify a rule
    Given I have a rule payload
    When I PUT it to the /rules/ endpoint
    Then I receive a 201 status code

  Scenario: Delete a rule
    Given I have a rule payload
    When I DELETE it to the /rules endpoint
    Then I receive a 201 status code