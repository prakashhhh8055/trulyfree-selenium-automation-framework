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
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Logger logger;
    public Properties p;

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass(groups={"sanity","login","regression","ddt","registration"})
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws IOException {

        // Load config.properties
        FileReader file = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        String env = p.getProperty("execution_env");

        // ================= REMOTE EXECUTION =================
        if(env.equalsIgnoreCase("remote")) {

            switch(browser.toLowerCase()) {

                case "chrome":

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-infobars");
                    options.setPlatformName(os);

                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("profile.password_manager_leak_detection", false);

                    options.setExperimentalOption("prefs", prefs);

                    driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
                    break;

                case "firefox":

                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setPlatformName(os);

                    driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions));
                    break;

                case "edge":

                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setPlatformName(os);

                    driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), edgeOptions));
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browser: " + browser);
            }
        }

        // ================= LOCAL EXECUTION =================
        else if(env.equalsIgnoreCase("local")) {

            switch(browser.toLowerCase()) {

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

                    driver.set(new ChromeDriver(options));
                    break;

                case "edge":

                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--disable-notifications");

                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browser name: " + browser);
            }
        }

        logger.info("Launching browser: " + browser);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().deleteAllCookies();
        getDriver().get(p.getProperty("url"));
        getDriver().manage().window().setSize(new Dimension(1920,1080));
    }

    // ================= TEARDOWN =================
    @AfterClass(groups={"sanity","login","regression","ddt","registration"})
    public void tearDown() {

        if(getDriver() != null) {
            getDriver().quit();
            driver.remove(); // VERY IMPORTANT
        }
    }

    // ================= UTIL METHODS =================
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumbers() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return randomString() + "@" + randomNumbers();
    }

    // ================= SCREENSHOT =================
    public String captureScreen(String tname) {

        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        String targetPath = System.getProperty("user.dir") + "/screenshots/" + tname + "-" + timeStamp + ".jpg";
        File targetFile = new File(targetPath);

        sourceFile.renameTo(targetFile);

        return targetPath;
    }
}



/*  if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
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
	
	//driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
	driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap));
	
 } */