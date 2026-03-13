package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import components.LogInViaEmailPopup;
import components.LoginPopup;
import pageObjects.HomePage;

public class LoginAndLogoutTest extends baseClass
{

    @Test(groups={"regression","login"})
    public void verifyLoginAndLogout()
    {
        logger.info("********** Test Started : Verify Login And Logout **********");

        HomePage home = new HomePage(driver);
        
        logger.info("Clicking on Login icon");
        home.header.openLogin();

        LoginPopup loginViaPhone = new LoginPopup(driver);
        
        logger.info("Selecting 'Login via Email' option");
        loginViaPhone.clickLogInViaEmail();

        LogInViaEmailPopup loginViaEmail = new LogInViaEmailPopup(driver);

        logger.info("Entering Email");
        loginViaEmail.setEmail(p.getProperty("email"));

        logger.info("Entering Password");
        loginViaEmail.setPassword(p.getProperty("password"));

        logger.info("Clicking Continue button");
        loginViaEmail.clickContinue();

        logger.info("Verifying whether Profile text is displayed after login");
        
        boolean status=home.header.IsProfileTxtPresent();
        
        if(status==true)
        {
        	logger.info("Login successful - Profile text is visible");
        	logger.info("Hovering over profile and clicking Logout");
            home.header.hoverAndSelectLogout();
            logger.info("********** Test Finished : Verify Login And Logout **********");
            Assert.assertTrue(true);
        }
        else
        {
        	logger.info("Login Failed - Profile text is NOT visible");
        	logger.info("Clicking onb Cross icon to Close Login Via Email Popup");
        	loginViaEmail.clickCrossIcon();
        	Assert.fail();
        }
        
    }
}