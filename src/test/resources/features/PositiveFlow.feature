Feature: Positive flows for Credit Card Application and user journeys

  Background:
    Given the user opens the application

  Scenario: Successful registration with valid details
    When the user registers with valid details
    Then registration should be successful

  Scenario: Successful login with valid credentials
    Given a registered user exists
    When the user logs in with valid credentials
    Then login should succeed

  Scenario: Add book to cart (logged in user)
  Given I navigate to the nopcommerce page
  Then I see the nopcommerce home page
  When I click login option in the home page
  Then I should navigate to the login page
  When I enter valid username and password
  And I click login button
  Then I should navigate to the product page
  When I click Books under home page menu
  And I open "First Prize Pies" product page
  When I click add to cart on product page
  Then I should see the message as "The product has been added to your shopping cart"
  When I click Shopping cart option
  Then I see the message as "Shopping cart" page title