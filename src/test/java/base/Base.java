package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import pages.AccountLogoutPage;
import pages.AccountPage;
import pages.AccountSuccessPage;
import pages.HomePage;
import pages.LoginPage;
import pages.NewsletterPage;
import pages.RegisterPage;
import pages.SearchPage;
import utils.CommonUtils;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public HomePage homePage;
	public RegisterPage registerPage;
	public AccountSuccessPage accountSuccessPage;
	public AccountPage accountPage;
	public NewsletterPage newsletterPage;
	public LoginPage loginPage;
	public AccountLogoutPage accountLogoutPage;
	public SearchPage searchPage;
	
	public WebDriver openApplicationURLInTheBrowser() {
		
		prop = CommonUtils.loadPropertiesFile();
		
		
		String browserName = prop.getProperty("browserName");
		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equals("edge")) {
			driver = new EdgeDriver();
		}else if(browserName.equals("safari")) {
			driver = new SafariDriver();
		}
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("url"));
		
		return driver;
		
	}
	
	public void closeBrowser(WebDriver driver) {
		
		if(driver!=null) {
			driver.quit();
		}
		
	}

}
