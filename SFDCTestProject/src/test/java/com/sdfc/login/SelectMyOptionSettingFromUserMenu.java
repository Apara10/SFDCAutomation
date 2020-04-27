package com.sdfc.login;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class SelectMyOptionSettingFromUserMenu extends LaunchWebBrowser {

	static WebDriver driver = null;

	public WebDriver  verifyMySetting(WebDriver driver) throws Exception {
		WebElement userNavigationlinkEle = WaitUtility.waitForElementVisible(driver, By.id("userNav-arrow"));
		userNavigationlinkEle.click();
		WebElement mySettingLink = driver.findElement(By.xpath("//a[contains(text(),'My Settings')]"));
		mySettingLink.click();
		System.out.println("My Setting is dispalyed");
		WebElement personallinkEle = WaitUtility.waitForElementVisible(driver,
				By.xpath("//div[@id='PersonalInfo']//a[@class='header setupFolder']"));
		personallinkEle.click();
		WebElement loginHistoryLink = driver.findElement(By.xpath("//a[@id='LoginHistory_font']"));
		loginHistoryLink.click();
		System.out.println("Login History is diplayed");
		WebElement downloadLink = driver
				.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]"));
		downloadLink.click();
		System.out.println("Login History is diplayed and data downloaded in cvs format");
		Thread.sleep(2000);
		WebElement displayLink = driver
				.findElement(By.xpath("//div[@id='DisplayAndLayout']//a[@class='header setupFolder']"));
		displayLink.click();
		WebElement customizeMyTablink = driver.findElement(By.id("CustomizeTabs_font"));
		customizeMyTablink.click();
		System.out.println("Customized My Tab Page succesfully got dispalyed");
		Thread.sleep(5000);
		WebElement selectCustomEle = driver
				.findElement(By.xpath("//table[@class='detailList']//tbody//tr[2]//td//select"));
		Select customapps = new Select(selectCustomEle);
		customapps.selectByVisibleText(selectCustomiseTab);
		System.out.println("Sales Force Chater got selected");
		WebElement selectAvalibleTabEle = driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		Select availableSelect = new Select(selectAvalibleTabEle);
		List<WebElement> allOptions = availableSelect.getOptions();
				availableSelect.selectByVisibleText("Reports");
				System.out.println("hi");
		
			WebElement addButtLink = driver.findElement(By.xpath("//a[@id='duel_select_0_right']"));
			addButtLink.click();
			WaitUtility.waitForElementVisible(driver, driver.findElement(By.xpath("//select[@id='duel_select_1']")));
			driver.findElement(By.xpath("//input[@name='save']")).click();
			System.out.println(
					"Reports field is added to Selected Tabs list and also added in the links available in top of salesforce page .");
			WebElement emaillink = driver
					.findElement(By.xpath("//div[@id='EmailSetup']//a[contains(@class,'header setupFolder')]"));
			emaillink.click();
			WebElement emailSettinglink = driver.findElement(By.xpath("//a[@id='EmailSettings_font']"));
			emailSettinglink.click();
			WebElement emailNameEle = WaitUtility.waitForElementVisible(driver, By.id("sender_name"));
			emailNameEle.clear();
			emailNameEle.sendKeys(emailName);
			WebElement emailAddressEle = WaitUtility.waitForElementVisible(driver, By.id("sender_email"));
			emailAddressEle.clear();
			emailAddressEle.sendKeys(emailAddress);
			WebElement AutomaticBCCRadioEle1 = driver.findElement(By.id("auto_bcc1"));
			WebElement AutomaticBCCRadioEle2 = driver.findElement(By.id("auto_bcc0"));
			if (AutomaticBCCRadioEle1.isSelected()) {
				AutomaticBCCRadioEle2.click();
			} else {
				AutomaticBCCRadioEle1.click();
			}
			driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[contains(@name,'save')]")).click();
			System.out.println("Your settings have been successfully saved.");
			Thread.sleep(3000);
			WebElement calendar=WaitUtility.waitForElementVisible(driver, By.xpath("//div[@id='CalendarAndReminders']//a[contains(@class,'header setupFolder')]"));
			calendar.click();
			WebElement remainderEle = WaitUtility.waitForElementVisible(driver, By.xpath(" //a[@id='Reminders_font']"));
			remainderEle.click();
			WebElement testRemainderButEle = WaitUtility.waitForElementVisible(driver,
					By.xpath("//input[@id='testbtn']"));
			testRemainderButEle.click();
			System.out.println("Sample event pop window is dispayed.");
			Thread.sleep(2000);
			return driver;
			
		

		}
	
}
