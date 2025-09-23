package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;

public class Logout extends Base {
	
	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		
		driver = openApplicationURLInTheBrowser();
		homePage = new HomePage(driver);
		
	}
	
	@AfterMethod
	public void teardown() {
		
		closeBrowser(driver);	
		
	}
	
	@Test(priority=1)
	public void logoutFromMyAccountDropMenu() {
		loginPage = homePage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("validEmailOne"), prop.getProperty("validPassword"));
		accountPage.clickOnMyAccountDropMenu();
		accountLogoutPage = accountPage.selectLogoutOption();
		Assert.assertEquals(driver.getTitle(),"Account Logout");
		accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(driver.getTitle(),"Your Store");
	}
	
	@Test(priority=2)
	public void logoutFromRightColumnOptions() {
		loginPage = homePage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("validEmailOne"), prop.getProperty("validPassword"));
		accountLogoutPage = accountPage.selectRightColumnLogoutOption();
		Assert.assertEquals(driver.getTitle(),"Account Logout");
		accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(driver.getTitle(),"Your Store");
	}

}
