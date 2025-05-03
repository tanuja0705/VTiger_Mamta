package com.crm.contactTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.crm.generic.ObjectRepository.ContactsPage;
import com.crm.generic.ObjectRepository.CreateNewContactPage;
import com.crm.generic.ObjectRepository.CreateNewOrganizationPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LoginPage;
import com.crm.generic.ObjectRepository.OrganizationsPage;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.PropertyUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateContactWithOrga {

	public static void main(String[] args) throws Throwable, IOException {
		WebDriver driver=null;
		JavaUtility java = new JavaUtility();
		WebDriverUtility web = new WebDriverUtility();
		ExcelUtility exc = new ExcelUtility();
		PropertyUtility prop = new PropertyUtility();
	
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
		home.getOrganization().click();
		
		OrganizationsPage orgPage = new OrganizationsPage(driver);
		orgPage.getPlusSign().click();
		
		int ran_num = java.getRandomNum();
		
		String orgName = exc.getDataFromExcelFile("Sheet1", 0, 0) +ran_num;
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(driver);
		newOrg.createOrg(orgName);
		
		Thread.sleep(2000);
		
		home.getContacts().click();
		ContactsPage cont = new ContactsPage(driver);
		cont.getPlusSign().click();
		
		String value2 = exc.getDataFromExcelFile("Sheet1", 1, 0) +ran_num;
		
		CreateNewContactPage createCon = new CreateNewContactPage(driver);
		createCon.createContact(value2, orgName, driver);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		web.switchingWindow(driver, "Contacts&action");
		createCon.getSaveBtn().click();
		
		String actual_org_name = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(actual_org_name.trim().equals(orgName)) {
			System.out.println("expected org name"+" "+orgName);
			System.out.println("actual is"+" "+actual_org_name);
		}
		else
			System.out.println("unsuccessfull");
		
		home.getAdminLink().click();
		home.getSignout().click();
		driver.quit();
	}

}
