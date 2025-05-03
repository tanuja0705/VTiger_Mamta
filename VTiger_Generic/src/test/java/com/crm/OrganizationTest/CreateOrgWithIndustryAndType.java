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

public class CreateOrgWithIndustryAndType {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		PropertyUtility prop = new PropertyUtility();
		JavaUtility java = new JavaUtility();
		WebDriverUtility web = new WebDriverUtility();
		ExcelUtility exc = new ExcelUtility();
		
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
		String indust = exc.getDataFromExcelFile("Sheet1", 0, 1);
		String typ = exc.getDataFromExcelFile("Sheet1", 0, 2);
		
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(driver);
		newOrg.createOrg(orgName, indust, typ);
		
		//verification
		String org_var = driver.findElement(By.className("dvHeaderText")).getText();
		String result1 = java.validation(org_var, orgName);
		System.out.println(orgName +"is"+ result1);
		
		String org_nam = driver.findElement(By.id("dtlview_Organization Name")).getText();
		String result2 = java.validation(org_nam, orgName);
		System.out.println(orgName +"is"+ result2);
		
		String indus = driver.findElement(By.id("dtlview_Industry")).getText();
		String result3 = java.validation(indus, indust);
		System.out.println(indust +"is"+ result3);
		
		String type = driver.findElement(By.id("dtlview_Type")).getText();
		String result4 = java.validation(type, typ);
		System.out.println(typ +"is"+ result4);
		
		//signout
		//Actions act = new Actions(driver);
		home.getAdminLink().click();
		home.getSignout().click();
		driver.quit();

	}

}
