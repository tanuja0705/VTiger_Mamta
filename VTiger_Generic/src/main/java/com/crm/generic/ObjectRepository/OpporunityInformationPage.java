package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpporunityInformationPage {
	
	public OpporunityInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@title='Organizations']") private WebElement verifyOrgName;
	@FindBy(id="dtlview_Opportunity Name") private WebElement verifyOppName;
	
	public WebElement getVerifyOrgName() {
		return verifyOrgName;
	}
	public WebElement getVerifyOppName() {
		return verifyOppName;
	}
	

}
