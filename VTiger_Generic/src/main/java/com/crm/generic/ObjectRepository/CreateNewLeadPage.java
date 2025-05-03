package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewLeadPage {
	
	WebDriver driver;
	public CreateNewLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name ="lastname")
	private WebElement lastName;
	
	@FindBy(name ="company")
	private WebElement compName;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement leadSaveBtn;

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getCompName() {
		return compName;
	}

	public WebElement getLeadSaveBtn() {
		return leadSaveBtn;
	}
	
	public void createNewLead(String lastname, String compname) {
		lastName.sendKeys(lastname);
		compName.sendKeys(compname);
		leadSaveBtn.click();
	}

}
