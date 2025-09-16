package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class AccountPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
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
		elementUtils.clickOnElement(MenuLogoutOption);
		return new AccountLogoutPage(driver);
	}
	
	public void clickOnMyAccountDropMenu() {
		elementUtils.clickOnElement(myAccountDropMenu);
	}
	
	public NewsletterPage selectSubscribeOrUnscribeToNewsletterOption() {
		elementUtils.clickOnElement(subscribeOrUnsubscribeToNewsletter);
		return new NewsletterPage(driver);
	}
	
	public boolean isUserLoggedIn() {
		return elementUtils.isElementDisplayed(logoutOption);
	}
	
	public AccountLogoutPage selectRightColumnLogoutOption() {
		elementUtils.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}

}
