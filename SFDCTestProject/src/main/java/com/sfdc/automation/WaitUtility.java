package com.sfdc.automation;



import java.util.List;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	
	
	
	/**
	 * Waits for a given element to be visible
	 * 
	 * @param driver WebDriver instance
	 * @param locator By of the element to wait for
	 */
	public static WebElement waitForElementVisible(WebDriver driver, By locator){
	    WebDriverWait wait = new WebDriverWait(driver, 40);
	    if (!wait.equals(null)) {
	    
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }
	    else {
	    	System.out.println("wait"+wait);
	    	return null;
	    }
	}
	public static List<WebElement> waitForAllElementsToBeVisible(WebDriver driver, By locator){
	    WebDriverWait wait = new WebDriverWait(driver, 40);
	    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	    }
	public static List<WebElement>  presenceAllElementsToBeVisible(WebDriver driver, By locator){
	    WebDriverWait wait = new WebDriverWait(driver, 90);
	    return (List<WebElement>) wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	    }
	 public static void waitForElementNotVisible(WebDriver driver, By locator){
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    Boolean visible = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	public static void waitForElementVisible(WebDriver driver, WebElement e){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		 wait.until(ExpectedConditions.visibilityOf(e));
	}
	public static void waitForElementSelected(WebDriver driver, By locator){
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    Boolean bool = wait.until(ExpectedConditions.elementToBeSelected(locator));
	}
	public static void waitForElementSelected(WebDriver driver, WebElement e){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Boolean bool = wait.until(ExpectedConditions.elementToBeSelected(e));
	}
	public static WebElement  waitForElementClickable(WebDriver driver, By locator){
		 WebElement ele = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			System.out.println("webelement not found"+e);
		}
		return ele;
	}
	public static void waitForElementClickable(WebDriver driver, WebElement ele){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120);
			ele= wait.until(ExpectedConditions.elementToBeClickable(ele));
			
		} catch (Exception e2) {
			System.out.println("webelement not found"+e2);
		}
		
	}
	public static boolean waitForPageTitle(WebDriver driver, String title){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.titleContains(title));
	}
	public static  void waitForAlertIsToBePresent(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
	}
   public static boolean  waitFortextToBePresentInElement(WebDriver driver,WebElement e,String text) {
	   WebDriverWait wait = new WebDriverWait(driver, 40);
	  return  wait.until(ExpectedConditions.textToBePresentInElement(e, text));
   }
	

	/**
	 * Sleep script for the specified length
	 * (generally not an ideal solution)
	 * 
	 * @param length
	 */
	/*public static void sleep(long length){
	    try {
	        Thread.sleep(length);
	    } catch (InterruptedException e) {
	        Log .error("Sleep Interrupted");
	        Log.
	        e.printStackTrace();
	    }
	}*/
	
	

}
