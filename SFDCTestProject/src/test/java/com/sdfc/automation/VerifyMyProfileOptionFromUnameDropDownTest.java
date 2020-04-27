package com.sdfc.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.sfdc.automation.LaunchWebBrowser;
import com.sfdc.automation.WaitUtility;

public class VerifyMyProfileOptionFromUnameDropDownTest extends LaunchWebBrowser {
	public static WebDriver driver=null;
	static boolean isAlreadyLogIn=false;

	public static void main(String[] args) {
		
		
	}
	public WebDriver verifyClickMyProfile(WebDriver driver) throws InterruptedException {
		WebElement myProfileLink = driver.findElement(By.xpath("//a[contains(text(),'My Profile')]"));
		myProfileLink.click();
		return driver;
		
	}
	
	public    WebDriver  myProfiileAction(WebDriver driver) {
		
		WebElement moderatorbutton=WaitUtility.waitForElementVisible(driver, By.id("moderatorMutton"));
		moderatorbutton.click();
		WebElement editProfileLink=WaitUtility.waitForElementVisible(driver, By.xpath("//a[contains(text(),'Edit Profile')]"));
		editProfileLink.click();
		driver.switchTo().frame("aboutMeContentId");
		    WebElement lastnameele=WaitUtility.waitForElementVisible(driver, By.xpath("//input[@id='lastName']"));
		   String lastName= lastnameele.getAttribute("value");
	   	System.out.println("Last Name is"+lastName);
	   	lastnameele.clear();
	   	lastnameele.sendKeys(lastNameChanged);
	   	WebElement saveAllButtEle=WaitUtility.waitForElementVisible(driver, By.xpath("//input[contains(@class,'zen-btn zen-primaryBtn zen-pas')]"));
	   	saveAllButtEle.click();
	   	driver.switchTo().defaultContent();
	   	return driver;
	  
	  
	   	
	}
	public WebDriver  verifyPostLink(WebDriver driver) {
	   
	 	WebElement postlinkEle=WaitUtility.waitForElementVisible(driver, By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'Post')]"));
	   	postlinkEle.click();
	   	WebElement framePast=driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
	   	driver.switchTo().frame(framePast);
	   	String xpathPostArea="//body[@class='chatterPublisherRTE cke_editable cke_editable_themed cke_contents_ltr cke_show_borders placeholder']";
	    WebElement  bodyEle=driver.findElement(By.cssSelector("body"));
	   JavascriptExecutor executor = (JavascriptExecutor)driver;
	  	executor.executeScript("arguments[0].click();", bodyEle);
	  	executor.executeScript("arguments[0].innerHTML = 'Hello World!'", bodyEle);
	  	 driver.switchTo().defaultContent();
	  	WebElement shareButtonEle=driver.findElement(By.id("publishersharebutton"));
	  	shareButtonEle.click();
	  	System.out.println("Test Passed: ShareButton click");
		return driver;
		
	}
	public WebDriver  verifyFileLink(WebDriver driver) {
		WebElement fileLinkEle=WaitUtility.waitForElementVisible(driver, By.xpath("//span[contains(@class,'publisherattachtext ')][contains(text(),'File')]"));
		fileLinkEle.click();
		System.out.println("clicked  file link :Test case passed");
		WebElement uploadFileLink=driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
		uploadFileLink.click();
		System.out.println("clicked upload  file link :Test case passed");
		WebElement chooseFileInputEle=driver.findElement(By.xpath("//input[@id='chatterFile']"));
		chooseFileInputEle.sendKeys("C:\\Users\\mahar\\Desktop\\selenium.txt");
		System.out.println("clicked choose  file link :Test case passed");
		return driver;
		
	}
	
	public WebDriver verifyupLoadPhoto(WebDriver driver) throws InterruptedException {
		Actions actions = new Actions(driver);
		WebElement photoEle=WaitUtility.waitForElementVisible(driver, By.className("chatter-photo"));
		actions.moveToElement(photoEle).moveToElement(driver.findElement(By.id("uploadLink"))).click().build().perform();
		WebElement frameforPhoto=WaitUtility.waitForElementVisible(driver, By.xpath("//iframe[@id='uploadPhotoContentId']"));
		driver.switchTo().frame(frameforPhoto);
		WebElement chooseFile=driver.findElement(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadInputFile']"));
		chooseFile.sendKeys("C:\\Users\\mahar\\Downloads\\puppy.png");
		WebElement savePhotoEle=driver.findElement(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadBtn']"));
		savePhotoEle.click();
		System.out.println("Image uploaded Succesfully");
		Thread.sleep(5000);
		Actions action=new Actions(driver);
		action.dragAndDropBy(driver.findElement(By.xpath("//div[@class='imgCrop_handle imgCrop_handleNW']")), 200, 200);
		WebElement saveButtEle=driver.findElement(By.xpath("//input[@id='j_id0:j_id7:save']"));
		saveButtEle.click();
		//driver.switchTo().defaultContent();
		System.out.println("Photo Uplaoding done:TestCase is passed:");
		return driver;
		
	}
	   	
		
		
		
		
		
		
	

}
