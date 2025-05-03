package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampInformationPage {
	
	public CampInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "dtlview_Campaign Name") private WebElement verifyCampName;

	public WebElement getverifyCampName() {
		return verifyCampName;
	}

}
