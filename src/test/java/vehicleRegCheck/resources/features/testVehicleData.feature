Feature: Compare vehicle data from excel with live data

  In order to verify vehicle data
  as a user
  I want to go to DVLA website and check each vehicle

  Scenario:
    Given I go to "DVLA_URL" on "Chrome"
    And I click on "startButtonHref"
    And I enter "Vehicle_Registration" as "vehicleReg"
    And I click "continueButton"
    And the "VehicleMake" and "Colour" match
    Then I select "yesButton" click on "continueButton"
    And All data from excel match





