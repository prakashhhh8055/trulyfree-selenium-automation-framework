package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPopup {

	WebDriver driver;

	public SignupPopup(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='regPhoneInput']") WebElement phoneNumInput;
	@FindBy(xpath="//div[@class='iti-arrow']") WebElement countryDropdownIcon;
	@FindBy(xpath="//button[text()='CONTINUE']") WebElement continueBtn;
	@FindBy(xpath="//button[normalize-space()='Create with Email Instead']") WebElement createAccViaEmailBtn;
	@FindBy(xpath="//span[@class='jsx-4142677818 login-link']") WebElement loginBtn;
	@FindBy(xpath="//input[@id='standard-email']") WebElement emailIDInput;
	@FindBy(xpath="//input[@id='standard-password']") WebElement pwdInput;
	@FindBy(xpath="//button[normalize-space()='Continue']") WebElement continueBtnForSignUpViaEmail;
	
	public void setPhoneNum(String phoneNum)
	{
		phoneNumInput.sendKeys(phoneNum);
	}
	
	public void clickContinue()
	{
		continueBtn.click();
	}
	
	public LoginPopup clickLogin()
	{
		loginBtn.click();
		return new LoginPopup(driver);
	}
	
	public void SwitchtoLoginViaEmail()
	{
		createAccViaEmailBtn.click();
	}
	
	public void setEmailid(String email)
	{
		emailIDInput.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		pwdInput.sendKeys(pwd);
	}
	
	public void clickContinuebtn()
	{
		continueBtnForSignUpViaEmail.click();
	}
	

}
