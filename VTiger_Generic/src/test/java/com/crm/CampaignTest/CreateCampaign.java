package com.crm.CampaignTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.crm.generic.ObjectRepository.CampaignPage;
import com.crm.generic.ObjectRepository.CreateNewCampaignPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LoginPage;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.PropertyUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateCampaign {

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
		
		WebElement MoreEle = driver.findElement(By.linkText("More"));
		web.moveToEle(driver, MoreEle);
		
		HomePage home = new HomePage(driver);
		home.getCamp().click();
		
		CampaignPage camp = new CampaignPage(driver);
		camp.getCampPlusSign().click();
		
		int random = java.getRandomNum();
		String Camp = exc.getDataFromExcelFile("Sheet1", 2, 0) + random;
		
		CreateNewCampaignPage newCamp = new CreateNewCampaignPage(driver);
		newCamp.getCampName().sendKeys(Camp);
		boolean selected = newCamp.getSelectUser().isSelected();

		if(selected==false) {
			newCamp.getSelectUser().click();
		}
		else newCamp.getSelectUser1().click();
		
		newCamp.getSaveBtn().click();
		
		//verification
		String actual = driver.findElement(By.className("dvHeaderText")).getText();
		String Result = java.validation(actual, Camp);
		System.out.println(actual +"is "+ Result);
		
		home.logout();
		driver.quit();
	}

}
