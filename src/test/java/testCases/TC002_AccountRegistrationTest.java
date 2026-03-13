package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import components.CompleteProfileViaPhoneNum;
import components.LoginPopup;
import components.SignupPopup;
import pageObjects.HomePage;

public class TC002_AccountRegistrationTest extends baseClass
{
	
	@Test(groups= {"regression","registration"})
	public void verifyAccountRegistration() throws InterruptedException
	{
		logger.info("---Account Registration Test Case Started----");
		HomePage home=new HomePage(driver);
		home.header.openLogin();
		LoginPopup login=new LoginPopup(driver);
		login.clickCreateAccount();
		SignupPopup signup=new SignupPopup(driver);
		signup.SwitchtoLoginViaEmail();
		signup.setEmailid(randomString()+"@yopmail.com");
		signup.setPassword(randomAlphaNumeric());
		signup.clickContinuebtn();
		CompleteProfileViaPhoneNum laststep=new CompleteProfileViaPhoneNum(driver);
		laststep.setFirstName(randomString());
		laststep.setLastName(randomString());
		laststep.clickCountryCode();
		laststep.selectCountryByDialCode("91");
		laststep.setPhoneNum(randomNumbers());
		laststep.clickCreateAccount();
		Thread.sleep(10000);
		logger.info("Resetting application state for next iteration");
	    driver.navigate().refresh();
	    logger.info("Verifying the Profile Info Present and Signup Completed or Not");
	    Assert.assertTrue(home.header.IsProfileTxtPresent());
	    logger.info("---Account Registration Test Case Completed----");
		
	}


}
