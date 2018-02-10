package vehicleRegCheck.vehicleDataCheck;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features = {"classpath:features/testVehicleData.feature"},

        monochrome = true,
        plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json" })
public class VehicleRunner {

}
