package tests;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AccountLogoutPage;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtils;

public class Logout {
	
	WebDriver driver;
	Properties prop;
	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;
	AccountLogoutPage accountLogoutPage;
	
	@BeforeMethod
	public void setup() {
		prop = CommonUtils.loadPropertiesFile();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("url"));
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();		
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
