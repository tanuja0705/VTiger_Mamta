package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadDetailsPage {
	public LeadDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="mouseArea_Last Name")
	private WebElement verifyLastName;
	
	@FindBy(id="mouseArea_Company")
	private WebElement verifyComp;

	public WebElement getVerifyLastName() {
		return verifyLastName;
	}

	public WebElement getVerifyComp() {
		return verifyComp;
	}

}
