package com.sdfc.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class VerifyTheEditedLastNameTest extends LaunchWebBrowser {

	public static boolean isAlreadyLogIn = false;
	public static WebDriver driver = null;
	public static void main(String[] args) throws Exception {
	verifyEditedLastName(isAlreadyLogIn);
	System.out.println("VerifyTheEditedLastNameTest cpmpleted");

	}
	private static void verifyEditedLastName(boolean isAlreadyLogIn2) throws Exception {
		isAlreadyLogIn2 = launchApp();
		  WebElement homeTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Home')]"));
		  homeTab.click();
			System.out.println("Home  Tab clicked");
			Thread.sleep(2000);
			WebElement ownerNamelink=WaitUtility.waitForElementVisible(driver, By.xpath("//h1[@class='currentStatusUserName']"));
			ownerNamelink.click();
			System.out.println("Profile page is being displayed"+driver.getTitle());
			
			WebElement editProfileLink=WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(@class,'contactInfoLaunch editLink')]//img"));
			editProfileLink.click();
			Thread.sleep(2000);
			driver.switchTo().frame("contactInfoContentId");
			WebElement contacttab=driver.findElement(By.xpath(".//a[contains(text(),'Contact')]"));
			if(contacttab.isSelected()) {
				System.out.println("Contact Tab is selected");
			}
			WebElement aboutTab=WaitUtility.waitForElementVisible(driver, By.xpath(".//a[contains(text(),'About')]"));
			aboutTab.click();
			 WebElement lastnameele=WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='lastName']"));
			 lastnameele.clear();
			 lastnameele.sendKeys("Abcd");
				WebElement saveAllButtEle=WaitUtility.waitForElementVisible(driver, By.xpath("//input[contains(@class,'zen-btn zen-primaryBtn zen-pas')]"));
			   	saveAllButtEle.click();
			   	driver.switchTo().defaultContent();
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
