package experiments;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Sample {
	
	public static void main(String[] args) throws IOException {
		
		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		String appURL = prop.getProperty("url");
		System.out.println(appURL);
		
	}

}
