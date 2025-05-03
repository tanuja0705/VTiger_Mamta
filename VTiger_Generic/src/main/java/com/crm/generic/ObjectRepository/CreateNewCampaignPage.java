package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
	
	WebDriver driver;
	public CreateNewCampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name ="campaignname")
	private WebElement campName;
	
	@FindBy(xpath ="//input[@value='T']")
	private WebElement selectUser;
	
	@FindBy(xpath ="//input[@value='U']")
	private WebElement selectUser1;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getSelectUser1() {
		return selectUser1;
	}

	public WebElement getCampName() {
		return campName;
	}

	public WebElement getSelectUser() {
		return selectUser;
	}
	
}
