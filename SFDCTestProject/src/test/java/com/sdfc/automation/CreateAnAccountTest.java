package com.sdfc.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class CreateAnAccountTest  extends LaunchWebBrowser{
	public static boolean isAlreadyLogIn = false;
	public static WebDriver driver = null;

	public static void main(String[] args) throws Exception {
		createAnAccountverify(isAlreadyLogIn);
	}
	
		public static void createAnAccountverify(boolean isLoggedIn) throws Exception {
			isLoggedIn = launchApp();
			WebElement accountTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Accounts')]"));
			accountTab.click();
			System.out.println("Account Tab clicked");
			Thread.sleep(2000);
			System.out
					.println("homecontactPage:" + driver.findElement(By.xpath("//h2[contains(@class,'pageDescription')]")));
			WebElement newBtn = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@name='new']"));
			newBtn.click();
			Thread.sleep(2000);
			System.out.println(driver.findElement(By.xpath("//h2[contains(@class,'pageDescription')]")));
			WebElement acctNme = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='acc2']"));
			acctNme.sendKeys(accountNameConatct);
			WebElement selectEle = WaitUtility.waitForElementVisible(driver, By.xpath("//select[contains(@name,'acc6')]"));
			Select select = new Select(selectEle);
			select.selectByVisibleText(accountType);
			WebElement selectCustPrio = WaitUtility.waitForElementVisible(driver,
					By.xpath("//select[@id='00N5w00000HXjIE']"));
			Select select1 = new Select(selectCustPrio);
			select1.selectByVisibleText(customerPriority);
			WebElement saveBtn = WaitUtility.waitForElementVisible(driver,
					By.xpath("//div[contains(@class,'pbBottomButtons')]//input[1]"));
			saveBtn.click();
			isLoggedIn = logoutOfApp(driver, isLoggedIn);
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
