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

public class Logout {
	
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
	public void logoutFromMyAccountDropMenu() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("amotooricap6@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Logout")).click();
		Assert.assertEquals(driver.getTitle(),"Account Logout");
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertEquals(driver.getTitle(),"Your Store");
	}
	
	@Test(priority=2)
	public void logoutFromRightColumnOptions() {
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("amotooricap6@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		driver.findElement(By.xpath("//*[@id='column-right']//a[text()='Logout']")).click();
		Assert.assertEquals(driver.getTitle(),"Account Logout");
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertEquals(driver.getTitle(),"Your Store");
	}

}
