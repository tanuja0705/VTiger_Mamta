package com.crm.generic.javaUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaUtility {
	
	public int getRandomNum() {
		
		Random ran = new Random();
		int number = ran.nextInt(1000);
		return number;
	}
	
	public String getSystemDate() {
		
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dateObj);
		return date;
	}
	
	public String getEndDate(int days) {
		
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		sim.format(dateObj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String EndDate = sim.format(cal.getTime());
		return EndDate;
	}
	
	public String validation(String actual, String expected) {
		if(actual.contains(expected)) {
			return "validated";
		}
		else return "not validated";
	}
	
	public WebDriver openBrowser(WebDriver driver, String browser) {
		if(browser.equals("chrome")){
			driver = new ChromeDriver();
		}
		else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		else
			driver =new ChromeDriver();
		
		return driver;
		
	}

}
