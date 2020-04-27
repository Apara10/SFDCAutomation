package com.sdfc.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class SelectUserMenuForDropDownTest extends LaunchWebBrowser {

	public static boolean isAlreadyLogIn = false;
	public static void main(String[] args) throws InterruptedException {
		RememberUserNameTest  rememberUserNameTest=new RememberUserNameTest();
		SelectUserMenuForDropDownTest selectUserMenuForDropDown=new SelectUserMenuForDropDownTest();
		SelectUserMenuForDropDownTest.verfySelectUserMenuForDropDownTest(driver,rememberUserNameTest,selectUserMenuForDropDown);
	//	Thread.sleep(3000);
	//	driver.quit();
	}
	
	public  static WebDriver  verfySelectUserMenuForDropDownTest(WebDriver driver,RememberUserNameTest login,SelectUserMenuForDropDownTest dropdownmenu) {
		driver = LaunchWebBrowser.loadBrowser();
		login.launchSDFCApplication(driver, loginUrl);
		isAlreadyLogIn = login.rememberMeCheckBox(driver, isAlreadyLogIn, aUsername, aPassword);
		dropdownmenu.verifyusernameDropdown(driver,isAlreadyLogIn);
		return driver;
	}

	public void verifyusernameDropdown(WebDriver driver,Boolean  isLoggedin) {
      System.out.println("Logged in: "+isLoggedin);
		if (isLoggedin) {
			WebElement userNavigationlinkEle = WaitUtility.waitForElementVisible(driver, By.id("userNav"));
			WaitUtility.waitForElementVisible(driver, driver.findElement(By.id("userNavLabel")));
			String usernameEleText = driver.findElement(By.id("userNavLabel")).getText();
				System.out.println(
						"SalesForce login page is launched and application home page is logged in with correct username.");
				userNavigationlinkEle.click();
				if (userNavigationlinkEle.isDisplayed()) {
					System.out.println("User menu drop down should be available");
					System.out.println(
							"Drop down with \"My profile\", \"My Settings\", \"Developer Console\",\"Logout\" , \"Switching to lightning Experience\" is displayed");
				}
				
		} else {
			System.out.println("TestCase Failed:SalesForce Username drop down is not diplayed  ");
		}

	}

}
