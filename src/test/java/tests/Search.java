package tests;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchPage;
import utils.CommonUtils;

public class Search {
	
	WebDriver driver;
	Properties prop;
	HomePage homePage;
	SearchPage searchPage;
	
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
