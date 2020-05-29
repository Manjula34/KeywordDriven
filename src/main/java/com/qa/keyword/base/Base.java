package com.qa.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\itsdi\\Downloads\\selenium\\drivers\\chromedriver.exe");
			if (prop.getProperty("headless").equals("yes")){
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
				
			}else {
				driver = new ChromeDriver();
			}			
		}
		return driver;
	}
	
	public Properties init_properties() throws IOException {
		prop =  new Properties();
		FileInputStream fs =  new FileInputStream("C:\\Users\\itsdi\\eclipse-workspace\\KeywordDriven\\src\\main\\java\\com\\qa\\keyword\\config\\Config.properties");
		prop.load(fs);
		return prop;
	}
	

}
