package com.sdfc.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class QuarterlySummaryOpportunityReportTest extends LaunchWebBrowser {

	public static WebDriver driver = null;
	public static boolean isAlreadyLogIn = false;

	public static void main(String[] args) throws Exception {
		RememberUserNameTest login = new RememberUserNameTest();
		driver = LaunchWebBrowser.loadBrowser();
		login.launchSDFCApplication(driver, loginUrl);
		login.rememberMeCheckBox(driver, isAlreadyLogIn, aUsername, aPassword);
		 WebElement oppurtunityTab=WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Opportunities')]"));
		 oppurtunityTab.click();
		 Thread.sleep(3000);
		 WebElement intervalEle=driver.findElement(By.xpath("//select[@name='quarter_q']"));
		 Select selectInterval=new Select(intervalEle);
		 WebElement includelEle=driver.findElement(By.xpath("//select[@name='open']"));
		 Select selectInclude=new Select(includelEle);
		 Thread.sleep(2000);
		 selectInterval.selectByVisibleText(currentANextFQ);
		 selectInclude.selectByVisibleText(openOpportunities);
		 WebElement reportButton=driver.findElement(By.xpath("//table[@class='opportunitySummary']//input[@name='go']"));
		 reportButton.click();
		 System.out.println("Report Page with the Opportunities that satisfies the search criteria will be displayed.");
		 Thread.sleep(2000);
		 driver.quit();
		
	}

}
