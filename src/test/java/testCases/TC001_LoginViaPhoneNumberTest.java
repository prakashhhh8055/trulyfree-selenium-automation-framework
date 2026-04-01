package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import components.LoginPopup;
import components.OTPVerificationPopup;
import pageObjects.HomePage;

public class TC001_LoginViaPhoneNumberTest extends baseClass
{	
	@Test(groups= {"sanity","login"})
	public void verifyLogin() throws InterruptedException
	{
		
		try {
		logger.info("*******Starting TC001_Login Via Phone Number*******");
		HomePage home=new HomePage(getDriver());
		logger.info("*******Clicked and Opened the Login Popup*******");
		LoginPopup login=home.header.openLogin();
		logger.info("*******Entered the Phone Number*******");
		login.setPhoneNum(p.getProperty("phonenumber"));
		logger.info("*******Clicked on Continue Button*******");
		OTPVerificationPopup otp=login.clickContinue();
		logger.info("*******Getting Confirmation OTP Sent to your number*******");
		String confirmationMsg=otp.getconfirmationMsg();
		if(confirmationMsg.equalsIgnoreCase("Verification code sent to the phone number +1 -202-555-1234"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.assertTrue(false);
		}
		logger.debug("Confirmation msg Appeaes once OTP Sent to User -> "+confirmationMsg);
		logger.info("*******OTP Verification POPUP Opened*******");
		otp.setOTP(p.getProperty("otp"));
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*******Finished TC001 Completed Logged In Sucessfully*******");

	}
	
}
