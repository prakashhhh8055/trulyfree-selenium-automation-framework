package components;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.PLPpage;

public class HeaderComponent {

	WebDriver driver;
	WebDriverWait wait;

	public HeaderComponent(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@title='Login/Sign up']//*[name()='svg']") WebElement Login;
	@FindBy(xpath="//a[@aria-label='Go to cart page']//*[name()='svg']") WebElement Cart;
	@FindBy(xpath="//span[@class='jsx-66837041 profile__text']") WebElement profile_txt;
	@FindBy(xpath="//p[contains(text(),'Logout')]") WebElement logoutBtn;
	@FindBy(xpath="//*[text()='My Referrals']") WebElement MyReferrals;
	@FindBy(xpath="//input[@class='jsx-bcc2e1b3560dc6c7 search_input w-100 md_none']/parent::form") WebElement Search;
	
	@FindBy(xpath="//div[@role='dialog']//button[text()='Logout']")  WebElement LogoutConfirm;
	
	public LoginPopup openLogin()
	{
		Login.click();
		return new LoginPopup(driver);
	}
	
	public CartPage openCart()
	{
		Cart.click();
		return new CartPage(driver);
	}
	
	public PLPpage performSearch(String searchInput) throws InterruptedException
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Wait for search box
	    wait.until(ExpectedConditions.visibilityOf(Search));
	    wait.until(ExpectedConditions.elementToBeClickable(Search));

	    Search.click();
	    Thread.sleep(5000);
	    
	    driver.switchTo().activeElement().sendKeys(searchInput);
	    driver.switchTo().activeElement().sendKeys(Keys.ENTER);
	    return new PLPpage(driver);
	}
	
	public void hoverAndSelectLogout() 
	{
		
		Actions action = new Actions(driver);
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Wait until profile is visible
	    wait.until(ExpectedConditions.visibilityOf(profile_txt));
	    action.moveToElement(profile_txt).perform();
	    wait.until(ExpectedConditions.visibilityOf(MyReferrals));
	    action.moveToElement(MyReferrals).perform();
	    wait.until(ExpectedConditions.visibilityOf(logoutBtn));
	    action.scrollToElement(logoutBtn).perform();
	    

	    // Wait until logout appears
	    wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));

	    logoutBtn.click();
	    wait.until(ExpectedConditions.elementToBeClickable(LogoutConfirm));
	    LogoutConfirm.click();
	}
	
	public boolean IsProfileTxtPresent()
	{
	    try
	    {
	        return profile_txt.isDisplayed();
	    }
	    catch(Exception e)
	    {
	        return false;
	    }
	}
	
	
	

}
