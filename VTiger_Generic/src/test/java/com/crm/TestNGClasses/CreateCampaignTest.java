
package com.crm.TestNGClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.ObjectRepository.CampInformationPage;
import com.crm.generic.ObjectRepository.CampaignPage;
import com.crm.generic.ObjectRepository.CreateNewCampaignPage;
import com.crm.generic.ObjectRepository.HomePage;
import com.crm.generic.baseutility.BaseClass;

@Listeners(com.crm.generic.listeners.ListenerImpClass.class)
public class CreateCampaignTest extends BaseClass{
	@Test(groups = "smoke")// retryAnalyzer = com.crm.generic.listeners.RetryAnalyzerImp.class)
	public void createCampaign() throws Throwable {
		
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
		
		CampInformationPage campInfo = new CampInformationPage(driver);
		
		//validation
		String actual = campInfo.getverifyCampName().getText();
		Assert.assertEquals(actual, Camp);

	}

}
