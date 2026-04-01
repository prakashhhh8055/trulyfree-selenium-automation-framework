package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import components.CompleteProfileViaPhoneNum;
import components.LogInViaEmailPopup;
import components.LoginPopup;
import components.SignupPopup;
import pageObjects.HomePage;

public class TC002_AccountRegistrationTest extends baseClass
{
	
	@Test(groups= {"regression","registration"})
	public void verifyAccountRegistration() throws InterruptedException
	{
		logger.info("---Account Registration Test Case Started----");
	/*	HomePage home=new HomePage(getDriver());
		home.header.openLogin();
		LoginPopup login=new LoginPopup(getDriver());
		login.clickCreateAccount();
		SignupPopup signup=new SignupPopup(getDriver());
		signup.SwitchtoLoginViaEmail();
		signup.setEmailid(randomString()+"@yopmail.com");
		signup.setPassword(randomAlphaNumeric());
		signup.clickContinuebtn();
		CompleteProfileViaPhoneNum laststep=new CompleteProfileViaPhoneNum(getDriver());
		laststep.setFirstName(randomString());
		laststep.setLastName(randomString());
		laststep.clickCountryCode();
		laststep.selectCountryByDialCode("91");
		laststep.setPhoneNum(randomNumbers());
		laststep.clickCreateAccount();
		Thread.sleep(10000);
		logger.info("Resetting application state for next iteration");
		getDriver().navigate().refresh();
	    logger.info("Verifying the Profile Info Present and Signup Completed or Not");
	    Assert.assertTrue(home.header.IsProfileTxtPresent());
	    logger.info("---Account Registration Test Case Completed----"); */
		
		HomePage home=new HomePage(getDriver());
		LoginPopup login=home.header.openLogin();
		LogInViaEmailPopup loginViaEmail=login.clickLogInViaEmail();
		SignupPopup signup=loginViaEmail.clickCreateAccount();
		signup.setEmailid(randomString()+"@yopmail.com");
		signup.setPassword(randomAlphaNumeric());
		CompleteProfileViaPhoneNum laststep=signup.clickContinuebtn();
		laststep.setFirstName(randomString());
		laststep.setLastName(randomString());
		laststep.clickCountryCode();
		laststep.selectCountryByDialCode("1");
		laststep.setPhoneNum(randomNumbers());
		laststep.clickCreateAccount();
		Thread.sleep(10000);
		logger.info("Resetting application state for next iteration");
		getDriver().navigate().refresh();
	    logger.info("Verifying the Profile Info Present and Signup Completed or Not");
	    HomePage home1=new HomePage(getDriver());
	    Assert.assertTrue(home1.header.IsProfileTxtPresent());
	    logger.info("---Account Registration Test Case Completed----");
		
	}


}
