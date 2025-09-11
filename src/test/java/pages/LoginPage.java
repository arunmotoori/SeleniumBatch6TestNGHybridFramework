package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
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
		emailAddressField.sendKeys(emailAddressText);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String getWarningMessage() {
		return warningMessage.getText();
	}

}
