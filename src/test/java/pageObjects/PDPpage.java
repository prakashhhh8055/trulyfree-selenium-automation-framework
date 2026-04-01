package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PDPpage extends BasePage {

	public PDPpage(WebDriver driver) 
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//div[contains(@class,'fast_free_shipping_lbl ')]//span[contains(@class,'free_shipping_text')]" ) WebElement freeShippingLabel;
	@FindBy(xpath="//button[@id='add-to-cart-button']" ) WebElement addToCartBtn;
	@FindBy(xpath="//p[@data-testid='product-name']") WebElement productTitle;
	@FindBy(xpath="//div[@class='jsx-3500409674 detail_section']//a[@class='brand_name text-uppercase']") WebElement brandNameCta;
	@FindBy(xpath="//div[@class='react-select__control css-1d3tt3r-control']//div[@class='react-select__single-value css-1uccc91-singleValue']") WebElement quantityDropdown;
	
	public String getPdpTitle()
	{
		return productTitle.getText();
	}
	
	public String getBrandName()
	{
		return brandNameCta.getText();
	}
	
	public void clickBrandName()
	{
		brandNameCta.click();
	}
	
	public void clickAddToCartbtn()
	{
		addToCartBtn.click();
	}
	
	public void selectQuantity()
	{
		Actions action=new Actions(driver);
		action.moveToElement(quantityDropdown).click();
	}
	
	
}
