package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PLPpage;

@Test(groups = {"regression","sanity"})
public class TC009_VerifyPLPFreeShippingFeeFilter extends baseClass
{

	public void VerifyPLPFreeShippingFeeFilter() throws InterruptedException
	{
		HomePage home=new HomePage(getDriver());
		home.header.performSearch(p.getProperty("searchKeyword"));
		PLPpage plp=new PLPpage(getDriver());
		Thread.sleep(5000);
		plp.isPLPLoaded();
		plp.enableFreeShippingFeeToggle();
		Thread.sleep(5000);
		int searchResultCount=plp.getSearchResultCount();
		int productCount=plp.getTotalProductCount();
		System.out.println("After Applying filter, Total Search result count is -> "+searchResultCount);
		System.out.println("After Applying filter, Product Count is -> "+productCount);
		Assert.assertEquals(searchResultCount,productCount);
	}
}
