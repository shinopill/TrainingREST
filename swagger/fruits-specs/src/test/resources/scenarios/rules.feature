Feature: Managing rules

  Background:
    Given there is a rules server

  Scenario: Create a rule
    Given I have a rule payload
    When I POST it to the /rules endpoint
    Then I receive a 202 status code from /rules endpoint

  Scenario: Create an already existing rule
    Given I have a rule payload
    When I POST it to the /rules endpoint
    Then I receive a 400 status code from /rules endpoint

  Scenario: Check the rule has been created
    Given I have a rule payload
    When I GET it to the /rules/ruleName endpoint
    Then I receive a 202 status code from /rules endpoint
    And response body should contain the asked rule data

  Scenario: Get rules for my application
    Given I have an API key
    When I GET it to the /rules endpoint
    Then I receive a 200 status code from /rules endpoint
    And Response Body should contain rules data

  Scenario: Delete a rule
    Given I have a rule payload
    When I DELETE it to the /rules endpoint
    Then I receive a 202 status code from /rules endpoint

  Scenario: Get rules application
    Given I have an API key
    When I GET it to the /rules endpoint
    Then I receive a 200 status code from /rules endpoint
    And Response Body should contain a rules array

