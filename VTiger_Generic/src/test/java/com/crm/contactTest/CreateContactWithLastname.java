package com.crm.contactTest;

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

public class CreateContactWithLastname {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		PropertyUtility prop = new PropertyUtility();
		ExcelUtility exc = new ExcelUtility();
		JavaUtility java = new JavaUtility();
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
		
		String lastName = exc.getDataFromExcelFile("Sheet1", 1, 0) +ran_num;
		
		CreateNewContactPage createCon = new CreateNewContactPage(driver);
		createCon.createContact(lastName);
		
		String last_name = driver.findElement(By.id("dtlview_Last Name")).getText();
		String result2 = java.validation(last_name, lastName);
		System.out.println(last_name +"is "+ result2);
		
		home.getAdminLink().click();
		home.getSignout().click();
		driver.quit();
		
	}

}
