package vehicleRegCheck.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import vehicleRegCheck.model.Vehicle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class webConnector {

    public Properties OR = null;
    public Properties CONFIG = null;
    public WebDriver driver = null;
    public String[] vehicleArr;
    public Vehicle v = null;

    public webConnector() {
        if (OR == null) {
            try {

                OR = new Properties();
                FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//vehicleRegCheck//config//OR.properties");
                OR.load(fs);

                CONFIG = new Properties();
                fs = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//vehicleRegCheck//config//config.properties");
                CONFIG.load(fs);

            } catch (Exception e) {
                System.out.println("Error on initializing properties files");

            }

        }

    }

    public void openBrowser(String browserType) {
        if (browserType.equals("Mozilla")) {

            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "geckodriver");
            driver = new FirefoxDriver();
        } else if (browserType.equals("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("chrome.switches", "--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");

            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();

        // implicit wait ( time for driver to wait if an element is not present)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    public void navigate(String URL) {
        driver.get(CONFIG.getProperty(URL));
    }

    public void clickbyhref(String ObjectName) {
        driver.findElement(By.xpath(OR.getProperty(ObjectName))).click();

    }

    public void clickByName(String objectName) {
        driver.findElement(By.name(OR.getProperty(objectName))).click();
    }

    public void clickById(String objectName) {
        driver.findElement(By.cssSelector(OR.getProperty(objectName))).click();
    }

    public void provideRegistrationNumber(String text)

    {
        driver.findElement(By.id(OR.getProperty("vehicleReg"))).sendKeys((text));
    }

    public void select(String objectName, String text) {
        driver.findElement(By.id(OR.getProperty(objectName))).sendKeys(text);

    }

    public String getText(String objectName) {
        WebElement e = driver.findElement(By.xpath(OR.getProperty(objectName)));
        String text = e.getText();
        return text;
    }

    public boolean isElementPresent(String objectName) {
        int count = driver.findElements(By.xpath(OR.getProperty(objectName))).size();
        if (count == 0)
            return false;
        else
            return true;

    }

    public void setWait(int secs) {
        driver.manage().timeouts().implicitlyWait(secs, TimeUnit.SECONDS);
    }

    public Vehicle getExpectedVehicleData(final String regNumber) throws IOException {

        Vehicle v = new Vehicle();
        List<String> details = VehicleDataFromExcel.getData().get(regNumber);

        v.setVehicle_Registration(regNumber);
        v.setVehicle_make(details.get(0));
        v.setDate_of_first_registration(details.get(1));
        v.setYear_of_manufacture(details.get(2));
        v.setCylinder_capacity(details.get(3));
        v.setCO2Emissions(details.get(4));
        v.setFuel_type(details.get(5));
        v.setExport_marker(details.get(6));
        v.setVehicle_status(details.get(7));
        v.setVehicle_colour(details.get(8));
        v.setVehicle_type_approval(details.get(9));
        v.setWheelplan(details.get(10));
        v.setRevenue_weight(details.get(11));

        return v;
    }

    public Vehicle getActualVehicleDetails() {
        Vehicle v = new Vehicle();

        v.setVehicle_Registration("");
        v.setVehicle_make(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"),1)));
        v.setDate_of_first_registration(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 2)));
        v.setYear_of_manufacture(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 3)));
        v.setCylinder_capacity(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 4)));
        v.setCO2Emissions(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 5)));
        v.setFuel_type(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 6)));
        v.setExport_marker(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 7)));
        v.setVehicle_status(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 8)));
        v.setVehicle_colour(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 9)));
        v.setVehicle_type_approval(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 10)));
        v.setWheelplan(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 11)));
        v.setRevenue_weight(getTextFromDriver(String.format(OR.getProperty("value_FirstPart"), 12)));
        return v;
    }

    private String getTextFromDriver(final String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }
}

