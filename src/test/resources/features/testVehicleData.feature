Feature: Compare vehicle data from excel with live data

  In order to verify vehicle data
  as a user
  I want to go to DVLA website and check each vehicle

  Scenario Outline: :
    Given I go to "DVLA_URL" on "Chrome"
    And I click on "startButtonHref"
    And I enter "<vehicleRegistration>"
    And I click "continueButton"
    And the "VehicleMake" and "Colour" match from Input File
    Then I select "yesButton" click on "continueButton"
    And All data from excel match

  Examples:
    | vehicleRegistration|
    |VFZ1344             |
    |GK12YRF             |
    |LY15ULA             |
    |DV06 HBZ            |
    |HY66 WUT            |







