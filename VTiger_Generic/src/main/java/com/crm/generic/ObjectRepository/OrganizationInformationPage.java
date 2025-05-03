package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText") private WebElement verifyHeader;
	@FindBy(id = "dtlview_Organization Name") private WebElement verifyOrgName;
	@FindBy(id = "dtlview_Phone") private WebElement verifyPhone;
	@FindBy(id = "dtlview_Industry") private WebElement verifyIndus;
	@FindBy(id = "dtlview_Type") private WebElement verifyType;
	
	public WebElement getVerifyIndus() {
		return verifyIndus;
	}
	public WebElement getVerifyType() {
		return verifyType;
	}
	public WebElement getVerifyPhone() {
		return verifyPhone;
	}
	public WebElement getVerifyHeader() {
		return verifyHeader;
	}
	public WebElement getVerifyOrgName() {
		return verifyOrgName;
	}
	
}
