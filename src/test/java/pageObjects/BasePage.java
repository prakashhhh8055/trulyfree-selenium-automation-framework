package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import components.HeaderComponent;

public class BasePage {
	
	WebDriver driver;
    public HeaderComponent header;
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		header = new HeaderComponent(driver);
	}
	


}
