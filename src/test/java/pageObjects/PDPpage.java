package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PDPpage extends BasePage {

	public PDPpage(WebDriver driver) 
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//div[contains(@class,'fast_free_shipping_lbl ')]//span[contains(@class,'free_shipping_text')]" ) WebElement freeShippingLabel;
	@FindBy(xpath="//button[@id='add-to-cart-button']" ) WebElement addToCartBtn;
	

}
