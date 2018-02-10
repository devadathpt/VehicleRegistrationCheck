package vehicleRegCheck.vehicleDataCheck;
import org.junit.Assert;
import vehicleRegCheck.model.Vehicle;
import vehicleRegCheck.utilities.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import vehicleRegCheck.utilities.webConnector.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class VehicleData {

    webConnector selenium = new webConnector();



    @Given("^I go to \"(.*?)\" on \"(.*?)\"$")
    public void i_go_to_on(String url, String browser) {
        selenium.openBrowser(browser);
        selenium.navigate(url);

    }

    @And("^I click on \"(.*?)\"$")
    public void i_click_on(String object)  {

        selenium.clickbyhref(object);
    }

    @And("^I enter \"(.*?)\" as \"(.*?)\"$")
    public void i_enter_as(String Vehicle_Registration, String object) throws IOException {



      Vehicle_Registration = selenium.v.getVehicle_Registration().toString();
      selenium.type(object,Vehicle_Registration);


    }

    @And("^I click \"(.*?)\"$")
    public void i_select_click_on(String object) {

        selenium.click(object);
    }

    @And("^the \"(.*?)\" and \"(.*?)\" match$")
    public void the_Make_and_Colour_match(String VehicleMake, String Colour) {

        Assert.assertEquals("Make does not match",selenium.getText(VehicleMake),selenium.v.getVehicle_make().toString());
        Assert.assertEquals("Colour does not match",selenium.getText(Colour),selenium.v.getVehicle_colour().toString());


    }

    @And("^I select \"(.*?)\" click on \"(.*?)\"$")
    public void i_select_click_on(String yesButton, String object)  {

     selenium.click(yesButton);
     selenium.click(object);

    }

    @And("^All data from excel match$")
    public void all_data_from_excel_match() {

        for (int i = 1; i <= 13; i++) {
            String object=(selenium.OR.getProperty("value_FirstPart")+i+(selenium.OR.getProperty("value_SecondPart")));
            System.out.println(selenium.OR.getProperty("value_FirstPart") + i + (selenium.OR.getProperty("value_SecondPart")));

            selenium.setWait(10);
            Assert.assertEquals("Make does not match", selenium.getText(object), selenium.vehicleArr[i - 1].toString());

        }
    }
}
