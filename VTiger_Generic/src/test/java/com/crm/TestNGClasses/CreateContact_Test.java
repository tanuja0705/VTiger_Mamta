package com.crm.TestNGClasses;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.crm.generic.ObjectRepository.ContactInformationPage;
import com.crm.generic.ObjectRepository.ContactsPage;
import com.crm.generic.ObjectRepository.CreateNewContactPage;
import com.crm.generic.ObjectRepository.CreateNewOrganizationPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.ObjectRepository.OrganizationsPage;
import com.crm.generic.baseutility.BaseClass;

@Listeners(com.crm.generic.listeners.ListenerImpClass.class)
public class CreateContact_Test extends BaseClass {
	@Test(groups = "smoke", retryAnalyzer = com.crm.generic.listeners.RetryAnalyzerImp.class)
	public void createContactWithLastName() throws Throwable {
		
		HomePage home = new HomePage(driver);
		home.getContacts().click();
		
		ContactsPage cont = new ContactsPage(driver);
		cont.getPlusSign().click();
		
		int ran_num = java.getRandomNum();
		
		String lastName = exc.getDataFromExcelFile("Sheet1", 1, 0) +ran_num;
		
		CreateNewContactPage createCon = new CreateNewContactPage(driver);
		createCon.createContact(lastName);
		
		ContactInformationPage info = new ContactInformationPage(driver);
		
		String last_name = info.getVerifyLastName().getText();
		Assert.assertEquals(last_name, lastName);
//		String result2 = java.validation(last_name, lastName);
//		System.out.println(last_name +"is "+ result2);

	}
	
	@Test
	public void createContactWithSupportDate() throws Throwable {
				
		HomePage home = new HomePage(driver);
		home.getContacts().click();
		
		ContactsPage cont = new ContactsPage(driver);
		cont.getPlusSign().click();
		
		int ran_num = java.getRandomNum();
		
		String value1 = exc.getDataFromExcelFile("Sheet1", 1, 0) +ran_num;
		String str_date = java.getSystemDate();
		String end_date = java.getEndDate(30);
		
		CreateNewContactPage createCon = new CreateNewContactPage(driver);
		
		createCon.createContact(value1 ,str_date, end_date);
		
		//validation
		
		ContactInformationPage info = new ContactInformationPage(driver);
		
		String last_name = info.getVerifyLastName().getText();
		soft.assertEquals(last_name, value1);
				
		String start = info.getVerifyStartDate().getText();
		soft.assertEquals(start, str_date);
		
		String end = info.getVerifyEndDate().getText();
		soft.assertEquals(end, end_date);

	}
	
	@Test
	public void createContactWithOrg() throws Throwable {
		
		HomePage home = new HomePage(driver);
		home.getOrganization().click();
		
		OrganizationsPage orgPage = new OrganizationsPage(driver);
		orgPage.getPlusSign().click();
		
		int ran_num = java.getRandomNum();
		
		String orgName = exc.getDataFromExcelFile("Sheet1", 0, 0) +ran_num;
		CreateNewOrganizationPage newOrg = new CreateNewOrganizationPage(driver);
		newOrg.createOrg(orgName);
		
		Thread.sleep(2000);
		
		home.getContacts().click();
		ContactsPage cont = new ContactsPage(driver);
		cont.getPlusSign().click();
		
		String value2 = exc.getDataFromExcelFile("Sheet1", 1, 0) +ran_num;
		
		CreateNewContactPage createCon = new CreateNewContactPage(driver);
		createCon.createContact(value2, orgName, driver);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		web.switchingWindow(driver, "Contacts&action");
		createCon.getSaveBtn().click();
		
		ContactInformationPage info = new ContactInformationPage(driver);
		
		String actual_org_name = info.getVerifyOrganization().getText().trim();
		soft.assertNotEquals(actual_org_name, orgName);

	}
}
