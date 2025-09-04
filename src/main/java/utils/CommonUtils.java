package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

}
