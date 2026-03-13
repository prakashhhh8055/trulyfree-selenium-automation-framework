package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPopup {
	
	WebDriver driver;
	public LoginPopup(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='loginPhoneInput']") WebElement phoneNumInput;
	@FindBy(xpath="//div[@class='iti-arrow']") WebElement countryDropdownIcon;
	@FindBy(xpath="//button[text()='Continue']") WebElement continueBtn;
	@FindBy(xpath="//button[normalize-space()='Create Account']") WebElement createAccBtn;
	@FindBy(xpath="//button[text()='Log In with Email']") WebElement LogInViaEmail;
	

	public void setPhoneNum(String phoneNum)
	{
		phoneNumInput.sendKeys(phoneNum);
	}
	
	public void clickContinue()
	{
		continueBtn.click();
	}
	
	public SignupPopup clickCreateAccount()
	{
		createAccBtn.click();
		return new SignupPopup(driver);
	}
	
	public LogInViaEmailPopup clickLogInViaEmail()
	{
		LogInViaEmail.click();
		return new LogInViaEmailPopup(driver);
	}
}
