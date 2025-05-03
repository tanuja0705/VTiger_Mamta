package com.crm.CampaignTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.crm.generic.ObjectRepository.CreateNewProduct;
import com.crm.generic.ObjectRepository.EditProductPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LoginPage;
import com.crm.generic.ObjectRepository.ProductsPage;
import com.crm.generic.fileUtility.ExcelUtility;
import com.crm.generic.fileUtility.PropertyUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

public class EditProductTest {

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
		home.getProducts().click();
		
		ProductsPage prd = new ProductsPage(driver);
		prd.getPrdAddSign().click();
		
		String product = exc.getDataFromExcelFile("Sheet1", 0, 0)+ran;
		CreateNewProduct newPrd = new CreateNewProduct(driver);
		newPrd.createPrd(product);
		
		Thread.sleep(2000);
		home.getProducts().click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='"+product+"']/../../td[9]/a[text()='edit']")).click();
		
		String price = exc.getDataFromExcelFile("Sheet1", 6, 0);
		EditProductPage editPrice = new EditProductPage(driver);
		editPrice.editUnitPrice(price);
		
		newPrd.getSvBtn().click();
		home.logout();
		driver.quit();
	}

}
