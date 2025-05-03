package com.crm.OrganizationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.crm.generic.ObjectRepository.CreateNewOrganizationPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LoginPage;
import com.crm.generic.ObjectRepository.OrganizationsPage;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.PropertyUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateOrganizationWithPhone {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		PropertyUtility prop = new PropertyUtility();
		ExcelUtility exc = new ExcelUtility();
		JavaUtility java = new JavaUtility();
		WebDriverUtility web= new WebDriverUtility();
		
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
		String phoneNum = exc.getDataFromExcelFile("Sheet1", 0, 3) +ran_num;
		
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(driver);
		newOrg.createOrg(orgName, phoneNum);
		
		//verification
		String org_var = driver.findElement(By.className("dvHeaderText")).getText();
		String result1 = java.validation(org_var, orgName);
		System.out.println(result1);
		
		String org_nam = driver.findElement(By.id("dtlview_Organization Name")).getText();
		String result2 = java.validation(org_nam, orgName);
		System.out.println(result2);
		
		String ph = driver.findElement(By.id("dtlview_Phone")).getText();
		String result3 = java.validation(ph, phoneNum);
		System.out.println(result3);
		
		//signout
		//Actions act = new Actions(driver);
		home.getAdminLink().click();
		home.getSignout().click();
		driver.quit();
	}

}
