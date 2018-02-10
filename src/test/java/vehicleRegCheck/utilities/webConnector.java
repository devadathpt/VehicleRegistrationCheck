package vehicleRegCheck.utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import vehicleRegCheck.model.Vehicle;

public class webConnector {

    public Properties OR = null;
    public Properties CONFIG = null;
    public WebDriver driver = null;
    public String[]  vehicleArr;
    public Vehicle v = null;

    public webConnector()
    {
        if(OR==null){
            try{

                OR = new Properties();
                FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//vehicleRegCheck//config//OR.properties");
                OR.load(fs);

                CONFIG = new Properties();
                fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//vehicleRegCheck//config//config.properties");
                CONFIG.load(fs);

                setData();

            }
            catch(Exception e){
                System.out.println("Error on initializing properties files");

            }

        }

    }

    public void openBrowser(String browserType)
    {
        if(browserType.equals("Mozilla")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
            driver = new FirefoxDriver();
        }else if (browserType.equals("Chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("chrome.switches","--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling");
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");

            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();

        // implicit wait ( time for driver to wait if an element is not present)
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    public void navigate(String URL)
    {
        driver.get(CONFIG.getProperty(URL));
    }

    public void clickbyhref(String ObjectName)
    {
        driver.findElement(By.xpath(OR.getProperty(ObjectName))).click();

    }

    public void click(String objectName)
    {
        driver.findElement(By.name(OR.getProperty(objectName))).click();
    }

    public void type(String objectName,String text)

    {
        driver.findElement(By.id(OR.getProperty(objectName))).sendKeys((text));
    }
    public void select(String objectName,String text)
    {
        driver.findElement(By.id(OR.getProperty(objectName))).sendKeys(text);

    }

    public String getText(String objectName)
    {
       WebElement e= driver.findElement(By.xpath(OR.getProperty(objectName)));
        String text = e.getText();
        return text;
    }
    public boolean isElementPresent(String  objectName)
    {
        int count = driver.findElements(By.xpath(OR.getProperty(objectName))).size();
        if(count==0)
            return false;
        else
            return true;

    }

    public void setWait(int secs)
    {
        driver.manage().timeouts().implicitlyWait(secs, TimeUnit.SECONDS);
    }

    public void setData() throws IOException
    {
        String[]  vehicleArr = new String[getVehicleDataFromExcel.getData().size()];
        vehicleArr = getVehicleDataFromExcel.getData().get(1).toArray(vehicleArr);
        ArrayList<Vehicle> VehicleList = new ArrayList<>();
        v = new Vehicle();
        v.setVehicle_Registration(vehicleArr[0]);
        v.setVehicle_make(vehicleArr[1]);
        v.setDate_of_first_registration(vehicleArr[2]);
        v.setYear_of_manufacture(vehicleArr[3]);
        v.setCylinder_capacity(vehicleArr[4]);
        v.setCO2Emissions(vehicleArr[5]);
        v.setFuel_type(vehicleArr[6]);
        v.setExport_marker(vehicleArr[7]);
        v.setVehicle_status(vehicleArr[8]);
        v.setVehicle_colour(vehicleArr[9]);
        v.setVehicle_type_approval(vehicleArr[10]);
        v.setWheelplan(vehicleArr[11]);
        v.setRevenue_weight(vehicleArr[12]);
    }

}
