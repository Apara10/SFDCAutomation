package com.sdfc.automation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class BlockingAnEventByWeeklyRecurranceTest extends LaunchWebBrowser {
	public static boolean isAlreadyLogIn = false;
	public static WebDriver driver = null;

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
		String timexpath = "//table[@id='calTable']//td[contains(@class,'fixedTable')]//div//a[contains(text(),'4:00 PM')]";
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
		endTime.sendKeys("7:00 PM");
		WebElement recurrencecheck = WaitUtility.waitForElementVisible(driver,
				By.xpath(" //input[@id='IsRecurrence']"));
		if (!recurrencecheck.isSelected()) {
			recurrencecheck.click();
		}
		Thread.sleep(2000);
		WebElement weekelyRadio = driver.findElement(By.xpath("//input[@id='rectypeftw']"));
		weekelyRadio.click();
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int today = calendar.get(Calendar.DATE);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		calendar.add(Calendar.DAY_OF_YEAR, 13);
		int aDay = calendar.get(Calendar.DATE);
		int aMonth = calendar.get(Calendar.MONTH);
		int ayear = calendar.get(Calendar.YEAR);
		WebElement endateTime = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='EndDateTime']"));
		endateTime.click();

		Thread.sleep(1000);
		if (month != aMonth) {
			driver.findElement(By.xpath("//div[@id='datePicker']//img[@class='calRight']")).click();
			Thread.sleep(1000);
		}
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='calDays']/tbody/tr/td"));
		// WebElement
		// tdlinkofcurrentDay=driver.findElement(By.xpath("//table[@class='calDays']//tr//td[text(),'"+today+"']"));
		for (int i = 0; i < list.size(); i++) {
			if (driver.findElement(By.xpath("//table[@class='calDays']//tr//td[text()='" + aDay + "']")).getText()
					.equals(Integer.toString(aDay))) {
				WebElement sday = driver
						.findElement(By.xpath("//table[@class='calDays']//tr//td[text()='" + aDay + "']"));
				System.out.println(sday);
				sday.click();
				break;
			}
		}
		System.out.println("hi");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']")).click();
		WebElement monthview = WaitUtility.waitForElementVisible(driver, By.xpath("//img[@class='monthViewIcon']"));
		monthview.click();
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
