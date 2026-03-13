package components;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderComponent {

	WebDriver driver;

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
	
	@FindBy(xpath="//div[@role='dialog']//button[text()='Logout']")  WebElement LogoutConfirm;
	
	public void openLogin()
	{
		Login.click();
	}
	
	public void openCart()
	{
		Cart.click();
	}
	
	public void hoverAndSelectLogout() 
	{
		
		Actions action = new Actions(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
