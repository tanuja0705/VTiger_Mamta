package com.crm.generic.webdriverUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void getMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void explicitWait(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void switchingWindow(WebDriver driver, String title) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String windo_id = it.next();
			driver.switchTo().window(windo_id);
			
			String currentUrl = driver.getCurrentUrl();
			if(currentUrl.contains(title)) {
				break;
			}
		}
	}
	
	//Frames
	public void switchToFrame(WebDriver driver, String name) {
		driver.switchTo().frame(name);
	}
	
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, WebElement ele) {
		driver.switchTo().frame(ele);
	}
	
	//AlertPopup
	public void alertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void alertDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void alertSendkeys(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}
	
	public String alertGetText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}
	
	//SelectClass
	public void selectByindex(WebElement ele, int index) {
		Select sel = new Select(ele);
		sel.selectByIndex(index);
	}
	
	public void selectByvalue(WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByValue(value);
	}
	
	public void selectByvisible(WebElement ele, String text) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(text);
	}
	
	public WebElement toGetFirstSelect(WebElement ele) {
		Select sel = new Select(ele);
		WebElement web = sel.getFirstSelectedOption();
		return web;
	}
	
	//ActionClass
	public void moveToEle(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().perform();
	}
	
	public void mouseHover(WebDriver driver,WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
	}
	
	public void dragDrop(WebDriver driver, WebElement ele, int x, int y) {
		Actions act = new Actions(driver);
		act.dragAndDropBy(ele, x, y).perform();
	}
	
	public void sendKey(WebDriver driver, WebElement ele, String text) {
		Actions act = new Actions(driver);
		act.click(ele).sendKeys(Keys.ENTER, text);
	}
	
	public void scrollBy(WebDriver driver, int x, int y) {
		Actions act = new Actions(driver);
		act.scrollByAmount(x, y).perform();
	}
	
	public void scrolltoEle(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.scrollToElement(ele);
	}
	
	public void rightClck(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.contextClick(ele).perform();
	}
	
	//Takes Screenshot
	public void takeSSofWebPage(WebDriver driver, String file) throws IOException {
		TakesScreenshot tks = (TakesScreenshot) driver;
		File src = tks.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/'"+file+"'");
		FileHandler.copy(src, dest);
	}
	
	public void takeSSofWebEle(WebElement ele, String file) throws IOException {
		File src = ele.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/'"+file+"'");
		FileHandler.copy(src, dest);
	}
	
	//Java script Executor
	public void jseScrolldown(WebDriver driver, int x, int y) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy('"+x+"','"+y+"')");
	}
	
	public void jseClick(WebDriver driver, WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ele);
	}
	
	public void jseSendData(WebDriver driver, WebElement ele, String seq) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='"+seq+"'", ele);
	}
	

}
