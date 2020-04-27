package com.sdfc.login;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class LeadTabTesting extends LaunchWebBrowser {

	static boolean isAlreadyLogIn = false;
	static WebDriver driver = null;

	public static void main(String[] args) throws Exception {

		// TC20-leadsTab
		clickLeadTab(isAlreadyLogIn);
		// TC21-leadsSelectView
		validateDropDown(isAlreadyLogIn);
		// TC22-defaultView
		functionOfGoButton(isAlreadyLogIn);
		// List item "Todays Leads" work
		listOtemTodaysLead(isAlreadyLogIn);
		// Check "New" button on Leads Home
		checkNewBtnOnLeadHome(isAlreadyLogIn);

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

	public static void clickLeadTab(boolean isAlreadyLogIn2) throws Exception {
		isAlreadyLogIn2 = launchApp();
		WebElement leadTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Leads')]"));
		leadTab.click();
		Thread.sleep(1000);
		logoutOfApp(driver, isAlreadyLogIn2);
		Thread.sleep(2000);
		driver.quit();
	}

	public static void validateDropDown(boolean isAlreadyLogIn2) throws Exception {
		isAlreadyLogIn2 = launchApp();
		WebElement leadTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Leads')]"));
		leadTab.click();
		WebElement selectviewlist = WaitUtility.waitForElementVisible(driver, By.xpath("//select[@id='fcf']"));
		System.out.println(selectviewlist.getText());
		Thread.sleep(1000);
		logoutOfApp(driver, isAlreadyLogIn2);
		Thread.sleep(2000);
		driver.quit();

	}

	public static void functionOfGoButton(boolean isLoggedin) throws Exception {
		isLoggedin = launchApp();
		WebElement leadTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Leads')]"));
		leadTab.click();
		WebElement selectviewlist = WaitUtility.waitForElementVisible(driver, By.xpath("//select[@id='fcf']"));
		Select selects = new Select(selectviewlist);
		selects.selectByVisibleText("My Unread Leads");
		isLoggedin = logoutOfApp(driver, isLoggedin);
		Thread.sleep(2000);
		driver.quit();
		isLoggedin = launchApp();
		WebElement leadTab1 = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Leads')]"));
		leadTab1.click();
		WebElement goButton = WaitUtility.waitForElementVisible(driver,
				By.xpath("//span[@class='fBody']//input[@name='go']"));
		Thread.sleep(1000);
		goButton.click();
		System.out.println("Functionality of the Go Button\r\n:" + "Todays's Lead page should be displayed.");
		Thread.sleep(2000);
		logoutOfApp(driver, isLoggedin);
		Thread.sleep(2000);
		driver.quit();
	}

	public static void listOtemTodaysLead(boolean isLoggedin) throws Exception {
		isLoggedin = launchApp();
		WebElement leadTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Leads')]"));
		leadTab.click();
		WebElement selectviewlist = WaitUtility.waitForElementVisible(driver, By.xpath("//select[@id='fcf']"));
		Select selects = new Select(selectviewlist);
		selects.selectByVisibleText("Today's Leads");
		System.out.println("List item \"Todays Leads\" work\r\n" + "" + "Todays's Lead page should be displayed.");
		isLoggedin = logoutOfApp(driver, isLoggedin);
		Thread.sleep(2000);
		driver.quit();

	}

	public static void checkNewBtnOnLeadHome(boolean isLoggedin) throws Exception {
		isLoggedin = launchApp();
		WebElement leadTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Leads')]"));
		leadTab.click();
		WebElement newBtn = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@name='new']"));
		newBtn.click();
		System.out.println(driver.findElement(By.xpath("//h2[contains(@class,'pageDescription')]")));
		WebElement lastnm = WaitUtility.waitForElementVisible(driver,
				By.xpath("//input[contains(@name,'name_lastlea2')]"));
		lastnm.sendKeys("ABCD");
		WebElement companyNm = WaitUtility.waitForElementVisible(driver, By.xpath("//input[contains(@name,'lea3')]"));
		companyNm.sendKeys("ABCD");
		WebElement saveBtn = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[contains(@class,'pbBottomButtons')]//input[@name='save']"));
		saveBtn.click();
		Thread.sleep(1000);
		WebElement topName = driver.findElement(By.xpath("//h2[contains(@class,'topName')]"));
		System.out.println("New Lead Should be saved:" + topName.equals("ABCD"));
		System.out.println(" lead view page is opened");
		isLoggedin = logoutOfApp(driver, isLoggedin);
		Thread.sleep(2000);
		driver.quit();

	}
}
