Feature: Login

  Scenario: Test login with correct credentials
    Given I attempt to login with user "advanced" and "Qwerty123456"
    Then I see a Dashboards page

  Scenario Outline: Test login with incorrect credentials
    Given I attempt to login with user "<username>" and "<password>"
    When I wait until user is redirected to the Dashboard page
    Then Im still on login page
    Examples:
      | username     | password |
      | 1            | 1        |
      | advanced     | 1        |
      | 1            | advanced |
      | Qwerty123456 | 1        |