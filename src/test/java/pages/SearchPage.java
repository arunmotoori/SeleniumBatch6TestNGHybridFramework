package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class SearchPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="iMac")
	WebElement existingProductOne;
	
	@FindBy(xpath="//div[@id='content']/p[2]")
	WebElement noProductMessage;
	
	public boolean isProductDisplayedInSearchResults() {
		return elementUtils.isElementDisplayed(existingProductOne);
	}
	
	public String getMessage() {
		return elementUtils.getTextFromElement(noProductMessage);
	}

}
