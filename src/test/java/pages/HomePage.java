package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
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
		
		myAccountDropMenu.click();
		
	}
	
	public LoginPage navigateToLoginPage() {
		clickOnMyAccountDropMenu();
		return selectLoginOption();
	}

	
	public RegisterPage selectRegisterOption() {
		
		registerOption.click();
		return new RegisterPage(driver);
		
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public void enterProductIntoSearchBoxField(String productText) {
		searchBoxField.sendKeys(productText);
	}
	
	public SearchPage clickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	
}
