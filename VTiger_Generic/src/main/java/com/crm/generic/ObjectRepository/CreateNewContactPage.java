package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriverUtility.WebDriverUtility;


public class CreateNewContactPage {
	WebDriverUtility web = new WebDriverUtility();
	
	WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastName;

	@FindBy(name = "support_start_date")
	private WebElement suppStartDate;
	
	@FindBy(name = "support_end_date")
	private WebElement suppEndDate;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//input[@name='account_name']//following-sibling::img")
	private WebElement orgPlusSign;
	
	@FindBy(name = "search_text")
	private WebElement searchField;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getOrgPlusSign() {
		return orgPlusSign;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getLastName() {
		return lastName;
	}
	
	public WebElement getSuppStartDate() {
		return suppStartDate;
	}

	public WebElement getSuppEndDate() {
		return suppEndDate;
	}
	
	public void createContact(String lastname) {
		lastName.sendKeys(lastname);
		saveBtn.click();
	}
	
	public void createContact(String lastname, String startDate, String endDate) {
		lastName.sendKeys(lastname);
		suppStartDate.clear();
		suppStartDate.sendKeys(startDate);
		suppEndDate.clear();
		suppEndDate.sendKeys(endDate);
		saveBtn.click();
	}
	
	public void createContact(String lastname, String orgName, WebDriver driver) {
		lastName.sendKeys(lastname);
		orgPlusSign.click();
		web.switchingWindow(driver, "Accounts&action");
		searchField.sendKeys(orgName);
		searchBtn.click();
	}
}
