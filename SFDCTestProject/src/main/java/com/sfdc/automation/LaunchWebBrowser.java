package com.sfdc.automation;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchWebBrowser {
	protected static String wrongUserName;
	protected static String wrongPassword;
	protected static String aUsername;
	protected static String aPassword;
	protected static String aHomeUrl;
	protected  static String actualTitle;
	protected static String resetMsg;
	protected static String loginUrl;
	protected static String errorLoginMsg;
	protected static String uName;
	protected static String homePageTitle;
	protected static String lastNameChanged;
	protected static String profilePost;
	protected static String selectCustomiseTab;
	protected static String report;
	protected static String emailName;
	protected static String emailAddress;
	protected static String reportName;
	protected static String reportUName;
	protected static String currentANextFQ;
	protected static String openOpportunities;
	protected static String lastNameContact;
	protected static String accountNameConatct;
	protected static String accountType;
	protected static String customerPriority;
	protected static String viewName;
	protected static String fullname;
	protected static String newViewName;
	protected static String accountNameF;
	protected static String containsO;
	protected static String viewValue;
	protected static String accountNameConatct1;
	
	public static boolean isAlreadyLogIn = false;
	public static WebDriver driver=null;
	
	static Properties prop=PropertiesFileReader.getProperties();
	
	public static  WebDriver  loadBrowser() {
		LaunchWebBrowser.loadProperties(prop);
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();// maximize window
		driver.manage().deleteAllCookies();// delete all cookies
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	public static void loadProperties(Properties prop) {
		wrongUserName=prop.getProperty("wrongUserName");
		wrongPassword=prop.getProperty("wrongPassword");
		aUsername = prop.getProperty("username");
		aPassword=prop.getProperty("password");
		loginUrl = prop.getProperty("loginurl");
		aHomeUrl= prop.getProperty("homeurl");
		actualTitle=prop.getProperty("ForGotPwdTitle");
		resetMsg=prop.getProperty("resetPwdMsg");
		errorLoginMsg=prop.getProperty("errorLoginMsg");
		uName=prop.getProperty("uName");
		homePageTitle=prop.getProperty("homePageTitle");
		lastNameChanged=prop.getProperty("lastNameChanged");
		profilePost=prop.getProperty("profilePost");
		selectCustomiseTab=prop.getProperty("SelectCustomiseTab");
		report=prop.getProperty("reportsSelected");
		emailName=prop.getProperty("EmailName");
		emailAddress=prop.getProperty("EmailAddress");
		reportUName=prop.getProperty("reportUName");
		reportName=prop.getProperty("reportName");
		currentANextFQ=prop.getProperty("currentANextFQ");
		openOpportunities=prop.getProperty("openOpportunities");
		lastNameContact=prop.getProperty("lastNameContact");
		accountNameConatct=prop.getProperty("accountNameConatct");
		accountNameConatct1=prop.getProperty("accountNameConatct1");
		accountType=prop.getProperty("accountType");
		customerPriority=prop.getProperty("customerPriority");
		customerPriority=prop.getProperty("customerPriority");
		viewName=prop.getProperty("viewName");
		fullname=prop.getProperty("fullname");
		newViewName=prop.getProperty("viewName");
		accountNameF=prop.getProperty("accountNameF");
		containsO=prop.getProperty("containsO");
		viewValue=prop.getProperty("viewValue");
		

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
