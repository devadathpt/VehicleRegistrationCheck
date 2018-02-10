package vehicleRegCheck.utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
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

    public List<Vehicle> getExpectedVehicleData() throws IOException
    {
        List<Vehicle> vehicleList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> integerListEntry : getVehicleDataFromExcel.getData().entrySet()) {

            v = new Vehicle();
            v.setVehicle_Registration(integerListEntry.getValue().get(0));
            v.setVehicle_make(integerListEntry.getValue().get(1));
            v.setDate_of_first_registration(integerListEntry.getValue().get(2));
            v.setYear_of_manufacture(integerListEntry.getValue().get(3));
            v.setCylinder_capacity(integerListEntry.getValue().get(4));
            v.setCO2Emissions(integerListEntry.getValue().get(5));
            v.setFuel_type(integerListEntry.getValue().get(6));
            v.setExport_marker(integerListEntry.getValue().get(7));
            v.setVehicle_status(integerListEntry.getValue().get(8));
            v.setVehicle_colour(integerListEntry.getValue().get(9));
            v.setVehicle_type_approval(integerListEntry.getValue().get(10));
            v.setWheelplan(integerListEntry.getValue().get(11));
            v.setRevenue_weight(integerListEntry.getValue().get(12));
            vehicleList.add(v);
        }

        return vehicleList;
    }

}
