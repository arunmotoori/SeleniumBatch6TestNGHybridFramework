package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class RegisterPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(id="input-firstname")
	WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	WebElement lastNameField;
	
	@FindBy(id="input-email")
	WebElement emailField;
	
	@FindBy(id="input-telephone")
	WebElement telephoneField;
	
	@FindBy(id="input-password")
	WebElement passwordField;
	
	@FindBy(id="input-confirm")
	WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	WebElement yesNewsletterOption;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='0']")
	WebElement noNewsletterOption;
	
	public void enterFirstName(String firstNameText) {
		elementUtils.enterTextIntoTheElement(firstNameField, firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		elementUtils.enterTextIntoTheElement(lastNameField, lastNameText);
	}
	
	public void enterEmail(String emailText) {
		elementUtils.enterTextIntoTheElement(emailField, emailText);
	}
	
	public void enterTelephone(String telephoneText) {
		elementUtils.enterTextIntoTheElement(telephoneField, telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		elementUtils.enterTextIntoTheElement(passwordField, passwordText);
	}
	
	public void enterConfirmPassword(String passwordText) {
		elementUtils.enterTextIntoTheElement(passwordConfirmField, passwordText);
	}
	
	public void selectPrivacyPolicy() {
		elementUtils.clickOnElement(privacyPolicyField);
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		elementUtils.clickOnElement(continueButton);
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsletterOption() {
		elementUtils.clickOnElement(yesNewsletterOption);
	}
	
	public void selectNoNewsletterOption() {
		elementUtils.clickOnElement(noNewsletterOption);
	}

}
