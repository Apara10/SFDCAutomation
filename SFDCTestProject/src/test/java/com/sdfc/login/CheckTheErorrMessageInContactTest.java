package com.sdfc.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class CheckTheErorrMessageInContactTest extends LaunchWebBrowser {
	static boolean isAlreadyLogIn = false;
	static WebDriver driver = null;

	public static void main(String[] args) throws Exception {
		checkTheErorrMessage(isAlreadyLogIn);

	}

	public static void checkTheErorrMessage(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement contactsTab = WaitUtility.waitForElementVisible(driver,
				By.xpath("//a[contains(text(),'Contacts')]"));
		contactsTab.click();
		Thread.sleep(2000);
		WebElement viewLink = WaitUtility.waitForElementVisible(driver,
				By.xpath(" //a[contains(text(),'Create New View')]"));
		viewLink.click();
		WebElement viewUName2 = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='devname']"));
		viewUName2.sendKeys("EFGH");
		WebElement saveBtn = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		Thread.sleep(2000);
		saveBtn.click();
		Thread.sleep(2000);
		String actualErrorMSGContact = "Error: You must enter a value";
		;
		String errorTxt = driver.findElement(By.xpath("//input[@id='fname']//following-sibling::div")).getText();
		System.out.println(errorTxt);
		if (errorTxt.equalsIgnoreCase(actualErrorMSGContact)) {
			System.out.println("Error message is being displayed");
		} else
			System.out.println("No Error message");
		Thread.sleep(1000);

		logoutOfApp(driver, isLoggedIn);
		Thread.sleep(2000);
		driver.quit();
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

}
