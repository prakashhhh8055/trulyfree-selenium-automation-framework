package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PLPpage;

@Test(groups={"sanity","regression"})
public class TC007_VerifyBrokenLinksFromPLP extends baseClass
{
	public void VerifyBrokenLinksFromPLP() throws InterruptedException, IOException
	{
		HomePage home=new HomePage(getDriver());
		PLPpage plp=home.header.performSearch(p.getProperty("searchKeyword"));
		int count=plp.getTotalProductCount();
		System.out.println(count+" Products count");
		int brokenLinks = plp.verifyBrokenLinks();
		Assert.assertEquals(brokenLinks,0);
	}
			
}

