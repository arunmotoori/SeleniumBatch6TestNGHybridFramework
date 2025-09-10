package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	
	WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//aside[@id='column-right']//a[text()='Logout']")
	WebElement logoutOption;
	
	@FindBy(linkText="Continue")
	WebElement continueButton;
	
	public boolean displayStatusOfLogoutOption() {
		return logoutOption.isDisplayed();
	}
	
	public void clickOnContinueButton() {
		continueButton.click();
	}

}
