Feature: pointscale

  Background:
    Given there is a pointscale server

  Scenario: Create pointscale
    Given I have a pointscale payload
    When I POST it to /pointScales endpoint
    Then I receive a 202 status code from /pointScales endpoint

  Scenario: Get a pointscale
    Given I have an API key and a pointscale name
    When I GET it to the /pointScales/"{pointscaleName}" endpoint
    Then I receive a 200 status code from /pointScales endpoint
    And response body should contain pointscale data


  Scenario: Create an existing pointscale
    Given I have a pointscale payload
    When I POST it to /pointScales endpoint
    Then I receive a 400 status code from /pointScales endpoint

  Scenario: Update a pointscale
    Given I have a pointscale payload
    When I PUT it to the /pointScales endpoint
    Then I receive a 202 status code from /pointScales endpoint


  # Ce scénario ne vas pas marcher car nous n'avons pas de endpoint pour créer des utilisateurs
  Scenario: Get a user's points
    Given I have an API key, a pointscale name and an username
    When I GET it to the /users/"<string>"/"<pointscaleName>" endpoint
    Then I receive a 200 status code from /pointScales endpoint

