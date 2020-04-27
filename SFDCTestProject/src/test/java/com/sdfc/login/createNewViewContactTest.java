package com.sdfc.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class createNewViewContactTest extends LaunchWebBrowser {
	static boolean isAlreadyLogIn = false;
	static WebDriver driver = null;

	public static void main(String[] args) throws Exception {
		createNewViewContact(isAlreadyLogIn);

	}

	public static void createNewViewContact(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement contactTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Contacts')]"));
		contactTab.click();
		System.out.println("Contact  Tab clicked");
		Thread.sleep(3000);
		// System.out
		// .println("homecontactPage:" +
		// driver.findElement(By.xpath("//h2[contains(@class,'pageDescription')]")));
		WebElement viewLink = WaitUtility.waitForElementVisible(driver,
				By.xpath(" //a[contains(text(),'Create New View')]"));
		viewLink.click();
		WebElement viewName1 = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='fname']"));
		viewName1.sendKeys(viewName);
		Thread.sleep(2000);
		WebElement viewUName2 = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='devname']"));
		viewUName2.click();
		WebElement saveBtn = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		Thread.sleep(2000);
		saveBtn.click();
		WebElement selectview = WaitUtility.waitForElementVisible(driver, By.xpath("	//select[@name='fcf']"));
		Select selects = new Select(selectview);

		if (selects.getFirstSelectedOption().getText().equals(viewName)) {
			System.out.println("Newely added View  displayed in the contact view list");
		} else
			System.out.println("Newely added not displayed in the contact  view list");
		System.out.println("create new view is ended");
		isLoggedIn = logoutOfApp(driver, isLoggedIn);
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
