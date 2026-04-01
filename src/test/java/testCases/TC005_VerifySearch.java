package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

@Test(groups={"sanity","regression"})
public class TC005_VerifySearch extends baseClass
{
	
	public void verifySearch() throws InterruptedException
	{
		logger.info("---TC005_VerifySearch Test case Started----");
		HomePage home=new HomePage(getDriver());
		home.header.performSearch("Dog Treats");
		Assert.assertTrue(getDriver().getCurrentUrl().contains("Treats"));
		logger.info("---TC005_VerifySearch Test case Completed----");
	}
	

}
