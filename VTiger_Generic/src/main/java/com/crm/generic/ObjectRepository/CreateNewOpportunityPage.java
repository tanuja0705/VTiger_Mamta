package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOpportunityPage {
	
	WebDriver driver;
	public CreateNewOpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "potentialname")
	private WebElement potenName;
	
	@FindBy(xpath = "//input[@id='related_to_display']//following-sibling::img")
	private WebElement related;
	
	@FindBy(name = "search_text")
	private WebElement searchTab;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButn;
	
	public WebElement getPotenName() {
		return potenName;
	}

	public WebElement getRelated() {
		return related;
	}

	public WebElement getSearchTab() {
		return searchTab;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSaveButn() {
		return saveButn;
	}
	
	public void searchOrg(String org) {
		searchTab.sendKeys(org);
		searchBtn.click();
	}

}
