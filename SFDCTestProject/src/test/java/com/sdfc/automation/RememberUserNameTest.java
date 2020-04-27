package com.sdfc.automation;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.PropertiesFileReader;
import com.sfdc.automation.WaitUtility;

/*Test the remember username check box */

public class RememberUserNameTest extends LaunchWebBrowser {
	
	public static boolean isAlreadyLogIn = false;
	public static void main(String[] args) throws Exception {
		
		driver = LaunchWebBrowser.loadBrowser();
	    
		RememberUserNameTest rememberUserNameTest = new RememberUserNameTest();

		rememberUserNameTest.launchSDFCApplication(driver, loginUrl);
		isAlreadyLogIn = rememberUserNameTest.rememberMeCheckBox(driver, isAlreadyLogIn,aUsername,aPassword);
		rememberUserNameTest.logoutOfHomePage(driver, isAlreadyLogIn);
		Thread.sleep(3000);
		rememberUserNameTest.checkUserNameAfterLogOut(driver);
		driver.quit();
	}
	

	// Launch SFDC application
	public void launchSDFCApplication(WebDriver driver, String url) {
		// driver.get("https://login.salesforce.com");
		driver.get(loginUrl);
		WaitUtility.waitForPageTitle(driver, driver.getTitle());
		String title = driver.getTitle();

		if (title.equalsIgnoreCase("Login | Salesforce")) {
			System.out.println("SFDC login page is opened   " + title);
			System.out.println("TestCase Passed: SFDC login page is opened.");
		} else {
			System.out.println("LaunchBrowser Tescase falied.");
		}
	}

	public boolean rememberMeCheckBox(WebDriver driver, boolean isLogIn,String username,String password) {
		if (!isLogIn) {
			WebElement userNameEle = WaitUtility.waitForElementVisible(driver, By.id("username"));
			WebElement passwordEle = WaitUtility.waitForElementVisible(driver, By.id("password"));
			WebElement loginButtonEle = WaitUtility.waitForElementVisible(driver, By.id("Login"));
			WebElement rememberMeEle = WaitUtility.waitForElementVisible(driver, By.id("rememberUn"));
			userNameEle.sendKeys(username);
			passwordEle.sendKeys(password);
			rememberMeEle.click();
			loginButtonEle.click();
			String actualUrl = driver.getCurrentUrl();
			System.out.println(driver.getTitle());
			if (driver.getTitle().equalsIgnoreCase(homePageTitle)) {
				isAlreadyLogIn = true;
				System.out.println("TestCase Passed  :Sales force home page is displayed");
			} else
				System.out.println("login testcase passed:Home Page Displayed");
		}
		return isAlreadyLogIn;
	}

	public boolean logoutOfHomePage(WebDriver driver, boolean isLoggedIn) throws Exception {
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

	public void checkUserNameAfterLogOut(WebDriver driver) {

		driver.switchTo().defaultContent();
		WaitUtility.waitForElementVisible(driver, driver.findElement(By.id("idcard-identity")));
		String idText = driver.findElement(By.id("idcard-identity")).getText();
		if (idText.equalsIgnoreCase(aUsername)) {
			System.out.println(" Test Case Passed:User name " + idText + " displayed in user name field");
		} else {
			System.out.println("User name not displayed in user name field");

		}

	}

}
