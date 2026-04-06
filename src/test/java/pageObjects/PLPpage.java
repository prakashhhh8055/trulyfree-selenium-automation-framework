package pageObjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PLPpage extends BasePage
{
	WebDriverWait wait;
	JavascriptExecutor js=(JavascriptExecutor) driver;
	Actions action=new Actions(driver);
	public PLPpage(WebDriver driver) 
	{
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(xpath="//h1[@class='mb-0 pdp_title']") WebElement SearchKeyword;
	@FindBy(xpath="//span[@class='ais-Stats-text']") WebElement SearchResult;
	@FindBy(xpath="//a[@data-testid='product-image']") List<WebElement> ProductCard;
	@FindBy(xpath="//h2[@class='productui__product_name']")  List<WebElement> ProductTitles;
	@FindBy(xpath="//a[@data-testid='product-image' and @href]") List<WebElement> productLinks;
	@FindBy(xpath="//div[contains(@class,'productui__member_section')]//p[contains(text(),'$')]") List<WebElement> MemberPrices;
	@FindBy(xpath="//div[contains(@class,'productui__nonmember_section')]//p[contains(text(),'$')]") List<WebElement> NonMemberPrices;
	@FindBy(xpath="//p[contains(@class,'productui__brand_name')]") List<WebElement> BrandNames;
	@FindBy(xpath="(//li[contains(@class,'ais-RefinementList-item--selected')]//span[@class='ais-RefinementList-count'])[2]") WebElement SelectedFilterCount;
	@FindBy(xpath="//div[@class='CurrentRefinements d-none d-md-block']//button[@class='ais-ClearRefinements-button']") WebElement ClearFilterBtn;
	@FindBy(xpath="(//div[@role='slider'])[3]") WebElement minSlider;
	@FindBy(xpath="(//div[@role='slider'])[4]") WebElement maxSlider;
	@FindBy(xpath="//input[@class='ais-ToggleRefinement-checkbox']") WebElement FreeShippingFeeToggle;
	
	
	public String getFirstProductName()
	{
		return ProductTitles.get(0).getText();
	}
	
	public void ClearFilter()
	{
		if(ClearFilterBtn.isDisplayed())
		{
			ClearFilterBtn.click();
		}
		else
		{
			System.out.println("Filters Not Applied");
		}
	}
	
	
	public boolean verifyBrandFilter(String brandName) throws InterruptedException
	{
		js.executeScript("window.scrollTo(0,0);");
		Thread.sleep(5000);
		By selectBrandloc=By.xpath("//div[contains(@class,'jsx-3943973772 ais-Panel-body mt-3')]//span[@class='ais-RefinementList-labelText'][normalize-space()='"+brandName+"']");
		WebElement brand=driver.findElement(selectBrandloc);
		js.executeScript( "arguments[0].scrollIntoView({block:'center'});",brand);
	    wait.until(ExpectedConditions.elementToBeClickable(brand));
	    brand.click();
	    
	    Thread.sleep(5000);
	    
	    for(WebElement BrandName:BrandNames)
	    {
	    	String ActualBrandName=BrandName.getText();
	    	if(!ActualBrandName.equalsIgnoreCase(brandName))
	    	{
	            System.out.println("Mismatch Found: "+ActualBrandName);
	    		return false;
	    	}

	    }
		return true;
	}
	
	
	
	public void selectFilter(String filterOption) throws InterruptedException 
	{
		
		
		js.executeScript("window.scrollTo(0,0);"); //Resetting Position bcz Make sure we are at the Starting position of the PLP
		
		Thread.sleep(5000);
		
	    By optionLocator = By.xpath("//div[contains(@class,'jsx-3943973772 ais-Panel-body mt-3')]//span[@class='ais-RefinementList-labelText'][normalize-space()='"+filterOption+"']");
	    WebElement element=driver.findElement(optionLocator);

	    js.executeScript( "arguments[0].scrollIntoView({block:'center'});",element);

	    wait.until(ExpectedConditions.elementToBeClickable(element));
	    element.click();
	    
	}
	
	public int getSelectedFilterCount()
	{
		String count=SelectedFilterCount.getText();
		int filterCount=Integer.parseInt(count);
		if(filterCount==0)
		{
			System.out.println("None of the filters options selected");
		}
		
		System.out.println("Selected Filter Count is -> "+filterCount);
		return filterCount;
		
	}
	
	public PDPpage clickProductByName(String name) throws InterruptedException
	{
		js.executeScript("window.scrollTo(0,0);"); //Resetting Position bcz Make sure we are at the Starting position of the PLP
		boolean found = false;
		 for(WebElement ProductName:ProductTitles)
		 {
			 waitForVisibility(ProductName);
			 if(ProductName.getText().equalsIgnoreCase(name))
			 {
				 System.out.println("Found the product, clicking on it....!");
				 
				// Scroll to element (center)
	             ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",ProductName);
	             
	             // Wait until clickable
	             wait.until(ExpectedConditions.elementToBeClickable(ProductName));
				 ProductName.click();
				 Thread.sleep(3000);
				 System.out.println("PDP Page URL is --> "+driver.getCurrentUrl());
				 found = true;
				 break;
			 }
		 }
		 if(!found)
		 {
			 System.out.println("Product Name not exist......!");
		 }
		 
		 return new PDPpage(driver);
		
	}
	
	public int getSearchResultCount()
	{
		js.executeScript("window.scrollTo(0,0);"); //Resetting Position bcz Make sure we are at the Starting position of the PLP
		String text=SearchResult.getText();
		return Integer.parseInt(text.replaceAll("[^0-9]",""));
	}
	
	public boolean isPLPLoaded()
	{
		return ProductCard.size() > 0;
	}
	
	public int getTotalProductCount() 
	{
		js.executeScript("window.scrollTo(0,0);"); //Resetting Position bcz Make sure we are at the Starting position of the PLP

	    int previousCount = 0;
	    int currentCount = ProductCard.size();

	    while (currentCount > previousCount) 
	    {
	        previousCount = currentCount;

	        // Scroll to bottom
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

	        // Wait for new products to load
	        try {
	            Thread.sleep(5000); // replace with explicit wait if possible
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        currentCount = ProductCard.size();
	    }

	    return currentCount;
	}
	
	public int verifyBrokenLinks() throws IOException
	{
		  int brokenLinksCount = 0;
		  int validLinksCount = 0;
		
		if (productLinks == null || productLinks.isEmpty()) 
		{
            System.out.println("No links found to validate.");
            return 0;
        }
		
		for(WebElement link:productLinks)
		{	
			String hrefAttributeValue=link.getAttribute("href");
			
			if (hrefAttributeValue == null || hrefAttributeValue.isEmpty())
			{	
	            System.out.println("No links found to validate.");
	            continue;
	        }
			
			URL url=new URL(hrefAttributeValue);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000); // timeout
			connection.connect();
			
			int responseCode = connection.getResponseCode();
			
			if(responseCode>400)
			{
				System.out.println("❌ Broken Link: "+url+" "+responseCode);
				brokenLinksCount++;
			}
			else
			{
				System.out.println("✅ Valid Link: "+url);
				validLinksCount++;
				
			}
			
			
			
			
		}
		
		System.out.println("Total Links: " + productLinks.size());
	    System.out.println("Valid Links: " + validLinksCount);
	    System.out.println("Broken Links: " + brokenLinksCount);

	    return brokenLinksCount;   // 🔥 IMPORTANT
	}
	
	
	public void applyPriceSlider() throws InterruptedException
	{
		js.executeScript("arguments[0].scrollIntoView({block:'center'})",minSlider);
		action.clickAndHold(minSlider).moveByOffset(50,0).perform();
		System.out.println("Applied min Slider");
		Thread.sleep(5000);
		action.clickAndHold(maxSlider).moveByOffset(-50,0).perform();
		System.out.println("Applied max Slide");
		
	}
	
	public void enableFreeShippingFeeToggle() throws InterruptedException
	{
		js.executeScript("window.scrollTo(0,0);");
		wait.until(ExpectedConditions.visibilityOf(FreeShippingFeeToggle));
		js.executeScript("arguments[0].scrollIntoView({block:'center'})",FreeShippingFeeToggle);
		if(!FreeShippingFeeToggle.isSelected())
		{
			js.executeScript("arguments[0].click()",FreeShippingFeeToggle);
		}
		else
		{
			System.out.println("FreeShippingFeeToggle is already selected");
		}
		js.executeScript("window.scrollTo(0,0);");
	}

}
