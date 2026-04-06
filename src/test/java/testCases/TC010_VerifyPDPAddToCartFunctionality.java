package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.PDPpage;
import pageObjects.PLPpage;

@Test(groups = {"regression","sanity"})
public class TC010_VerifyPDPAddToCartFunctionality extends baseClass 
{
	
	public void VerifyPDPAddToCartFunctionality() throws InterruptedException
	{
		HomePage home=new HomePage(getDriver());
		PLPpage plp=home.header.performSearch(p.getProperty("searchKeyword"));
		String ExpectedProduct= plp.getFirstProductName();
		PDPpage pdp=plp.clickProductByName(ExpectedProduct);
		pdp.clickAddToCartbtn();
		Thread.sleep(3000);
		HomePage home1=new HomePage(getDriver());
		CartPage cart=home1.header.openCart();
		Thread.sleep(3000);
		Assert.assertTrue(cart.isProductPresent(ExpectedProduct),"❌ Product not found in cart: " +ExpectedProduct);
		

		
		
	}
	

}
