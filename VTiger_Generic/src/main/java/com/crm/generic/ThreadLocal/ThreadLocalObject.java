package com.crm.generic.ThreadLocal;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ThreadLocalObject {
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void setDriver(WebDriver edriver) {
		driver.set(edriver);
	}
	
	public static ExtentTest getTest() {
		return test.get();
	}
	
	public static void setTest(ExtentTest etest) {
		test.set(etest);
	}

}
