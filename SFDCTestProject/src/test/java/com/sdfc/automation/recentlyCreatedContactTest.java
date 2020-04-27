package com.sdfc.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;
public class recentlyCreatedContactTest extends LaunchWebBrowser {

	public static void main(String[] args) throws Exception {
		recentlyCreatedContact(isAlreadyLogIn);
	}

	public static void recentlyCreatedContact(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement contactTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Contacts')]"));
		contactTab.click();
		System.out.println("Contact  Tab clicked");
		Thread.sleep(3000);
		WebElement modelist = driver.findElement(By.xpath("//select[@id='hotlist_mode']"));
		Select select = new Select(modelist);
		select.selectByVisibleText("Recently Created");
		System.out.println("Recently Created contacts displayed in the recent conatct page");
		isLoggedIn = logoutOfApp(driver, isLoggedIn);
		Thread.sleep(1000);
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
