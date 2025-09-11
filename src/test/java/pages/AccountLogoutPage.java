package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountLogoutPage {
	
	WebDriver driver;
	
	public AccountLogoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(linkText="Continue")
	WebElement continueButton;
	
	public void clickOnContinueButton() {
		continueButton.click();
	}
}
