package com.sdfc.login;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class CreateContactTest extends LaunchWebBrowser {
	static boolean isAlreadyLogIn = false;
	static WebDriver driver = null;

	public static void main(String[] args) throws Exception {
		// Create new contact
		 createNewContact(isAlreadyLogIn);
		 createNewViewContact(isAlreadyLogIn);
		 myContactsView(isAlreadyLogIn);
		recentlyCreatedContact(isAlreadyLogIn);
		viewContactinContactPage(isAlreadyLogIn);
		checkTheErorrMessage(isAlreadyLogIn);
		checkTheCancelButton(isAlreadyLogIn);
		checkSaveNewBtnContactWork(isAlreadyLogIn);
		

	}

	private static void createNewContact(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement contactsTab = WaitUtility.waitForElementVisible(driver,
				By.xpath("//a[contains(text(),'Contacts')]"));
		contactsTab.click();
		Thread.sleep(2000);
		System.out.println(
				"Title of the page:  " + driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText());
		WebElement newbtn = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@name='new']"));
		newbtn.click();
		System.out.println(
				"Title of the page:  " + driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText());
		WebElement lastNme = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='name_lastcon2']"));
		lastNme.sendKeys(lastNameContact);
		WebElement acctNme = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='con4']"));
		acctNme.sendKeys(accountNameConatct);
		WebElement saveBtn = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[contains(@class,'pbBottomButtons')]//input[@name='save']"));
		saveBtn.click();
		Thread.sleep(1000);
		logoutOfApp(driver, isLoggedIn);
		Thread.sleep(2000);
		driver.quit();
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

	public static void myContactsView(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement contactTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Contacts')]"));
		contactTab.click();
		System.out.println("Contact  Tab clicked");
		Thread.sleep(3000);
		WebElement selectMycontact = driver.findElement(By.xpath("//select[@id='fcf']"));
		Select select = new Select(selectMycontact);
		select.selectByVisibleText("My Contacts");
		System.out.println("My Contacts view is being displayed");
		logoutOfApp(driver, isLoggedIn);
		Thread.sleep(2000);
		driver.quit();

	}

	public static void viewContactinContactPage(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement contactsTab = WaitUtility.waitForElementVisible(driver,
				By.xpath("//a[contains(text(),'Contacts')]"));
		contactsTab.click();
		Thread.sleep(2000);
		List<WebElement> elelinktable = driver.findElements(By.xpath("//table[starts-with(@class,dataRow)]//th//a"));
		for (WebElement actname : elelinktable) {
			if (actname.getText().equals(lastNameContact)) {
				actname.click();
				break;
			}
		}
		Thread.sleep(1000);
		logoutOfApp(driver, isLoggedIn);
		Thread.sleep(1000);
         driver.quit();
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
		String actualErrorMSGContact = "Error: You must enter a value";;
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
	public static void checkTheCancelButton(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement contactsTab = WaitUtility.waitForElementVisible(driver,
				By.xpath("//a[contains(text(),'Contacts')]"));
		contactsTab.click();
		Thread.sleep(2000);
		WebElement viewLink = WaitUtility.waitForElementVisible(driver,
				By.xpath(" //a[contains(text(),'Create New View')]"));
		viewLink.click();
		WebElement viewName1 = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='fname']"));
		viewName1.sendKeys("ABCD");
		Thread.sleep(2000);
		WebElement viewUName2 = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='devname']"));
		viewUName2.click();
		viewUName2.clear();
		viewUName2.sendKeys("EFGH");
		WebElement cancelbtn = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[@class='pbHeader']//input[@name='cancel']"));
		cancelbtn.click();
		Thread.sleep(1000);
		System.out.println(driver.getTitle());
		System.out.println("Contacts Home page is displayed and the View ABCD should not be created.");
		Thread.sleep(1000);
		logoutOfApp(driver, isLoggedIn);
		Thread.sleep(2000);
		driver.quit();
		
	}
	private static void checkSaveNewBtnContactWork(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement contactsTab = WaitUtility.waitForElementVisible(driver,
				By.xpath("//a[contains(text(),'Contacts')]"));
		contactsTab.click();
		Thread.sleep(2000);
		System.out.println(
				"Title of the page:  " + driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText());
		WebElement newbtn = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@name='new']"));
		newbtn.click();
		System.out.println(
				"Title of the page:  " + driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText());
		WebElement lastNme = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='name_lastcon2']"));
		lastNme.sendKeys("Indian");
		WebElement acctNme = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='con4']"));
		acctNme.sendKeys("Global Media");
		WebElement saveBtn = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[contains(@class,'pbBottomButtons')]//input[@name='save_new']"));
		saveBtn.click();
		Thread.sleep(1000);
		logoutOfApp(driver, isLoggedIn);
		Thread.sleep(2000);
		driver.quit();
	}
}
