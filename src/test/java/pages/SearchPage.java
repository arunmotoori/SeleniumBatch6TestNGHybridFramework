package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="iMac")
	WebElement existingProductOne;
	
	@FindBy(xpath="//div[@id='content']/p[2]")
	WebElement noProductMessage;
	
	public boolean isProductDisplayedInSearchResults() {
		return existingProductOne.isDisplayed();
	}
	
	public String getMessage() {
		return noProductMessage.getText();
	}

}
