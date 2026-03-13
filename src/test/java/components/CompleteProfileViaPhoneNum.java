package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompleteProfileViaPhoneNum 
{
	
	WebDriver driver;
	
	public CompleteProfileViaPhoneNum(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[contains(@placeholder,'Enter first name')]") WebElement firstName;
	@FindBy(xpath="//input[@placeholder=' Enter last name']") WebElement lastName;
	@FindBy(xpath="//input[@id='signuplast']") WebElement phoneNum;
	@FindBy(xpath="//button[normalize-space()='Create Account']") WebElement createAccount;
	@FindBy(xpath="//div[@class='selected-flag']") WebElement countryCode;
	
	public void setFirstName(String firstname)
	{
		firstName.sendKeys(firstname);
	}
	
	public void setLastName(String lastname)
	{
		lastName.sendKeys(lastname);
	}
	
	public void clickCountryCode()
	{
		countryCode.click();
	}
	
	public void selectCountryByDialCode(String dialCode) 
	{
	    String xpath = "//li[@data-dial-code='"+dialCode+"']";
	    driver.findElement(By.xpath(xpath)).click();  //select Country Code xpath
	}
	
	public void setPhoneNum(String phoneNumber)
	{
		phoneNum.sendKeys(phoneNumber);
	}
	
	public void clickCreateAccount()
	{
		createAccount.click();
	}

}
