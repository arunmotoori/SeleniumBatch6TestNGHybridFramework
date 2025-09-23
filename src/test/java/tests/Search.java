package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;

public class Search extends Base {
	
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
	public void verifySearchingWithExistingProductName() {
		
		homePage.enterProductIntoSearchBoxField(prop.getProperty("existingProductOne"));
		searchPage = homePage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
		
	}
	
	@Test(priority=2)
	public void verifySearchingWithNonExistingProductName() {
		homePage.enterProductIntoSearchBoxField(prop.getProperty("nonExistingProduct"));
		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.getMessage(),"There is no product that matches the search criteria.");
	}
	
	@Test(priority=3)
	public void verifySearchingWithoutProvidingAnyProductName() {
		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.getMessage(),"There is no product that matches the search criteria.");	
	}

}
