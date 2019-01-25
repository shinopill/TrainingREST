Feature: Managing Events

  Background:
    Given there is an event server

  Scenario: receive an event
    Given I have an event payload
    When I POST it to the /events endpoint
    Then I receive a 201 status code from /events endpoint

  Scenario: an user win a badge and get some points
    Given I have a badge payload, a pointscale payload, an user and a rule that uses them
    When I post an event with the rule



