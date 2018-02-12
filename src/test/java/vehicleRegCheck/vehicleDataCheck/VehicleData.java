package vehicleRegCheck.vehicleDataCheck;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import vehicleRegCheck.model.Vehicle;
import vehicleRegCheck.utilities.webConnector;
import org.apache.logging.log4j.*;



import java.io.IOException;

public class VehicleData {

    webConnector selenium = new webConnector();
    String vehicleRegistration;
    Vehicle expectedVehicle;

    private static final Logger logger = LogManager.getLogger(VehicleData.class);




    @Given("^I go to \"(.*?)\" on \"(.*?)\"$")
    public void i_go_to_on(String url, String browser) {
        logger.info("Opening Browser");
        logger.info("               ");
        selenium.openBrowser(browser);
        selenium.navigate(url);

    }

    @And("^I click on \"(.*?)\"$")
    public void i_click_on(String object)  {
        logger.info("Clicking on " + object );
        logger.info("                      ");

        selenium.clickbyhref(object);
    }

    @And("^I enter \"(.*?)\"$")
    public void i_enter_as(String vehicleRegistration) throws IOException {
        logger.info("Entering Vehicle       " + vehicleRegistration );
        logger.info("                       ");

        this.vehicleRegistration = vehicleRegistration;
        selenium.provideRegistrationNumber(vehicleRegistration);


    }

    @And("^I click \"(.*?)\"$")
    public void i_select_click_on(String object) {
        logger.info("Clicking on      " + object );
        logger.info("                           ");
        selenium.clickByName(object);
    }

    @And("^the \"(.*?)\" and \"(.*?)\" match from Input File$")
    public void the_Make_and_Colour_match(String VehicleMake, String Colour) throws IOException {
        expectedVehicle = selenium.getExpectedVehicleData(this.vehicleRegistration);
        logger.info("Matching on      " + VehicleMake + " :  " +  Colour );
        logger.info("                           ");

        Assert.assertEquals("Make does not match",selenium.getText(VehicleMake),expectedVehicle.getVehicle_make());
        Assert.assertEquals("Colour does not match",selenium.getText(Colour),expectedVehicle.getVehicle_colour());
    }

    @And("^I select \"(.*?)\" click on \"(.*?)\"$")
    public void i_select_click_on(String yesButton, String object)  {
        logger.info("Clicking on      " + yesButton);
        logger.info("                           ");

        selenium.clickById(yesButton);
        selenium.clickByName(object);

    }

    @And("^All data from excel match$")
    public void all_data_from_excel_match() throws IOException {

        Vehicle actualVehicleDetails = selenium.getActualVehicleDetails();

        logger.info("Matching all Data      ");
        logger.info("                           ");


        Assert.assertEquals("Vehicle date of registration",actualVehicleDetails.getDate_of_first_registration(), expectedVehicle.getDate_of_first_registration());
        Assert.assertEquals("Vehicle Year of Manufacturing",actualVehicleDetails.getYear_of_manufacture(), expectedVehicle.getYear_of_manufacture());
        Assert.assertEquals("Vehicle Cylinder Capacity",actualVehicleDetails.getCylinder_capacity(),expectedVehicle.getCylinder_capacity());
        Assert.assertEquals("Vehicle CO2Emissionns",actualVehicleDetails.getCO2Emissions(),expectedVehicle.getCO2Emissions());
        Assert.assertEquals("Vehicle FuelType",actualVehicleDetails.getFuel_type(),expectedVehicle.getFuel_type());
        Assert.assertEquals("Vehicle Export Marker",actualVehicleDetails.getExport_marker(),expectedVehicle.getExport_marker());
        Assert.assertEquals("Vehicle status",actualVehicleDetails.getVehicle_status(),expectedVehicle.getVehicle_status());
        Assert.assertEquals("Vehicle Colour",actualVehicleDetails.getVehicle_colour(),expectedVehicle.getVehicle_colour());
        Assert.assertEquals("Vehicle Type approval",actualVehicleDetails.getVehicle_type_approval(),expectedVehicle.getVehicle_type_approval());
        Assert.assertEquals("Vehicle WheelPlan",actualVehicleDetails.getWheelplan(),expectedVehicle.getWheelplan());
        Assert.assertEquals("Vehicle Revenue Weight",actualVehicleDetails.getRevenue_weight(),expectedVehicle.getRevenue_weight());
        selenium.driver.quit();
        logger.info("Closing Browser");

    }

}
