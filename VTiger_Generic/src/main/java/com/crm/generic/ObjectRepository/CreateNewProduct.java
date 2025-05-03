package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProduct {
	WebDriver driver;
	public CreateNewProduct(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name ="productname")
	private WebElement prdName;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement svBtn;
	
	public WebElement getPrdName() {
		return prdName;
	}

	public WebElement getSvBtn() {
		return svBtn;
	}
	
	public void createPrd(String productName) {
		prdName.sendKeys(productName);
		svBtn.click();
	}

}
