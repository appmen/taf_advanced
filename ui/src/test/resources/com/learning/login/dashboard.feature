Feature: Dashboards

  Background: I log in to RP with correct user
    Given I attempt to login with user "advanced" and "Qwerty123456"

  Scenario: Display correct dashboard after login
    When I see a Dashboards page
    Then I see a dashboard with name "DEMO DASHBOARD"