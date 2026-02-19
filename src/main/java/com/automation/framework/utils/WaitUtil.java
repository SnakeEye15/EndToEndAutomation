package com.automation.framework.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.framework.base.DriverFactory;
import com.automation.framework.config.ConfigReader;

public class WaitUtil {
	
	//Common wait Utils using dynamic timeout mentioned in configuration file
	private static WebDriverWait getWait() {
		return new WebDriverWait(DriverFactory.getDriver(),
				Duration.ofSeconds(ConfigReader.getExplicitWaitTime()));
	}
	
	//Wait until element is visible 
	public static WebElement waitForVisibility(By locator) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//Wait until element is ready to click
	public static WebElement waitForClickable(By locator){
		return getWait().until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	//Wait until all elements are visible (used for DropDown options in our case)
	public static void waitForAllVisible(By locator) {
		getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
}
