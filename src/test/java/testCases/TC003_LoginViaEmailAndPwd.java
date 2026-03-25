package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import components.LogInViaEmailPopup;
import components.LoginPopup;
import pageObjects.HomePage;

public class TC003_LoginViaEmailAndPwd extends baseClass
{
	@Test(groups={"sanity","login"})
	public void verifyLoginWithEmailAndPassword()
	{
		logger.info("=== Test Started : Login Via Email ===");

	        HomePage home = new HomePage(getDriver());
	        home.header.openLogin();
	        logger.info("Login popup opened");

	        LoginPopup loginViaPhone = new LoginPopup(getDriver());
	        loginViaPhone.clickLogInViaEmail();
	        logger.info("Clicked Login via Email");

	        LogInViaEmailPopup loginViaEmail = new LogInViaEmailPopup(getDriver());

	        loginViaEmail.setEmail(p.getProperty("email"));
	        logger.info("Entered Email");

	        loginViaEmail.setPassword(p.getProperty("password"));
	        logger.info("Entered Password");

	        loginViaEmail.clickContinue();
	        logger.info("Clicked Continue button");

	        boolean status = loginViaEmail.confirmationMsgDisplayed();

	        logger.info("Checking confirmation message");

	        if(status)
	        {
	            logger.info("Login successful : " + loginViaEmail.getconfirmationMsg());
	            logger.info("=== Test Completed Successfully ===");
	            Assert.assertTrue(true);
	        }
	        else
	        {
	            logger.error("Login failed: Invalid credentials");
	            Assert.fail("Login failed due to invalid credentials");
	        }
	
		
	}
	
	
}
