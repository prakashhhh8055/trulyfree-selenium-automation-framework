package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//h2[contains(@class,'cart_product_title')]") List<WebElement> actualProducts;
	@FindBy(xpath="//h2[contains(@class,'membership_in_cart_product_title')]") WebElement membershipTitle;
	@FindBy(xpath="//span[@class='jsx-1119138660 membership_in_cart_btn']") WebElement membershipAddBtn;
	@FindBy(xpath="//button[contains(@class,'proceed_checkout_btn')]") WebElement checkoutBtn;
	@FindBy(xpath="//h1[contains(text(),'Your Cart (')]") WebElement CartCount;
	
	
	public boolean isMembershipVisible() 
	{
		waitForVisibility(membershipTitle);
	    return membershipTitle.isDisplayed();
	}

	public boolean isMembershipAddButtonVisible() 
	{
		waitForVisibility(membershipAddBtn);
	    return membershipAddBtn.isDisplayed();
	}

	public boolean isCheckoutDisabled() 
	{
		waitForVisibility(checkoutBtn);
	    return checkoutBtn.isEnabled();
	}
	
	public int getCartCount()
	{
		waitForVisibility(CartCount);
		String text=CartCount.getText();
		String res=text.replaceAll("[^0-9]","");
		return Integer.parseInt(res);
	}
	
	public boolean isProductPresent(String expectedProductName) 
	{
        for (WebElement product : actualProducts) 
        {
        	waitForVisibility(product);
            if (product.getText().trim().equalsIgnoreCase(expectedProductName.trim())) 
            {
                return true;
            }
        }
        return false;
    }

}
