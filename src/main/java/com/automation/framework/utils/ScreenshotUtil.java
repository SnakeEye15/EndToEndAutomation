package com.automation.framework.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.automation.framework.base.DriverFactory;

public class ScreenshotUtil {
		
	//capture screenshot and return file path that later used on listeners
	public static String captureScreenshot(String testName) {
		try {
			
			//converting driver to screenshot driver
			TakesScreenshot ts= (TakesScreenshot)DriverFactory.getDriver();
			
			//capture it as file
			File source= ts.getScreenshotAs(OutputType.FILE);
			
			//giving it unique name
			String timeStamp= new SimpleDateFormat("yyyymmdd_HHmmss").format(new Date());
			
			String path="reports/screenshots/"+testName+"_"+timeStamp+".png";
			
			File destination = new File(path);
			
			//copy screenshot to project folder
			FileHandler.copy(source,destination);
			
			return path;
			
		}catch(Exception e) {
			throw new RuntimeException("Failed to Capture screenshot");
		}
	}
}
