package com.sdfc.automation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
		System.out.println("Home  Tab clicked");
		Thread.sleep(4000);
		WebElement datelink = driver.findElement(By.xpath(" //a[contains(text(),'Monday April 27, 2020')]"));
		datelink.click();
		Thread.sleep(2000);
		String timexpath = "//table[@id='calTable']//td[contains(@class,'fixedTable')]//div//a[contains(text(),'8:00 PM')]";
		WebElement timeEle = WaitUtility.waitForElementVisible(driver, By.xpath(timexpath));
		timeEle.click();
		String currentHandle = driver.getWindowHandle();
		WebElement subjectin = WaitUtility.waitForElementVisible(driver, By.xpath("//img[@class='comboboxIcon']"));
		// WebElement subjectin = WaitUtility.waitForElementVisible(driver,
		// By.xpath("//input[contains(@name,'evt5')]"));
		subjectin.click();

		ArrayList<String> windowTabs = new ArrayList(driver.getWindowHandles());
		for (String s : windowTabs) {
			if (!s.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(s);
				Thread.sleep(1000);
				System.out.println("Title of the new window: " + driver.getTitle());
				Thread.sleep(2000);
				WebElement other = driver.findElement(By.xpath("//a[contains(text(),'Other')]"));
				other.click();
			}
		}
		driver.switchTo().window(currentHandle);
		Thread.sleep(1000);
		WebElement endTime = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
		endTime.clear();
		Thread.sleep(1000);
		endTime.sendKeys("9:00 PM");
		Thread.sleep(2000);
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
