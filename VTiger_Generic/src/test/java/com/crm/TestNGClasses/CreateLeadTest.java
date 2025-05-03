package com.crm.TestNGClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.ObjectRepository.CreateNewLeadPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.LeadDetailsPage;
import com.crm.generic.ObjectRepository.LeadPage;
import com.crm.generic.baseutility.BaseClass;

@Listeners(com.crm.generic.listeners.ListenerImpClass.class)
public class CreateLeadTest extends BaseClass {
	
	@Test(retryAnalyzer = com.crm.generic.listeners.RetryAnalyzerImp.class)
	public void createLead() throws Throwable {
			
		int ran = java.getRandomNum();
		HomePage home = new HomePage(driver);
		home.getLead().click();
		
		LeadPage lead = new LeadPage(driver);
		lead.getLeadPlusSign().click();
		
		String lead_last = exc.getDataFromExcelFile("Sheet1", 4, 0)+ran;
		String lead_comp = exc.getDataFromExcelFile("Sheet1", 5, 0)+ran;
		
		CreateNewLeadPage newLead = new CreateNewLeadPage(driver);
		newLead.createNewLead(lead_last, lead_comp);
		
		LeadDetailsPage det = new LeadDetailsPage(driver);
		String lastName = det.getVerifyLastName().getText().trim();
		String compName = det.getVerifyComp().getText().trim();
		
		Assert.assertEquals(lead_last, lastName);
		Assert.assertEquals(lead_comp, compName);

	}
	
	@Test
	public void searchLead() throws Throwable {
			
		int ran = java.getRandomNum();
		
		HomePage home = new HomePage(driver);
		home.getLead().click();
		
		LeadPage lead = new LeadPage(driver);
		lead.getLeadPlusSign().click();
		
		String lead_last = exc.getDataFromExcelFile("Sheet1", 4, 0)+ran;
		String lead_comp = exc.getDataFromExcelFile("Sheet1", 5, 0)+ran;
		
		CreateNewLeadPage newLead = new CreateNewLeadPage(driver);
		newLead.createNewLead(lead_last, lead_comp);
		home.getLead().click();
		
		WebElement ele = lead.getSearch();
		web.selectByvalue(ele, "lastname");
		lead.getSearchText().sendKeys(lead_last);
		
		lead.getSearchBtn().click();
		String searchText = driver.findElement(By.xpath("//a[text()='"+lead_last+"']")).getText();
		soft.assertEquals(searchText, lead_last);
		soft.assertAll();
	}
	
	@Test
	public void deleteLead() throws Throwable {
		
		int ran = java.getRandomNum();
		
		HomePage home = new HomePage(driver);
		home.getLead().click();
		
		LeadPage lead = new LeadPage(driver);
		lead.getLeadPlusSign().click();
		
		String lead_last = exc.getDataFromExcelFile("Sheet1", 4, 0)+ran;
		String lead_comp = exc.getDataFromExcelFile("Sheet1", 5, 0)+ran;
		
		CreateNewLeadPage newLead = new CreateNewLeadPage(driver);
		newLead.createNewLead(lead_last, lead_comp);
		
		home.getLead().click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+lead_last+"']/../../td[10]/a[text()='del']")).click();
		web.alertAccept(driver);

	}

}
