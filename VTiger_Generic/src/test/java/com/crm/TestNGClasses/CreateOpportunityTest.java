package com.crm.TestNGClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.ObjectRepository.CreateNewOpportunityPage;
import com.crm.generic.ObjectRepository.CreateNewOrganizationPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.OpporunityInformationPage;
import com.crm.generic.ObjectRepository.OrganizationsPage;
import com.crm.generic.baseutility.BaseClass;

@Listeners(com.crm.generic.listeners.ListenerImpClass.class)
public class CreateOpportunityTest extends BaseClass{
	
	@Test(retryAnalyzer = com.crm.generic.listeners.RetryAnalyzerImp.class)
	public void createOpporTest() throws Throwable {
				
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
		
		OpporunityInformationPage info = new OpporunityInformationPage(driver);
		
		String orgname = info.getVerifyOrgName().getText();
		Assert.assertEquals(orgname, orgName);
		
		String oppname = info.getVerifyOppName().getText();
		soft.assertEquals(oppname, opp_name);
		soft.assertAll();

	}

}
