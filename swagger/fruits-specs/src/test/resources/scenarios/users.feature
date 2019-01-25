Feature: users

  #Il faut d'abord pouvoir insérer des utilisateurs pour tester ces scenarios
  Background:
    Given there is an event/rule/ server

    Scenario: Get users from an app
      Given I have an API key
      When I GET it to the /users endpoint
      Then I receive a 202 status code from /users endpoint
      And Response Body should contain an user array