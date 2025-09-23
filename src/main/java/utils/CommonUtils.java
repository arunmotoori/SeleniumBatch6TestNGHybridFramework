package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

public class CommonUtils {
	
	public static Properties loadPropertiesFile()  {
		
		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;

	}
	
	public static String generateBrandNewEmail() {
		
		return "arunmotoori"+System.currentTimeMillis()+"@gmail.com";
		
	}
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReports = new ExtentReports();
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"\\Reports\\extentReport.html"));
		ExtentSparkReporterConfig sparkReportConfig = sparkReporter.config();
		sparkReportConfig.setDocumentTitle("TutorialsNinja Report");
		sparkReportConfig.setReportName("TN Test Results");
		
		extentReports.attachReporter(sparkReporter);
		
		extentReports.setSystemInfo("Username","Arun Motoori");
		extentReports.setSystemInfo("Selenium Version","4.35.0");
		extentReports.setSystemInfo("Operating System","Windows 11");
		
		return extentReports;
	
	}
	
	public static String takeScreenshot(WebDriver driver,String testName) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot,new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;
	}

}
