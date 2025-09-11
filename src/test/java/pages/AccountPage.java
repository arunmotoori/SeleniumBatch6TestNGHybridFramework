package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="Subscribe / unsubscribe to newsletter")
	WebElement subscribeOrUnsubscribeToNewsletter;
	
	@FindBy(xpath="//*[@id='column-right']//a[text()='Logout']")
	WebElement logoutOption;
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement myAccountDropMenu;
	
	@FindBy(linkText="Logout")
	WebElement MenuLogoutOption;
	
	public AccountLogoutPage selectLogoutOption() {
		MenuLogoutOption.click();
		return new AccountLogoutPage(driver);
	}
	
	public void clickOnMyAccountDropMenu() {
		myAccountDropMenu.click();
	}
	
	public NewsletterPage selectSubscribeOrUnscribeToNewsletterOption() {
		subscribeOrUnsubscribeToNewsletter.click();
		return new NewsletterPage(driver);
	}
	
	public boolean isUserLoggedIn() {
		return logoutOption.isDisplayed();
	}
	
	public AccountLogoutPage selectRightColumnLogoutOption() {
		logoutOption.click();
		return new AccountLogoutPage(driver);
	}

}
