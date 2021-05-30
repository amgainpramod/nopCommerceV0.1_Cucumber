Feature: Login
  Scenario: Successful login with valid credentials
#    Given User launches Chrome Browser
#    When User Opens URL "http://admin-demo.nopcommerce.com/login"
    Given User is at login page
    When User enters Email as "admin@yourstore.com" and password as "admin"
    And Clicks on login
    Then Navigation Page title should be "Welcome to your store!"
    When User clicks on log out link
    Then login page title should be "Welcome, please sign in!"
#    And close browser

  Scenario Outline: Data Driven Login
#    Given User launches Chrome Browser
#    When User Opens URL "http://admin-demo.nopcommerce.com/login"
    Given User is at login page
    When User enters Email as "<email>" and password as "<password>"
    And Clicks on login
    Then Navigation Page title should be "Welcome to your store!"
    When User clicks on log out link
    Then login page title should be "Welcome, please sign in!"
#    And close browser
    Examples:
      | email | password |
      | admin@yourstore.com | admin |
      | admin1@yourstore.com | admin123 |