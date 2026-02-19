package com.automation.framework.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.automation.framework.base.DriverFactory;

public class SafeClickUtil {
	
	//First try to click if ads appears than switch to javascript click
	public static void click(WebElement element) {
		try {
			element.click();
		}catch(Exception e) {
			//move till the view of element located at
            ((JavascriptExecutor) DriverFactory.getDriver())
                 .executeScript("arguments[0].scrollIntoView(true);", element);
           
            
            //Now click on it
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", element);
		}
	}

}
