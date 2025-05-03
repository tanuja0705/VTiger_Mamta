package com.crm.contactTest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.crm.generic.ObjectRepository.ContactsPage;
import com.crm.generic.ObjectRepository.CreateNewContactPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LoginPage;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.PropertyUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateContactSupportDate {

	public static void main(String[] args) throws Throwable, IOException {
		WebDriver driver=null;
		PropertyUtility prop = new PropertyUtility();
		JavaUtility java = new JavaUtility();
		ExcelUtility exc = new ExcelUtility();
		WebDriverUtility web = new WebDriverUtility();
		
		String BROWSER = prop.getDataFromPropertyFile("browser");
		String URL = prop.getDataFromPropertyFile("url");
		String USERNAME = prop.getDataFromPropertyFile("username");
		String PASSWORD = prop.getDataFromPropertyFile("password");
		
		driver = java.openBrowser(driver, BROWSER);
		driver.get(URL);
		web.getMaximize(driver);
		web.implicitWait(driver);
		
		LoginPage login = new LoginPage(driver);
		login.login(USERNAME, PASSWORD);
		
		HomePage home = new HomePage(driver);
		home.getContacts().click();
		
		ContactsPage cont = new ContactsPage(driver);
		cont.getPlusSign().click();
		
		int ran_num = java.getRandomNum();
		
		String value1 = exc.getDataFromExcelFile("Sheet1", 1, 0) +ran_num;
		String str_date = java.getSystemDate();
		String end_date = java.getEndDate(30);
		
		CreateNewContactPage createCon = new CreateNewContactPage(driver);
		
		createCon.createContact(value1 ,str_date, end_date);
		
		//verification
		String last_name = driver.findElement(By.id("dtlview_Last Name")).getText();
		String result1 = java.validation(last_name, value1);
		System.out.println(last_name +"is "+ result1);
				
		String start = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		String result2 = java.validation(start, str_date);
		System.out.println(start +"is "+ result2);
		
		String end = driver.findElement(By.id("dtlview_Support End Date")).getText();
		String result3 = java.validation(end, end_date);
		System.out.println(end +"is "+ result3);
		
		home.getAdminLink().click();
		home.getSignout().click();
		driver.quit();

	}

}
