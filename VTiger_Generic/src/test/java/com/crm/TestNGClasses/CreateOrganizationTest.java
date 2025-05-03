package com.crm.TestNGClasses;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.ObjectRepository.CreateNewOrganizationPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.OrganizationInformationPage;
import com.crm.generic.ObjectRepository.OrganizationsPage;
import com.crm.generic.baseutility.BaseClass;

@Listeners(com.crm.generic.listeners.ListenerImpClass.class)
/*
 * author mamatha
 * 
 */
public class CreateOrganizationTest extends BaseClass {
	
	@Test(groups = "regression") //retryAnalyzer = com.crm.generic.listeners.RetryAnalyzerImp.class)
	public void createOrganization() throws Throwable {
				
		HomePage home = new HomePage(driver);
		home.getOrganization().click();

		OrganizationsPage orgPage = new OrganizationsPage(driver);
		orgPage.getPlusSign().click();
		
		int ran_num = java.getRandomNum();
		
		String orgName = exc.getDataFromExcelFile("Sheet1", 0, 0) +ran_num;
		
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(driver);
		newOrg.createOrg(orgName);
		
		//validation
		OrganizationInformationPage info = new OrganizationInformationPage(driver);
//		String org_var = info.getVerifyHeader().getText();
//		Assert.assertEquals(org_var, orgName);
		
		String org_nam = info.getVerifyOrgName().getText();
		Assert.assertEquals(org_nam, orgName);	
	}
	
	@Test
	public void createOrgaWithPhone() throws Throwable {
		
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
			
		OrganizationInformationPage info = new OrganizationInformationPage(driver);
		String org_nam = info.getVerifyOrgName().getText();
		Assert.assertEquals(org_nam, orgName);
		
		String ph = info.getVerifyPhone().getText();
		soft.assertEquals(ph, phoneNum);
		soft.assertAll();
		
	}
	
	@Test
	public void createOrgWithIndustryAndType() throws Throwable {
		
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
//		String org_var = driver.findElement(By.className("dvHeaderText")).getText();
//		String result1 = java.validation(org_var, orgName);
//		System.out.println(orgName +"is"+ result1);
		
		OrganizationInformationPage info = new OrganizationInformationPage(driver);
		String org_nam = info.getVerifyOrgName().getText();
		Assert.assertEquals(org_nam, orgName);
		
		String indus = info.getVerifyIndus().getText();
		soft.assertEquals(indus, indust);
		
		String type = driver.findElement(By.id("dtlview_Type")).getText();
		soft.assertEquals(type, typ);
		soft.assertAll();
	}

}
