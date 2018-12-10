Feature: Managing badges

   Background:
      Given there is a Gamification server
      
   Scenario: Create a badge
      Given I have a badge payload
      When I POST it to the /badge/appId endpoint
      Then I receive a 201 status code

   Scenario: Create an already existing badge   
      Given I have a badge payload
      When I POST it to the /badge/appId endpoint
      Then I receive a 201 status code
      When I POST it to the /badge/appId endpoint
      Then I receive a 403 status code

   Scenario: Check the badge has been created
      Given I have a badge payload
      When I GET it to the /badge/appId/badgeId endpoint
      Then I receive a 200 status code 
      And response body should contain the asked badge data

   Scenario: Get badges for my application
      Given I have a badge payload
      When I GET all badges to the /badge/appId endpoint 
      Then I receive a 200 status code
      And Response Body should contain badges data

   Scenario: Modify a badge
      Given I have a badge payload
      When I POST it to the /badges/appId/badgeId endpoint
      Then I receive a 201 status code 
