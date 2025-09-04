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

public class Login {
	
	WebDriver driver;
	Properties prop;
	
	@BeforeMethod
	public void setup() {
		
		prop = CommonUtils.loadPropertiesFile();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("url"));
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	
	}
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentias() {
		
		Assert.assertEquals(driver.getTitle(),"Account Login");
		
		driver.findElement(By.id("input-email")).sendKeys("amotooricap6@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='column-right']//a[text()='Logout']")).isDisplayed());
		Assert.assertEquals(driver.getTitle(),"My Account");
	
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()  {
		
		driver.findElement(By.id("input-email")).sendKeys(generateBrandNewEmail());
		driver.findElement(By.id("input-password")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(),"Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys(generateBrandNewEmail());
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(),"Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys("amotooricap7@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(),"Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutEnteringAnyCredentials() {
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(),"Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	public static String generateBrandNewEmail() {
		
		return "arunmotoori"+System.currentTimeMillis()+"@gmail.com";
		
	}

}
