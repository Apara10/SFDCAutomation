package com.sdfc.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class createAccountReportTest extends LaunchWebBrowser {

		public static boolean isAlreadyLogIn = false;
		public static WebDriver driver = null;

		public static void main(String[] args) throws Exception {
			//LaunchBrowserLoginLogout launchApp=new LaunchBrowserLoginLogout();
			createAccountReport(isAlreadyLogIn);
			System.out.println("End of testcases");
		}

		public static void createAccountReport(boolean isLoggedIn) throws Exception {
			isLoggedIn = launchApp();
			WebElement accountTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Accounts')]"));
			accountTab.click();
			System.out.println("Account Tab clicked");
			WebElement clickGreater30 = WaitUtility.waitForElementVisible(driver,
					By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]"));
			clickGreater30.click();
			driver.findElement(By.xpath("//input[@id='ext-gen20']")).click();
			WebElement createDate = WaitUtility.waitForElementVisible(driver,
					By.xpath("//div[contains(text(),'Created Date')]"));
			createDate.click();
			driver.findElement(By.id("ext-comp-1042")).click();
			String datePath = "//div[@class='x-form-field-wrap x-form-field-trigger-wrap x-box-item x-trigger-wrap-focus']//img[@class='x-form-trigger x-form-date-trigger']";
			driver.findElement(By.xpath(datePath)).click();
			System.out.println("Today's day selected and List of accounts qualified is displayed ");
			WebElement saveButton = WaitUtility.waitForElementVisible(driver, By.xpath("//button[@id='ext-gen49']"));
			saveButton.click();
			WebElement reportNameIn = WaitUtility.waitForElementVisible(driver,
					By.xpath("//input[@id='saveReportDlg_reportNameField']"));
			reportNameIn.sendKeys(reportName);
			WebElement reportUNameIn = driver.findElement(By.xpath("//input[@id='saveReportDlg_DeveloperName']"));
			reportUNameIn.clear();
			reportUNameIn.sendKeys(reportUName);
			Thread.sleep(3000);
			WebElement saveRun = WaitUtility.waitForElementVisible(driver,
					By.xpath("//td[@class='x-btn-mc']//em//button[contains(text(),'Save and Run Report')]"));
			saveRun.click();
			System.out.println("Report page with details and  Automation is displayed.");
			isLoggedIn = logoutOfApp(driver, isLoggedIn);
			Thread.sleep(2000);
			driver.quit();

		}
	
	


}
