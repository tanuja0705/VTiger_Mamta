package com.crm.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProductPage {
	
	WebDriver driver;
	public EditProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="unit_price")
	private WebElement price;
	
	public WebElement getPrice() {
		return price;
	}
	
	public void editUnitPrice(String value) {
		price.clear();
		price.sendKeys(value);
	}

}
