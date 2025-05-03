package com.crm.OrganizationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.crm.generic.ObjectRepository.CreateNewOrganizationPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LoginPage;
import com.crm.generic.ObjectRepository.OrganizationsPage;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.PropertyUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateOrganization {

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
		
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(driver);
		newOrg.createOrg(orgName);
		
		//verification
		String org_var = driver.findElement(By.className("dvHeaderText")).getText();
	
		String org_nam = driver.findElement(By.id("dtlview_Organization Name")).getText();

		//Assert.assertEquals(org_var, orgName);
		Assert.assertEquals(org_nam, orgName);
		
		//signout
		//Actions act = new Actions(driver);
		home.logout();
		driver.quit();
		
	}

}
