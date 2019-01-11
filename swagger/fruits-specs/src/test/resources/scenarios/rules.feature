Feature: Managing rules

  Background:
    Given there is a gamification server

  Scenario: Post a rule
    Given I have a rule payload
    When I POST it to the /rules payload endpoint
    Then I receive a 201 status code

  Scenario: Post an existing rule
    Given I have a rule payload
    When I POST it to the /rules payload endpoint
    Then I receive a 201 status code
    When I POST it to the /rules payload endpoint
    Then I receive a 400 status code

  Scenario: Get rules application
    Given I have an API key
    When I GET it to the /rules endpoint
    Then I receive a 200 status code
    And Response Body should contain a rules array

