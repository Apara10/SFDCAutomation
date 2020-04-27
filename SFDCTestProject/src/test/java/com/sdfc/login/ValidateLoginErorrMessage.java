package com.sdfc.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class ValidateLoginErorrMessage extends LaunchWebBrowser {
	
	public static void main(String[] args) {
		boolean isAlreadyLogIn=false;
		driver = LaunchWebBrowser.loadBrowser();
		RememberUserNameTest rememberUserNameTest = new RememberUserNameTest();
		rememberUserNameTest.launchSDFCApplication(driver,loginUrl);
		isAlreadyLogIn = rememberUserNameTest.rememberMeCheckBox(driver, isAlreadyLogIn,wrongUserName,wrongPassword);
		System.out.println(isAlreadyLogIn);
		WebElement errormsgEle=WaitUtility.waitForElementVisible(driver, By.id("error"));
		if (errormsgEle.getText().equals(errorLoginMsg)) {
			System.out.println("TestCase Passed:"+errorLoginMsg);
			
		} else {
			System.out.println("TeseCase is Failed");

		}
		

	}

}
