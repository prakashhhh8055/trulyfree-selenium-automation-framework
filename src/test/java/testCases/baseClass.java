package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups={"sanity","login","regression","ddt","registration"})
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws InterruptedException, IOException 
    {
    	
    	//loading config.peroperties file
    	FileReader file=new FileReader("C:\\Users\\Prakash\\eclipse-workspace\\trulyfreeV1\\src\\test\\resources\\config.properties");
    	p=new Properties();
    	p.load(file);
    	
        logger = LogManager.getLogger(this.getClass());
        logger.info("Launching browser: " + browser);

        switch (browser.toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);

                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
                break;

            case "edge":

                System.setProperty("webdriver.edge.driver", "C:\\Drivers\\msedgedriver.exe");
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                throw new IllegalArgumentException("Invalid browser name: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.get(p.getProperty("url"));
        driver.manage().window().setSize(new Dimension(1920,1080));
        //driver.manage().window().maximize();

    }
    @AfterClass(groups={"sanity","login","regression","ddt","registration"})
    public void tearDown() {

    	if(driver != null)
        {
            driver.quit();
        }
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumbers() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        String generatedNum = RandomStringUtils.randomNumeric(10);
        return (generatedString + "@" + generatedNum);
    }
    
    public String captureScreen(String tname)
    {
    	SimpleDateFormat df=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    	Date dt=new Date();
    	String currentTimeStamp=df.format(dt);
    	
    	TakesScreenshot takescreenshot=(TakesScreenshot) driver;
    	File sourceFile=takescreenshot.getScreenshotAs(OutputType.FILE);
    	
    	String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"-"+currentTimeStamp+".jpg";
    	File targetFile=new File(targetFilePath);
    	
    	sourceFile.renameTo(targetFile);
    	
    	return targetFilePath;
    }
}