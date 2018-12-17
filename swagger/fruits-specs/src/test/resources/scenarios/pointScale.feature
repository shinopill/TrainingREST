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

  Scenario: Delete a pointscale
    Given I have a pointscale payload
    When I DELETE it to the /pointscales endpoint
    Then I receive 201 status code

  Scenario: Delete a missing pointscale
    Given I have a pointscale payload
    When I DELETE it to the /pointscales endpoint
    Then I receive 201 status code
    When I DELETE it to the /pointscales endpoint
    Then I receive 400 status code

  Scenario: Get a pointscale
    Given I have an API key and a pointscale name
    When I GET it to the /pointScales/{pointScaleName} endpoint
    Then I receive a 200 status code
    And response body should contain pointscale data

  Scenario: Get users pointscale
    Given I have an API key, a pointscale name and an username
    When I GET it to the /pointscales/{pointScaleName}/{username} endpoint
    Then I receive a 200 status code
    And response body should contain an username and his points

  Scenario: Get all users for a pointscale and their points for this pointscale
    Given I have an API key and a pointscale name
    When I GET it to the /pointScales/{pointScaleName}/users endpoint
    Then I receive a 200 status code
    And response body should contain an users list and the points they have

