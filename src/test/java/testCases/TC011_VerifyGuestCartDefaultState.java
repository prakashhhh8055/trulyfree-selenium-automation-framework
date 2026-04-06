package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomePage;

@Test(groups = {"regression","sanity"})
public class TC011_VerifyGuestCartDefaultState extends baseClass 
{
	public void VerifyGuestCartDefaultState()
	{
		 CartPage cart = new HomePage(getDriver()).header.openCart();
		 Assert.assertTrue(cart.isMembershipVisible(), "Membership not visible");
		 Assert.assertTrue(cart.isMembershipAddButtonVisible(), "Add button missing");
		 Assert.assertTrue(cart.isCheckoutDisabled(), "Checkout should be disabled");
		 Assert.assertEquals(cart.getCartCount(),0, "Cart count should be 0");
	}
}
