package tests;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class Search {
	
	WebDriver driver;
	Properties prop;
	
	@BeforeMethod
	public void setup() {
		prop = CommonUtils.loadPropertiesFile();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchingWithExistingProductName() {
		driver.findElement(By.name("search")).sendKeys(prop.getProperty("existingProductOne"));
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("iMac")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifySearchingWithNonExistingProductName() {
		driver.findElement(By.name("search")).sendKeys(prop.getProperty("nonExistingProduct"));
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/p[2]")).getText(),"There is no product that matches the search criteria.");
	}
	
	@Test(priority=3)
	public void verifySearchingWithoutProvidingAnyProductName() {
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/p[2]")).getText(),"There is no product that matches the search criteria.");	
	}

}
