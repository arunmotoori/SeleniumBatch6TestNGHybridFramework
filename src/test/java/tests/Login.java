package tests;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import utils.CommonUtils;
import utils.MyXLSReader;

public class Login extends Base {
	
	public WebDriver driver;

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
	
	@Test(priority=1,dataProvider="loginDataProvider")
	public void verifyLoginWithValidCredentias(HashMap<String,String> hMap) {
		
		Assert.assertEquals(driver.getTitle(),"Account Login");
		
		//loginPage.enterEmailAddress(prop.getProperty("validEmailOne"));
		loginPage.enterEmailAddress(hMap.get("Username"));
		//loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.enterPassword(hMap.get("Password"));
		accountPage = loginPage.clickOnLoginButton();
		
		Assert.assertTrue(accountPage.isUserLoggedIn());
		Assert.assertEquals(driver.getTitle(),"My Account");
	
	}
	
	@DataProvider(name="loginDataProvider")
	public Object[][] dataSupplierMethod() {
		MyXLSReader myXLSReader = new MyXLSReader("\\src\\test\\resources\\ProjectData.xlsx");
		Object[][] data = CommonUtils.getTestData(myXLSReader,"Login","DataDrivenSheet");
		return data;
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
