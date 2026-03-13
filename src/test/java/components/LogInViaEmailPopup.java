package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInViaEmailPopup {
	
	
	
	WebDriver driver;

	public LogInViaEmailPopup(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='standard-email']") WebElement emailID; 
	@FindBy(xpath="//input[@id='standard-password']") WebElement pwd;
	@FindBy(xpath="//button[text()='CONTINUE']") WebElement continueBtn;
	@FindBy(xpath="//span[@id='client-snackbar']") WebElement confirmMsg;
	@FindBy(xpath="//div[contains(@class,'cursor-pointer')]//*[name()='svg']") WebElement crossIcon;
	
	
	public void setEmail(String email)
	{
		emailID.sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		pwd.sendKeys(password);
	}
	
	public void clickContinue()
	{
		continueBtn.click();
	}
	
	public void clickCrossIcon()
	{
		crossIcon.click();
	}
	
	public Boolean confirmationMsgDisplayed()
	{
		try 
		{
			return confirmMsg.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public String getconfirmationMsg()
	{
		try 
		{
			return confirmMsg.getText();
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}

	
	
}
