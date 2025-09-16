package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class AccountSuccessPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//aside[@id='column-right']//a[text()='Logout']")
	WebElement logoutOption;
	
	@FindBy(linkText="Continue")
	WebElement continueButton;
	
	public boolean displayStatusOfLogoutOption() {
		return elementUtils.isElementDisplayed(logoutOption);
	}
	
	public AccountPage clickOnContinueButton() {
		elementUtils.clickOnElement(continueButton);
		return new AccountPage(driver);
	}

}
