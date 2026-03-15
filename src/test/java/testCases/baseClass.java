package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {

    public  WebDriver driver;
	//public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public  Logger logger;
    public  Properties p;

    @BeforeClass(groups={"sanity","login","regression","ddt","registration"})
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws InterruptedException, IOException 
    {
    	
    	//loading config.peroperties file
    	FileReader file=new FileReader("C:\\Users\\Prakash\\eclipse-workspace\\trulyfreeV1\\src\\test\\resources\\config.properties");
    	p=new Properties();
    	p.load(file);
    	
        logger = LogManager.getLogger(this.getClass());
        
   /*     if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
        	DesiredCapabilities cap=new DesiredCapabilities();
        	
        	//Decide OS
        	if(os.equalsIgnoreCase("windows"))
        	{
        		cap.setPlatform(Platform.WIN11);
        	}
        	else if(os.equalsIgnoreCase("mac"))
        	{
        		cap.setPlatform(Platform.MAC);
        	}
        	else if(os.equalsIgnoreCase("linux"))
        	{
        		cap.setPlatform(Platform.LINUX);
        	}
        	else
        	{
        		System.out.println("No Matching OS");
        		return;
        	}      	
        	
        	//Decide Browser
        	switch (browser.toLowerCase()) 
        	{
        		case "chrome": cap.setBrowserName("chrome"); break;
	       
        		case "edge": cap.setBrowserName("MicrosoftEdge"); break;	               

        		case "firefox": cap.setBrowserName("firefox"); break;
	             
        		default: System.out.println("No matching browser"); return;      			
        	}
        	
        	driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
        	
         }  */
        
        
        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            switch(browser.toLowerCase())
            {
                case "chrome":

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-infobars");
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("profile.password_manager_leak_detection", false);

                    options.setExperimentalOption("prefs", prefs);

                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                    break;

                case "firefox":

                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
                    break;

                case "edge":

                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), edgeOptions);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browser: " + browser);
            }
        }
        
        
       logger.info("Launching browser: " + browser);

        if(p.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            switch (browser.toLowerCase()) 
            {

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


                default:
                    throw new IllegalArgumentException("Invalid browser name: " + browser);
            }
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.get(p.getProperty("url"));
        driver.manage().window().setSize(new Dimension(1920,1080));
        //driver.get().manage().window().maximize();
        
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