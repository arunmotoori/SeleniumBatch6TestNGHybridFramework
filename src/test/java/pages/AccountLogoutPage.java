package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class AccountLogoutPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public AccountLogoutPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}

	@FindBy(linkText="Continue")
	WebElement continueButton;
	
	public void clickOnContinueButton() {
		elementUtils.clickOnElement(continueButton);
	}
}
