package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class HomePage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement myAccountDropMenu;
	
	@FindBy(linkText="Register")
	WebElement registerOption;
	
	@FindBy(linkText="Login")
	WebElement loginOption;
	
	@FindBy(name="search")
	WebElement searchBoxField;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement searchButton;
	
	public RegisterPage navigateToRegisterPage() {
		
		clickOnMyAccountDropMenu();
		return selectRegisterOption();
		
	}
	
	public void clickOnMyAccountDropMenu() {
		
		elementUtils.clickOnElement(myAccountDropMenu);
		
	}
	
	public LoginPage navigateToLoginPage() {
		
		clickOnMyAccountDropMenu();
		return selectLoginOption();
	}

	
	public RegisterPage selectRegisterOption() {
		
		elementUtils.clickOnElement(registerOption);
		return new RegisterPage(driver);
		
	}
	
	public LoginPage selectLoginOption() {
		
		elementUtils.clickOnElement(loginOption);
		return new LoginPage(driver);
	}
	
	public void enterProductIntoSearchBoxField(String productText) {
		
		elementUtils.enterTextIntoTheElement(searchBoxField, productText);
		
	}
	
	public SearchPage clickOnSearchButton() {
		
		elementUtils.clickOnElement(searchButton);
		return new SearchPage(driver);
		
	}
	
	
}
