package com.crm.generic.baseutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LoginPage;
import com.crm.generic.ThreadLocal.ThreadLocalObject;
import com.crm.generic.databaseUtility.DatabaseUtility;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.PropertyUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

public class BaseClass {
	public WebDriver driver;
	public static WebDriver sdriver;
	public PropertyUtility prop = new PropertyUtility();
	public ExcelUtility exc = new ExcelUtility();
	public JavaUtility java = new JavaUtility();
	public WebDriverUtility web = new WebDriverUtility();
	public SoftAssert soft = new SoftAssert();
	
	@BeforeSuite(groups = {"smoke","regression"})
	public void beforeSuite() {
		System.out.println("Before Suite");
	}
	
	//@Parameters("browser1")
	@BeforeClass(groups = {"smoke","regression"})
	public void beforeClass() throws Throwable {
		System.out.println("Before Class");
		String BROWSER = prop.getDataFromPropertyFile("browser");
		if(BROWSER.equals("chrome")){
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		else
			driver =new ChromeDriver();
		ThreadLocalObject.setDriver(driver);
	}
	
	
	@BeforeMethod(groups = {"smoke","regression"})
	public void beforeMethod() throws Throwable {
		System.out.println("Before Method");
		String URL = prop.getDataFromPropertyFile("url");
		String USERNAME = prop.getDataFromPropertyFile("username");
		String PASSWORD = prop.getDataFromPropertyFile("password");
		driver.get(URL);
		web.getMaximize(driver);
		web.implicitWait(driver);
		LoginPage login = new LoginPage(driver);
		login.login(USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"smoke","regression"})
	public void afterMethod() {
		System.out.println("after method");
		HomePage home = new HomePage(driver);
		home.logout();
	}
	
	@AfterClass(groups = {"smoke","regression"})
	public void afterClass() {
		System.out.println("after class");
		driver.quit();
	}
	
	@AfterSuite(groups = {"smoke","regression"})
	public void afterSuite() {
		System.out.println("after suite");
		DatabaseUtility db = new DatabaseUtility();
		db.closeDbConnection();
	}

}
