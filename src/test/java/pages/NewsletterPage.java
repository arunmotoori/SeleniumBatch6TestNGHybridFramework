package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewsletterPage {
	
	WebDriver driver;
	
	public NewsletterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	WebElement yesNewsletterOption;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='0']")
	WebElement noNewsletterOption;
	
	public boolean isYesNewletterOptionSelected() {
		return yesNewsletterOption.isSelected();
	}
	
	public boolean isNoNewsletterOptionSelected() {
		return noNewsletterOption.isSelected();
	}

}
