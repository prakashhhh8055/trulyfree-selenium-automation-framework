package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
		PLPpage PLP=home.header.performSearch(p.getProperty("searchKeyword"));
		System.out.println("Is PLP Loaded -> "+PLP.isPLPLoaded());
		int searchResultCount=PLP.getSearchResultCount();
		int productCardCount=PLP.getTotalProductCount();
		sa.assertEquals(productCardCount, searchResultCount);
		System.out.println("Search Result Count is - "+searchResultCount);
		System.out.println("PLP Product Cards Count is - "+productCardCount);
		sa.assertAll();
		logger.info("---TC006 Test case Completed----");
		
	}
	
	
	
	
	
	
}
