package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class NewsletterPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	
	public NewsletterPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	WebElement yesNewsletterOption;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='0']")
	WebElement noNewsletterOption;
	
	public boolean isYesNewletterOptionSelected() {
		return elementUtils.isElementInSelectedState(yesNewsletterOption);
	}
	
	public boolean isNoNewsletterOptionSelected() {
		return elementUtils.isElementInSelectedState(noNewsletterOption);
	}

}
