package com.qa.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.keyword.base.Base;

public class KeywordEngine {
	public WebDriver driver;
	public Properties prop;
	public static XSSFWorkbook book;
	public static XSSFSheet sheet;
	
	public final String SCENARIO ="C:\\Users\\itsdi\\eclipse-workspace\\KeywordDriven\\src\\main\\java\\com\\qa\\keyword\\scenarios\\Test_scenarios.xlsx";
	
	public void startExecution(String sheetname) throws IOException {
		
		String locatorName = null;
		String locatorvalue = null;
		FileInputStream file =  null;
		Base base;
		
		try {
			 file = new FileInputStream(SCENARIO);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = new XSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetname);
		int k = 0;
		System.out.println("sheetrow num :" +sheet.getLastRowNum());
		for(int i=0;i<sheet.getLastRowNum();i++) {
			try {
			String locatorColvalue = sheet.getRow(i+1).getCell(k+1).toString().trim();
			
			if(!locatorColvalue.equalsIgnoreCase("NA")) {
				System.out.println("inside if " + i);
				locatorName = locatorColvalue.split("=")[0].trim();
				locatorvalue = locatorColvalue.split("=")[1].trim();
				
			}
			String action = sheet.getRow(i +1).getCell(k+2).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+3).toString().trim();
			
			switch (action) {
			case "open browser":
				base = new Base();
				prop = base.init_properties();
				if(value.isEmpty() || value.equals("NA"))
				{
					base.init_driver(prop.getProperty("browser"));
				}else {
					driver = base.init_driver(value);
				}
				
				break;
			case "enter url":
				
				if(value.isEmpty() || value.equals("NA"))
				{
					driver.get(prop.getProperty("url"));
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				}else {
					driver.get(value);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				}
				break;
			case "quit":
				driver.quit();
				break;

			default:
				break;
			}
			
			switch (locatorName) {
			case "id":
				
				WebElement element =  driver.findElement(By.id(locatorvalue));
				if (action.equalsIgnoreCase("Sendkeys")) {
					element.clear();
					
					element.sendKeys(value);
					
				}else if(action.equalsIgnoreCase("click")) {
					element.click();
				}	
				locatorName = null;
				break;
			case "linkText":
				WebElement element1 =  driver.findElement(By.linkText(locatorvalue));
				element1.click();
				locatorName = null;
				break;
			default:
				break;
			}
			}catch(Exception e) {
		}
	}
		
		
	}

}
