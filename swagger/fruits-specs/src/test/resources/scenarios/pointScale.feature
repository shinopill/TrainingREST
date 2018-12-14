Feature: PointScale

   Background:
      Given there is a gamification server

   Scenario: Create pointscale
      Given I have a pointScale payload
      When I POST it to /pointScale endpoint
      Then I receive a 201 status code

   Scenario: get PointScale
      Given I have an API key
      When I GET it to the /pointScale/appId endpoint
      Then I receive a 200 status code 
      And response body should contain every PointScale data

   Scenario: Update user Points 
      Given I have a username, a pointScale payload and points
      When I POST it to the /pointscale/update/userpoints
      Then I receive 200 status code

   Scenario: update scale points
      Given I have a PointScale payload
      When I POST it to the /pointScale/update/scalePoints endpoint
      Then I receive a 200 status code

   Scenario: get userInfo
      Given I have pointScale payload and a username
      When I GET it to the /pointScale/user endpoint
      Then I receive a 200 status code
      And response body should contain user datas

   Scenario: get user points
      Given I have an API key
      When I GET it to the /pointscale/user/all endpoint
      Then I receive a 200 status code 
      And response body should contain every user and their points  
