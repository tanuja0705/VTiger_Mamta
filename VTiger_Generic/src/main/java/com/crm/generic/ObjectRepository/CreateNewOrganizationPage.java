package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateNewOrganizationPage {
	
	WebDriverUtility web = new WebDriverUtility();
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement organizationName;
	
	@FindBy(id = "phone")
	private WebElement phName;
	
	@FindBy(name = "industry")
	private WebElement industry;
	
	@FindBy(name = "accounttype")
	private WebElement type;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public WebElement getOrganizationName() {
		return organizationName;
	}

	public WebElement getPhName() {
		return phName;
	}

	public WebElement getIndustry() {
		return industry;
	}

	public WebElement getType() {
		return type;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	//business logic
	public void createOrg(String orgName) {
		organizationName.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String phone) {
		organizationName.sendKeys(orgName);
		phName.sendKeys(phone);
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String indValue, String tyValue) {
		organizationName.sendKeys(orgName);
		web.selectByvisible(industry, indValue);
		web.selectByvisible(type, tyValue);
		saveBtn.click();
	}

}
