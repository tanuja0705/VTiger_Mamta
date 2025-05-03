package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	
	public ContactInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="dtlview_Last Name")
	private WebElement verifyLastName;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement verifyStartDate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement verifyEndDate;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement verifyOrganization;

	public WebElement getVerifyOrganization() {
		return verifyOrganization;
	}

	public WebElement getVerifyLastName() {
		return verifyLastName;
	}

	public WebElement getVerifyStartDate() {
		return verifyStartDate;
	}

	public WebElement getVerifyEndDate() {
		return verifyEndDate;
	}

}
