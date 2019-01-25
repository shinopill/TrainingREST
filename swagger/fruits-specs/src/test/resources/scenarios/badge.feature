Feature: Managing badges

  Background:
    Given there is a badge server

  Scenario: Create a badge
    Given I have a badge payload
    When I POST it to the /badges endpoint
    Then I receive a 202 status code from the /badges endpoint

  Scenario: Check the badge has been created
    Given I have a badge payload
    When I GET it to the /badges/{badgeName} endpoint
    Then I receive a 200 status code from the /badges endpoint
    And response body should contain the asked badge data

  Scenario: Create an already existing badge
    Given I have a badge payload
    When I POST it to the /badges endpoint
    Then I receive a 400 status code from the /badges endpoint

Scenario: Get badges for my application
    Given I have a badge payload
    When I GET all badges to the /badges endpoint
    Then I receive a 200 status code from the /badges endpoint
    And Response Body should contain badges data

  Scenario: Modify a badge
    Given I have a badge payload
    When I PUT it to the /badges/{badgeName} endpoint
    Then I receive a 202 status code from the /badges endpoint
