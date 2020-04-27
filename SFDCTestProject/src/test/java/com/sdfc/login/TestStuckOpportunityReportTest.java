package com.sdfc.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class TestStuckOpportunityReportTest  extends LaunchWebBrowser{
    
	public static WebDriver driver=null;
	public static boolean isAlreadyLogIn = false;
	public static void main(String[] args) throws Exception {
		RememberUserNameTest login = new RememberUserNameTest();
		driver = LaunchWebBrowser.loadBrowser();
		login.launchSDFCApplication(driver, loginUrl);
		 login.rememberMeCheckBox(driver, isAlreadyLogIn, aUsername, aPassword);
		
		 WebElement oppurtunityTab=WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Opportunities')]"));
		 oppurtunityTab.click();
		 Thread.sleep(3000);
		 WebElement stuckOLink=WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		 stuckOLink.click();
		 System.out.println("Report Page with the Opportunities that are Stuck will be displayed.");
	}
	
	

}
