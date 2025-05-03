package com.crm.CampaignTest;

import org.openqa.selenium.WebDriver;

import com.crm.generic.ObjectRepository.CreateNewLeadPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LeadPage;
import com.crm.generic.ObjectRepository.LoginPage;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.PropertyUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateLeadTest {

	public static void main(String[] args) throws Throwable {
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
		
		int ran = java.getRandomNum();
		LoginPage login = new LoginPage(driver);
		login.login(USERNAME, PASSWORD);
		
		HomePage home = new HomePage(driver);
		home.getLead().click();
		
		LeadPage lead = new LeadPage(driver);
		lead.getLeadPlusSign().click();
		
		String lead_last = exc.getDataFromExcelFile("Sheet1", 4, 0)+ran;
		String lead_comp = exc.getDataFromExcelFile("Sheet1", 5, 0)+ran;
		
		CreateNewLeadPage newLead = new CreateNewLeadPage(driver);
		newLead.createNewLead(lead_last, lead_comp);
		
		home.logout();
		driver.quit();

	}

}
