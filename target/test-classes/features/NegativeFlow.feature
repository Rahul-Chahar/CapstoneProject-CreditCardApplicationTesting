Feature: Negative flows for Credit Card Application and user journeys

  Background:
    Given the user opens the application

  Scenario: Registration with already registered email
    When the user registers with an existing email
    Then the system should show an email already exists error

  Scenario: Login with invalid password
    Given a registered user exists
    When the user logs in with an incorrect password
    Then the system should show 'Login was unsuccessful'

  
