Feature: Managing Events

  Background:
    Given there is a gamification server

  Scenario: receive an event
    Given I have an event payload
    When I POST it to the /events payload
    Then I receive a 201 status code