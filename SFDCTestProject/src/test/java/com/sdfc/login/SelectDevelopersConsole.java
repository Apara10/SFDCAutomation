package com.sdfc.login;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class SelectDevelopersConsole extends LaunchWebBrowser {

	public WebDriver verifyDeveloperConsole(WebDriver driver) throws InterruptedException {
		WebElement userNavigationlinkEle = WaitUtility.waitForElementVisible(driver, By.id("userNav-arrow"));
		userNavigationlinkEle.click();
		WebElement developerConsoleLink = driver.findElement(By.xpath("//a[contains(text(),'Developer Console')]"));
		developerConsoleLink.click();
		String window1=driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		String[] getWindows=windows.toArray(new String [windows.size()]);
		Thread.sleep(3000);
		driver.switchTo().window(getWindows[1]).close();
		System.out.println("Console window closed" );
		return driver;
	}
	

}
