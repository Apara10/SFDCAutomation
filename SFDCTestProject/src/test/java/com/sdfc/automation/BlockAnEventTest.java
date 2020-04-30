package com.sdfc.automation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class BlockAnEventTest extends LaunchWebBrowser {

	public static void main(String[] args) throws Exception {
         VeriBlockingAnEvent(isAlreadyLogIn);
	}
	private static void VeriBlockingAnEvent(boolean isAlreadyLogIn2) throws Exception {
		isAlreadyLogIn2 = launchApp();
		System.out.println("Logged in: " + isAlreadyLogIn2);
		WebElement homeTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Home')]"));
		homeTab.click();
		String currentHandle= driver.getWindowHandle();
		driver.switchTo().window(currentHandle);
		String userName=driver.findElement(By.xpath("//h1[@class='currentStatusUserName']//a")).getText();
		System.out.println("Home Page for "+userName+"is displayed");
		Thread.sleep(2000);
		WebElement datelink = driver.findElement(By.xpath(" //span[@class='pageDescription']//a"));
		System.out.println("Date Displayed:"+datelink.getText());
		datelink.click();
		Thread.sleep(2000);
		String expectedText=driver.findElement(By.xpath("//h1[contains(@class,'pageType')]")).getText();
		StringBuilder sbexpectedname=new StringBuilder(expectedText);
		if(sbexpectedname.indexOf(userName)>-1) {
			System.out.println("Current username is displayed");
		}
	 
		WebElement timelink=driver.findElement(By.xpath("//a[contains(text(),'8:00 PM')]"));
		timelink.click();
			System.out.println("Time link is clicked ");
		WebElement subjectin = WaitUtility.waitForElementVisible(driver, By.xpath("//img[@class='comboboxIcon']"));
		// WebElement subjectin = WaitUtility.waitForElementVisible(driver,By.xpath("//input[contains(@name,'evt5')]"));
		subjectin.click();

		ArrayList<String> windowTabs = new ArrayList(driver.getWindowHandles());
		for (String s : windowTabs) {
			if (!s.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(s);
				Thread.sleep(1000);
				System.out.println("Title of the new window: " + driver.getTitle());
				WebElement other = driver.findElement(By.xpath("//a[contains(text(),'Other')]"));
				other.click();
			}
		}
		driver.switchTo().window(currentHandle);
		Thread.sleep(1000);
		Actions action=new Actions(driver);
		WebElement endTime = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
		endTime.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", endTime);
	  action.moveToElement(endTime).click().build().perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']")).click();
		Thread.sleep(3000);
		isAlreadyLogIn2 = logoutOfApp(driver, isAlreadyLogIn2);
		Thread.sleep(2000);
		driver.quit();

	}

	public static boolean logoutOfApp(WebDriver driver, boolean isLoggedIn) throws Exception {
		if (isLoggedIn) {
			WebElement userNavigationlinkEle = WaitUtility.waitForElementVisible(driver, By.id("userNav-arrow"));
			userNavigationlinkEle.click();
			WebElement logoutLink = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
			Thread.sleep(2000);
			logoutLink.click();
			isLoggedIn = false;
			System.out.println("TestCase Passed: logout Succesfully");

		} else {
			System.out.println("TestCase Failed:Logout ");
		}

		return isLoggedIn;

	}

	public static boolean launchApp() {
		driver = LaunchWebBrowser.loadBrowser();
		driver.get(loginUrl);
		WaitUtility.waitForPageTitle(driver, driver.getTitle());
		String title = driver.getTitle();

		if (title.equalsIgnoreCase("Login | Salesforce")) {
			System.out.println("SFDC login page is opened   " + title);
			System.out.println("TestCase Passed: SFDC login page is opened.");
		} else {
			System.out.println("LaunchBrowser Tescase falied.");
		}
		WebElement userNameEle = WaitUtility.waitForElementVisible(driver, By.id("username"));
		WebElement passwordEle = WaitUtility.waitForElementVisible(driver, By.id("password"));
		WebElement loginButtonEle = WaitUtility.waitForElementVisible(driver, By.id("Login"));
		WebElement rememberMeEle = WaitUtility.waitForElementVisible(driver, By.id("rememberUn"));
		userNameEle.sendKeys(aUsername);
		passwordEle.sendKeys(aPassword);
		rememberMeEle.click();
		loginButtonEle.click();
		String actualUrl = driver.getCurrentUrl();
		System.out.println(driver.getTitle());
		if (driver.getTitle().equalsIgnoreCase(homePageTitle)) {
			isAlreadyLogIn = true;
			System.out.println("TestCase Passed  :Sales force home page is displayed");
		} else
			System.out.println("login testcase passed:Home Page Displayed");
		return isAlreadyLogIn;
	}


}
