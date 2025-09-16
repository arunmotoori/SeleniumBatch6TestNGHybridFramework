package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class LoginPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="input-email")
	WebElement emailAddressField;
	
	@FindBy(id="input-password")
	WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement warningMessage;
	
	public AccountPage loginToApplication(String emailAddressText,String passwordText) {
		enterEmailAddress(emailAddressText);
		enterPassword(passwordText);
		return clickOnLoginButton();
	}
	
	public void enterEmailAddress(String emailAddressText) {
		elementUtils.enterTextIntoTheElement(emailAddressField, emailAddressText);
	}
	
	public void enterPassword(String passwordText) {
		elementUtils.enterTextIntoTheElement(passwordField, passwordText);
	}
	
	public AccountPage clickOnLoginButton() {
		elementUtils.clickOnElement(loginButton);
		return new AccountPage(driver);
	}
	
	public String getWarningMessage() {
		return elementUtils.getTextFromElement(warningMessage);
	}

}
