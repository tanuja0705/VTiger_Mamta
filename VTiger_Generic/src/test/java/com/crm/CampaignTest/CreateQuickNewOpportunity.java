package com.crm.CampaignTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.crm.generic.ObjectRepository.CreateNewOpportunityPage;
import com.crm.generic.ObjectRepository.CreateNewOrganizationPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LoginPage;
import com.crm.generic.ObjectRepository.OrganizationsPage;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.PropertyUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateQuickNewOpportunity {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		PropertyUtility prop = new PropertyUtility();
		WebDriverUtility web = new WebDriverUtility();
		ExcelUtility exc = new ExcelUtility();
		JavaUtility java = new JavaUtility();
		
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
		
		WebElement ele = home.getQuickCreate();
		web.selectByvalue(ele, "Potentials");
		
		int ran = java.getRandomNum();
		String opp_name = exc.getDataFromExcelFile("Sheet1", 3, 0) + ran;
		
		CreateNewOpportunityPage newOppo = new CreateNewOpportunityPage(driver);
		newOppo.getPotenName().sendKeys(opp_name);
		newOppo.getRelated().click();
		
		web.switchingWindow(driver, "Accounts&action");
		
		newOppo.searchOrg(orgName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		web.switchingWindow(driver, "Campaigns&action");
		newOppo.getSaveButn().click();
		
		String orgname = driver.findElement(By.xpath("//a[@title='Organizations']")).getText();
		String result1 = java.validation(orgname, orgName);
		System.out.println(orgName+"is "+result1);
		
		String oppname = driver.findElement(By.id("dtlview_Opportunity Name")).getText();
		String result2 = java.validation(oppname, opp_name);
		System.out.println(opp_name+"is "+oppname+","+result2);
		
		home.logout();
		driver.quit();
	}

}
