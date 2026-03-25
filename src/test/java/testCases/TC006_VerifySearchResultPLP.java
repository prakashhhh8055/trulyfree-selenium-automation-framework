package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.PLPpage;


@Test(groups={"sanity","regression"})
public class TC006_VerifySearchResultPLP extends baseClass
{
	SoftAssert sa = new SoftAssert();
	public void VerifySearchResultPLP() throws InterruptedException
	{
		logger.info("---TC006 Test case Started----");
		HomePage home=new HomePage(getDriver());
		home.header.performSearch(p.getProperty("searchKeyword"));
		PLPpage PLP=new PLPpage(getDriver());
		System.out.println("Is PLP Loaded -> "+PLP.isPLPLoaded());
		int searchResultCount=PLP.getSearchResultCount();
		int productCardCount=PLP.getTotalProductCount();
		sa.assertEquals(productCardCount, searchResultCount);
		System.out.println("Search Result Count is - "+searchResultCount);
		System.out.println("PLP Product Cards Count is - "+productCardCount);
		Thread.sleep(5000);
		/*PLP.selectFilter(p.getProperty("SelectBrandFilterOption"));
		Thread.sleep(5000);
		int actualProductCount=PLP.getTotalProductCount();
		int expectedProductCardCount=PLP.getSelectedFilterCount();
		System.out.println("After Applying Filter - Actual Product Count is -> "+actualProductCount+" Expected Product Count is -> "+expectedProductCardCount);
		sa.assertEquals(actualProductCount,expectedProductCardCount);
		sa.assertTrue(PLP.isPLPLoaded());
		//PLP.clickProductByName(p.getProperty("productTitle")); */
		
		sa.assertEquals(PLP.verifyBrandFilter(p.getProperty("SelectBrandFilterOption")),true);
		PLP.ClearFilter();
		Thread.sleep(2000);
		sa.assertAll();
		
		logger.info("---TC006 Test case Completed----");
		
	}
	
	
	
	
	
	
}
