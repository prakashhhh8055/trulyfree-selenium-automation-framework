package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OTPVerificationPopup {
	
	WebDriver driver;

	public OTPVerificationPopup(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@aria-label='Please enter verification code. Digit 1']") WebElement otpInput;
	@FindBy(xpath="//span[@class='jsx-1027105241 ']") WebElement resendBtn;
	@FindBy(xpath="//*[contains(text(),'Verification code sent')]") WebElement confirmationMsg;
	
	public void setOTP(String otp)
	{
		otpInput.sendKeys(otp);
	}
	
	public void clickResend()
	{
		resendBtn.click();
	}
	
	public String getconfirmationMsg()
	{
		try 
		{
			return confirmationMsg.getText();
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}

}
