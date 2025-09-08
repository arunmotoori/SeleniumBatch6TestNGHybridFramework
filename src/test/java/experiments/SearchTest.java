package experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SearchTest {

	@Test
	public void verifySearchWithExistingProduct() throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		
		Thread.sleep(3000);
		
		boolean status = driver.findElement(By.linkText("HP LP3065")).isDisplayed();
		
		if(status) {
			System.out.println("Test Passed");
		}else {
			System.out.println("Test Failed");
		}

		driver.quit();
		
	}

}
