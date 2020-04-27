package com.sdfc.login;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class CreateAccountTest extends LaunchWebBrowser {
	public static boolean isAlreadyLogIn = false;
	public static WebDriver driver = null;

	public static void main(String[] args) throws Exception {
		createAnAccountverify(isAlreadyLogIn);
		createNewView(isAlreadyLogIn);
		editView(isAlreadyLogIn);
		mergeAccount(isAlreadyLogIn);
		createAccountReport(isAlreadyLogIn);
		System.out.println("End of testcases");
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

	public static void createNewView(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement accountTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Accounts')]"));
		accountTab.click();
		System.out.println("Account Tab clicked");
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
		viewUName2.sendKeys(viewName);
		WebElement saveBtn = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		Thread.sleep(2000);
		saveBtn.click();
		WebElement selectview = WaitUtility.waitForElementVisible(driver, By.xpath("	//select[@name='fcf']"));
		Select selects = new Select(selectview);

		if (selects.getFirstSelectedOption().getText().equals(viewName)) {
			System.out.println("Newely added View  displayed in the account view list");
		} else
			System.out.println("Newely added not displayed in the account view list");
		System.out.println("create new view is ended");
		isLoggedIn = logoutOfApp(driver, isLoggedIn);
		Thread.sleep(2000);
		driver.quit();
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

	public static void mergeAccount(boolean isLoggedIn) throws Exception {
		isLoggedIn = launchApp();
		WebElement accountTab = WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Accounts')]"));
		accountTab.click();
		System.out.println("Account Tab clicked");
		Thread.sleep(2000);
		System.out
				.println("homecontactPage:" + driver.findElement(By.xpath("//h2[contains(@class,'pageDescription')]")));
		WebElement mergeLink = WaitUtility.waitForElementVisible(driver,
				By.xpath(" //a[contains(text(),'Merge Accounts')]"));
		mergeLink.click();

		WebElement findAccountInput = WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='srch']"));
		findAccountInput.sendKeys(accountNameConatct);
		Thread.sleep(1000);
		WebElement findActBtn = WaitUtility.waitForElementVisible(driver,
				By.xpath("//input[contains(@name,'srchbutton')]"));
		Thread.sleep(2000);
		findActBtn.click();
		WebElement mergeTable = driver.findElement(By.xpath("//table[@class='list']"));
		List<WebElement> checkBoxesAcct = mergeTable.findElements(By.tagName("input"));
		if (!checkBoxesAcct.get(0).isSelected()) {
			checkBoxesAcct.get(0).click();
		}
		if (!checkBoxesAcct.get(1).isSelected()) {
			checkBoxesAcct.get(1).click();
		}

		Thread.sleep(2000);
		WebElement nextBtn = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[@class='pbTopButtons']//input[@title='Next']"));
		nextBtn.click();
		WebElement mergerbtn2 = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[contains(@class,'pbTopButtons')]//input[contains(@name,'save')]"));
		mergerbtn2.click();
		driver.switchTo().alert().accept();
		System.out.println("New Merge Account is listed:ended");
		isLoggedIn = logoutOfApp(driver, isLoggedIn);
		Thread.sleep(2000);
		driver.quit();
	}

}
