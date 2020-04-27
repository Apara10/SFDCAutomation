package com.sdfc.login;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.PropertiesFileReader;
import com.sfdc.automation.WaitUtility;

public class ForgotPasswordTest  extends LaunchWebBrowser{
	
	static PropertiesFileReader propertiesFile=new PropertiesFileReader();
	WebElement forPassWordEle=null;
	
	
	
	public static void main(String[] args) {
		driver = LaunchWebBrowser.loadBrowser();
		ForgotPasswordTest ForgotPasswordTest=new ForgotPasswordTest();
		RememberUserNameTest rememberUserNameTest=new RememberUserNameTest();
		rememberUserNameTest.launchSDFCApplication(driver,loginUrl );
		ForgotPasswordTest.clickOnForGotPassword();
		ForgotPasswordTest.testForGotPassword(driver);
	}

	public  void clickOnForGotPassword() {
		
		try {
		forPassWordEle=WaitUtility.waitForElementVisible(driver, By.id("forgot_password_link"));
		forPassWordEle.click();
		System.out.println(driver.getTitle());
		String ExpectedTitle=driver.getTitle();
		if (ExpectedTitle.equalsIgnoreCase(actualTitle)) {
			System.out.println("TestCase passed:Salesforce forgot password page is displayed");
		} else {
			
			 System.out.println("Tescase Failed:ForGotPassword");
		}
		}
		catch (Exception e) {
			System.out.println("ForGot Passord link not Found"+e);
		}
		}
    public void testForGotPassword(WebDriver driver) {
    	try {
    		WebElement userNameLink=WaitUtility.waitForElementVisible(driver, By.id("un"));
    		userNameLink.sendKeys(aUsername);
    		WebElement continueButttonEle=driver.findElement(By.id("continue"));
    		continueButttonEle.click();
    		WebElement resetMsgEle=WaitUtility.waitForElementVisible(driver, By.xpath("//p[contains(text(),"+resetMsg+")]"));
    		if (resetMsgEle.getText().equalsIgnoreCase(resetMsg)) {
    			System.out.println("TestCase Passed :Password reset message page is displayed.");
			} else {
				System.out.println("TestCase Failes:Password reset message is not diplayed");

			}
    		
		} 
    	catch (Exception e) {
			System.out.println("UserName text box Not visible"+e);
		}
    	
    	
    }		
		
		
		
	


}
