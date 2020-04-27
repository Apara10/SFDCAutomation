package com.sdfc.automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class verifyTheTabCustomisationTest extends LaunchWebBrowser {
	public static boolean isAlreadyLogIn = false;
	public static WebDriver driver = null;

	public static void main(String[] args) throws Exception {
		verifyTheTabCustomisation(isAlreadyLogIn);
		launchApp();
		List<WebElement> tabs1 = driver.findElements(By.xpath("//ul[@id='tabBar']//li"));
		Thread.sleep(3000);
		Boolean found1 = false;
		for (WebElement tabb : tabs1) {
			if (tabb.getText().equals("Chatter")) {
				found1 = true;
				System.out.println("the selected tab is stil there");
				break;
			}
		}
		Thread.sleep(2000);
		driver.quit();

	}

	private static void verifyTheTabCustomisation(boolean isAlreadyLogIn2) throws Exception {
		isAlreadyLogIn2 = launchApp();

		WebElement plusTab = WaitUtility.waitForElementVisible(driver,
				By.xpath("//a//img[contains(@class,'allTabsArrow')]"));
		plusTab.click();
		Thread.sleep(1000);
		WebElement customizedTab = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@name='customize']"));
		customizedTab.click();
		List<WebElement> optons = driver.findElements(By.xpath("//select[@id='duel_select_1']//options"));
		for (WebElement opt : optons) {
			if (opt.getText().equalsIgnoreCase("Chatter")) {
				WebElement selectedTab =driver.findElement(By.xpath("//select[@id='duel_select_1']"));
				Select selectedoptions = new Select(selectedTab);
				selectedoptions.selectByVisibleText("Chatter");
				System.out.println("Selected tab is there");
				WebElement removeButtLink = driver.findElement(By.xpath("//a[@id='duel_select_0_left']"));
				removeButtLink.click();
			}
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[contains(@name,'save')]")).click();
		List<WebElement> tabs= driver.findElements(By.xpath("//ul[@id='tabBar']//li"));
		Thread.sleep(3000);
		Boolean found = false;
		
	//	for (WebElement tab : tabs) {
	//		if (tab.getText().equalsIgnoreCase("Chatter")) {
	//			System.out.println("the selected tab is not there");
	//		}
	//		isAlreadyLogIn2 = logoutOfApp(driver, isAlreadyLogIn2);
		//	Thread.sleep(2000);
		
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
