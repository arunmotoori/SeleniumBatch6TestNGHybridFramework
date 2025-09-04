package tests;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
	}
	
	@Test(priority=1)
	public void verifyRegisterAccountUsingMandatoryFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys(generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		WebElement logoutOption = driver.findElement(By.xpath("//aside[@id='column-right']//a[text()='Logout']"));
		Assert.assertTrue(logoutOption.isDisplayed());
	
		Assert.assertEquals(driver.getTitle(),"Your Account Has Been Created!");
		
		driver.findElement(By.linkText("Continue")).click();
		
		Assert.assertEquals(driver.getTitle(),"My Account");
		
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAnAccountByProvidingAllTheFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys(generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		WebElement logoutOption = driver.findElement(By.xpath("//aside[@id='column-right']//a[text()='Logout']"));
		Assert.assertTrue(logoutOption.isDisplayed());
	
		Assert.assertEquals(driver.getTitle(),"Your Account Has Been Created!");
		
		driver.findElement(By.linkText("Continue")).click();
		
		Assert.assertEquals(driver.getTitle(),"My Account");
		
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringBySubscribingToNewsletter() {
				
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys(generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
		
		Assert.assertEquals(driver.getTitle(),"Newsletter Subscription");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).isSelected());
		
		
	}
	
	@Test(priority=4)
	public void verifyRegistringByNotSubscribingToNewsletter() {
				
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys(generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
		
		Assert.assertEquals(driver.getTitle(),"Newsletter Subscription");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).isSelected());
	
		
	}
	
	
	public static String generateBrandNewEmail() {
		
		return "arunmotoori"+System.currentTimeMillis()+"@gmail.com";
		
	}

}
