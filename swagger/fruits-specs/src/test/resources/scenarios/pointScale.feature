Feature: PointScale

  Background:
    Given there is a gamification server

  Scenario: Create pointscale
    Given I have a pointScale payload
    When I POST it to /pointScales endpoint
    Then I receive a 201 status code

  Scenario: Create an existing pointscale
    Given I have a pointScale payload
    When I POST it to /pointScales endpoint
    Then I receive a 201 status code
    When I POST it to /pointScales endpoint
    Then I receive a 400 status code

  Scenario: Update a pointscale
    Given I have a pointScale payload
    When I PUT it to the /pointScales endpoint
    Then I receive a 200 status code


  Scenario: Get a pointscale
    Given I have an API key and a pointScale name
    When I GET it to the /pointScales/"{pointScaleName}" endpoint
    Then I receive a 200 status code
    And response body should contain pointscale data

