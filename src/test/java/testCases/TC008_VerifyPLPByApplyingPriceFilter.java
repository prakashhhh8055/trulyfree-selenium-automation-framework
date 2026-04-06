package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PLPpage;

@Test(groups = {"regression","sanity"})
public class TC008_VerifyPLPByApplyingPriceFilter extends baseClass
{
	
	public void VerifyPLPByApplyingPriceFilter() throws InterruptedException
	{
		HomePage home=new HomePage(getDriver());
		PLPpage plp=home.header.performSearch(p.getProperty("searchKeyword"));
		plp.isPLPLoaded();
		Thread.sleep(5000);
		plp.applyPriceSlider();
		Thread.sleep(5000);
	}
	

}
