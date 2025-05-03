package com.crm.TestNGClasses;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.ObjectRepository.CreateNewProduct;
import com.crm.generic.ObjectRepository.EditProductPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.ProductsPage;
import com.crm.generic.baseutility.BaseClass;

@Listeners(com.crm.generic.listeners.ListenerImpClass.class)
public class CreateProductTest extends BaseClass{
	
	@Test //retryAnalyzer = com.crm.generic.listeners.RetryAnalyzerImp.class
	public void editProductPrice() throws Throwable {
			
		int ran = java.getRandomNum();
		
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
		String price_new = driver.findElement(By.xpath("//a[text()='"+product+"']/../../td[8]")).getText();
		boolean p = price_new.contains(price);
		soft.assertEquals(p, true);
	}

}
