package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Organizations")
	private WebElement organization;
	
	@FindBy(linkText = "Contacts")
	private WebElement contacts;
	
	@FindBy(linkText = "More")
	private WebElement more;
	
	@FindBy(name ="Campaigns")
	private WebElement camp;
	
	@FindBy(linkText ="Leads")
	private WebElement lead;
	
	@FindBy(linkText ="Products")
	private WebElement products;
	
	public WebElement getProducts() {
		return products;
	}

	public WebElement getLead() {
		return lead;
	}

	public WebElement getCamp() {
		return camp;
	}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminLink;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signout;
	
	@FindBy(id="qccombo")
	private WebElement quickCreate;
	
	public WebElement getQuickCreate() {
		return quickCreate;
	}

	public WebElement getAdminLink() {
		return adminLink;
	}

	public WebElement getSignout() {
		return signout;
	}

	public WebElement getOrganization() {
		return organization;
	}

	public WebElement getContacts() {
		return contacts;
	}

	public WebElement getMore() {
		return more;
	}
	
	public void logout() {
		adminLink.click();
		signout.click();
	}

}
