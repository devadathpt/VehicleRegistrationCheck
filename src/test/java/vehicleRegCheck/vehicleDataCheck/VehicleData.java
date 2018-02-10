package vehicleRegCheck.vehicleDataCheck;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import vehicleRegCheck.model.Vehicle;
import vehicleRegCheck.utilities.webConnector;

import java.io.IOException;

public class VehicleData {

    webConnector selenium = new webConnector();
    String vehicleRegistration;
    Vehicle expectedVehicle;



    @Given("^I go to \"(.*?)\" on \"(.*?)\"$")
    public void i_go_to_on(String url, String browser) {
        selenium.openBrowser(browser);
        selenium.navigate(url);

    }

    @And("^I click on \"(.*?)\"$")
    public void i_click_on(String object)  {
        selenium.clickbyhref(object);
    }

    @And("^I enter \"(.*?)\"$")
    public void i_enter_as(String vehicleRegistration) throws IOException {

        this.vehicleRegistration = vehicleRegistration;
        selenium.provideRegistrationNumber(vehicleRegistration);


    }

    @And("^I click \"(.*?)\"$")
    public void i_select_click_on(String object) {

        selenium.clickByName(object);
    }

    @And("^the \"(.*?)\" and \"(.*?)\" match from Input File$")
    public void the_Make_and_Colour_match(String VehicleMake, String Colour) throws IOException {
        expectedVehicle = selenium.getExpectedVehicleData(this.vehicleRegistration);

        Assert.assertEquals("Make does not match",selenium.getText(VehicleMake),expectedVehicle.getVehicle_make());
        Assert.assertEquals("Colour does not match",selenium.getText(Colour),expectedVehicle.getVehicle_colour());
    }

    @And("^I select \"(.*?)\" click on \"(.*?)\"$")
    public void i_select_click_on(String yesButton, String object)  {

        selenium.clickById(yesButton);
        selenium.clickByName(object);

    }

    @And("^All data from excel match$")
    public void all_data_from_excel_match() throws IOException {

        Vehicle actualVehicleDetails = selenium.getActualVehicleDetails();

        Assert.assertEquals("Vehicle data of registration",actualVehicleDetails.getDate_of_first_registration(), expectedVehicle.getDate_of_first_registration());
        Assert.assertEquals("Vehicle Year of Manufacturing",actualVehicleDetails.getYear_of_manufacture(), expectedVehicle.getYear_of_manufacture());
        selenium.driver.quit();
    }

}
