package testCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import components.LogInViaEmailPopup;
import components.LoginPopup;
import pageObjects.HomePage;
import utilities.DataProviders;

public class TC004_LoginViaEmailAndPasswordDDTTest extends baseClass
{
	
	@Test(dataProvider="TestData",dataProviderClass=DataProviders.class, groups={"regression","ddt"})
	public void verifyLoginViaEmailAndPasswordDDT(String email,String pwd,String exp)
	{
	    logger.info("********** Test Started : Login DDT **********");

	    HomePage home = new HomePage(getDriver());

	    logger.info("Clicking Login icon");
	    home.header.openLogin();

	    LoginPopup loginViaPhone = new LoginPopup(getDriver());

	    logger.info("Selecting Login via Email");
	    loginViaPhone.clickLogInViaEmail();

	    LogInViaEmailPopup loginViaEmail = new LogInViaEmailPopup(getDriver());

	    logger.info("Entering Email");
	    loginViaEmail.setEmail(email);

	    logger.info("Entering Password");
	    loginViaEmail.setPassword(pwd);

	    logger.info("Clicking Continue");
	    loginViaEmail.clickContinue();
	    
	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

	    boolean actualResult;
	    
	    try
	    {
	        actualResult = wait.until(driver -> home.header.IsProfileTxtPresent());
	    }
	    catch(Exception e)
	    {
	        actualResult = false;
	    }

	    if(exp.equalsIgnoreCase("Valid"))
	    {
	        if(actualResult)
	        {
	            logger.info("Login successful as expected");
	            home.header.hoverAndSelectLogout();
	        }
	        else
	        {
	            logger.error("Login failed but expected success");
	            try
	            {
	                loginViaEmail.clickCrossIcon();
	                logger.info("Login popup closed");
	            }
	            catch(Exception e)
	            {
	                logger.info("No popup found to close");
	            }
	            Assert.fail();
	        }
	    }
	    else if(exp.equalsIgnoreCase("Invalid"))
	    {
	        if(actualResult)
	        {
	            logger.error("Login succeeded but expected failure");
	            home.header.hoverAndSelectLogout();
	            Assert.fail();
	        }
	        else
	        {
	            logger.info("Login failed as expected");
	            try
	            {
	                loginViaEmail.clickCrossIcon();
	                logger.info("Login popup closed");
	            }
	            catch(Exception e)
	            {
	                logger.info("No popup found to close");
	            }
	        }
	    }

	    logger.info("Resetting application state for next iteration");
	    getDriver().navigate().refresh();

	    logger.info("********** Test Completed **********");
	}
	
}
