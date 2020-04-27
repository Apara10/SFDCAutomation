package com.sdfc.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class editViewOfAccountTest extends LaunchWebBrowser {
	public static boolean isAlreadyLogIn = false;
	public static WebDriver driver = null;
	public static void main(String[] args) throws Exception {
		editView(isAlreadyLogIn);
		
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
		public static void editView(boolean isLoggedIn) throws Exception {
			isLoggedIn = launchApp();
			WebElement accountTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Accounts')]"));
			accountTab.click();
			System.out.println("Account Tab clicked");
			Thread.sleep(2000);
			System.out
					.println("homecontactPage:" + driver.findElement(By.xpath("//h2[contains(@class,'pageDescription')]")));
			String tempXpath = "//span[@class='mruText'][contains(text(),'" + fullname + "')]";
			Thread.sleep(2000);
			// String userNameText = driver.findElement(By.xpath(tempXpath)).getText();
			// if (userNameText.trim().equalsIgnoreCase(fullname.trim())) {
			// System.out.println("Accounts page is displayed with correct" + userNameText +
			// "");
			// }
			WebElement selectview = WaitUtility.waitForElementVisible(driver, By.xpath("//select[@name='fcf']"));
			Select selects = new Select(selectview);
			selects.selectByVisibleText(viewName);
			Thread.sleep(2000);
			WebElement edit = driver.findElement(By.xpath("//span[@class='fFooter']//a[contains(text(),'Edit')]"));
			edit.click();
			WebElement viewName1 = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='fname']"));
			viewName1.sendKeys(newViewName);
			WebElement selectfield = WaitUtility.waitForElementVisible(driver, By.xpath("//select[@class='column']"));
			Select selectviewfield = new Select(selectfield);
			selectviewfield.selectByVisibleText(accountNameF);
			// select[@name='fop1']
			WebElement selectOp = WaitUtility.waitForElementVisible(driver, By.xpath("//select[@class='operator']"));
			Select selectviewfieldO = new Select(selectOp);
			selectviewfieldO.selectByVisibleText(containsO);
			Thread.sleep(2000);
			WebElement viewValuein = driver.findElement(By.xpath("//input[@id='fval1']"));
			Thread.sleep(2000);
			viewValuein.clear();
			viewValuein.sendKeys(viewValue);
			Thread.sleep(3000);
			WebElement saveButton = WaitUtility.waitForElementVisible(driver,
					By.xpath("//div[@class='pbBottomButtons']//input[1]"));
			saveButton.click();
			Select newS = new Select(driver.findElement(By.xpath("//select[@name='fcf']")));
			System.out.println("View Page is displayed with" + newS.getFirstSelectedOption().getText());
			System.out.println("edit view Ended");
			isLoggedIn = logoutOfApp(driver, isLoggedIn);
			Thread.sleep(2000);
			driver.quit();

		}

	

}
