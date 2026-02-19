package com.automation.framework.base;

import org.testng.annotations.*;
public class BaseTest {
	
	
	//Runs before each test method it's usage to setup the conditions before test starts
	@BeforeMethod
	public void setup() {
		//Getting  browser as per Config properties file
		DriverFactory.initDriver();
	
	}
	
	//Runs after each test method to clean the instance used and data used to clean unused memory
	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
	

}
