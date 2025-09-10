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
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		homePage.selectRegisterOption();
		
	}
	
	@Test(priority=1)
	public void verifyRegisterAccountUsingMandatoryFields() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		Assert.assertTrue(accountSuccessPage.displayStatusOfLogoutOption());
	
		Assert.assertEquals(driver.getTitle(),"Your Account Has Been Created!");
		
		accountSuccessPage.clickOnContinueButton();
		
		Assert.assertEquals(driver.getTitle(),"My Account");
		
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAnAccountByProvidingAllTheFields() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		Assert.assertTrue(accountSuccessPage.displayStatusOfLogoutOption());
	
		Assert.assertEquals(driver.getTitle(),"Your Account Has Been Created!");
		
		accountSuccessPage.clickOnContinueButton();
		
		Assert.assertEquals(driver.getTitle(),"My Account");
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringBySubscribingToNewsletter() {
				
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		accountSuccessPage.clickOnContinueButton();
		
		AccountPage accountPage = new AccountPage(driver);
		accountPage.selectSubscribeOrUnscribeToNewsletterOption();
		
		Assert.assertEquals(driver.getTitle(),"Newsletter Subscription");
		NewsletterPage newsletterPage = new NewsletterPage(driver);
		Assert.assertTrue(newsletterPage.isYesNewletterOptionSelected());
		
	}
	
	@Test(priority=4)
	public void verifyRegistringByNotSubscribingToNewsletter() {
				
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(generateBrandNewEmail());
		registerPage.enterTelephone(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNoNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		accountSuccessPage.clickOnContinueButton();
		
		AccountPage accountPage = new AccountPage(driver);
		accountPage.selectSubscribeOrUnscribeToNewsletterOption();
				
		Assert.assertEquals(driver.getTitle(),"Newsletter Subscription");
		
		NewsletterPage newsletterPage = new NewsletterPage(driver);
		Assert.assertTrue(newsletterPage.isNoNewsletterOptionSelected());
	
	}
	
	
	public static String generateBrandNewEmail() {
		
		return "arunmotoori"+System.currentTimeMillis()+"@gmail.com";
		
	}

}
