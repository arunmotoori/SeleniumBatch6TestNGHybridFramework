package tests;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AccountPage;
import pages.AccountSuccessPage;
import pages.HomePage;
import pages.NewsletterPage;
import pages.RegisterPage;
import utils.CommonUtils;

public class Register {
	
	WebDriver driver;
	Properties prop;
	HomePage homePage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	AccountPage accountPage;
	NewsletterPage newsletterPage;
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
	}
	
	@BeforeMethod
	public void setup() {
		
		prop = CommonUtils.loadPropertiesFile();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("url"));
		
		homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
		
	}
	
	@Test(priority=1)
	public void verifyRegisterAccountUsingMandatoryFields() {
	
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		
		Assert.assertTrue(accountSuccessPage.displayStatusOfLogoutOption());
	
		Assert.assertEquals(driver.getTitle(),"Your Account Has Been Created!");
		
		accountSuccessPage.clickOnContinueButton();
		
		Assert.assertEquals(driver.getTitle(),"My Account");
		
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAnAccountByProvidingAllTheFields() {
		
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		
		Assert.assertTrue(accountSuccessPage.displayStatusOfLogoutOption());
	
		Assert.assertEquals(driver.getTitle(),"Your Account Has Been Created!");
		
		accountSuccessPage.clickOnContinueButton();
		
		Assert.assertEquals(driver.getTitle(),"My Account");
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringBySubscribingToNewsletter() {
			
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		
		accountPage = accountSuccessPage.clickOnContinueButton();
	
		newsletterPage = accountPage.selectSubscribeOrUnscribeToNewsletterOption();
		
		Assert.assertEquals(driver.getTitle(),"Newsletter Subscription");
		Assert.assertTrue(newsletterPage.isYesNewletterOptionSelected());
		
	}
	
	@Test(priority=4)
	public void verifyRegistringByNotSubscribingToNewsletter() {
				
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNoNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
	
		accountPage = accountSuccessPage.clickOnContinueButton();
		newsletterPage = accountPage.selectSubscribeOrUnscribeToNewsletterOption();
		Assert.assertEquals(driver.getTitle(),"Newsletter Subscription");
		Assert.assertTrue(newsletterPage.isNoNewsletterOptionSelected());
	
	}
	
	
	public static String generateBrandNewEmail() {
		
		return "arunmotoori"+System.currentTimeMillis()+"@gmail.com";
		
	}

}
