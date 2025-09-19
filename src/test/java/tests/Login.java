package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import utils.CommonUtils;

public class Login extends Base {
	
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		
		driver = openApplicationURLInTheBrowser();
		homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
	
	}
	
	@AfterMethod
	public void teardown() {
		
		closeBrowser(driver);
		
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentias() {
		
		Assert.assertEquals(driver.getTitle(),"Account Login");
		
		loginPage.enterEmailAddress(prop.getProperty("validEmailOne"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		
		Assert.assertTrue(accountPage.isUserLoggedIn());
		Assert.assertEquals(driver.getTitle(),"My Account");
	
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()  {
		
		loginPage.enterEmailAddress(CommonUtils.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		Assert.assertEquals(loginPage.getWarningMessage(),"Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.enterEmailAddress(CommonUtils.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		Assert.assertEquals(loginPage.getWarningMessage(),"Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.enterEmailAddress(prop.getProperty("validEmailTwo"));
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		Assert.assertEquals(loginPage.getWarningMessage(),"Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutEnteringAnyCredentials() {
		
		loginPage.clickOnLoginButton();		
		Assert.assertEquals(loginPage.getWarningMessage(),"Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	
}
