package com.sdfc.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sfdc.automation.LaunchWebBrowser;

public class UserMenuTest {

	static WebDriver driver = null;
	public static boolean isAlreadyLogIn = false;

	public static void main(String[] args) throws Exception {
		RememberUserNameTest rememberUserNameTest = new RememberUserNameTest();
	    SelectUserMenuForDropDownTest selectUserMenuForDropDown = new SelectUserMenuForDropDownTest();
		driver = SelectUserMenuForDropDownTest.verfySelectUserMenuForDropDownTest(driver, rememberUserNameTest,selectUserMenuForDropDown);
		Thread.sleep(1000);
		driver = new VerifyMyProfileOptionFromUnameDropDownTest().verifyClickMyProfile(driver);
		driver = new VerifyMyProfileOptionFromUnameDropDownTest().myProfiileAction(driver);
		driver = new VerifyMyProfileOptionFromUnameDropDownTest().verifyPostLink(driver);
		driver = new VerifyMyProfileOptionFromUnameDropDownTest().verifyFileLink(driver);
		driver = new VerifyMyProfileOptionFromUnameDropDownTest().verifyupLoadPhoto(driver);
		driver = new SelectMyOptionSettingFromUserMenu().verifyMySetting(driver);
		driver = new SelectDevelopersConsole().verifyDeveloperConsole(driver);
		Thread.sleep(2000);
		new RememberUserNameTest().logoutOfHomePage(driver, true);
		Thread.sleep(2000);
		driver.quit();

	}

}
