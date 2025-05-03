package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadPage {
	WebDriver driver;
	public LeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//img[@title='Create Lead...']")
	private WebElement leadPlusSign;
	
	@FindBy(id ="bas_searchfield")
	private WebElement search;
	
	@FindBy(name ="search_text")
	private WebElement searchText;
	
	@FindBy(xpath ="//input[@value=' Search Now ']")
	private WebElement searchBtn;
	
	public WebElement getSearch() {
		return search;
	}

	public WebElement getSearchText() {
		return searchText;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getLeadPlusSign() {
		return leadPlusSign;
	}
	
}
