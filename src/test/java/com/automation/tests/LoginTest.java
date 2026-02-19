package com.automation.tests;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.framework.base.BaseTest;
import com.automation.framework.base.DriverFactory;
import com.automation.framework.config.ConfigReader;
import com.automation.framework.pages.LoginPage;
import com.automation.framework.utils.LoggerUtil;

import DataProvider.TestDataProvider;

public class LoginTest extends BaseTest{
	
	SoftAssert softAssert = new SoftAssert();
	private static final Logger log = LoggerUtil.getLogger(LoginPage.class);

	@Test(dataProvider="loginData",dataProviderClass=TestDataProvider.class)
	public void verifyLogin(String username, String password, String expectedResult) {
		//open login page
		DriverFactory.getDriver().get(ConfigReader.getHRMUrl());
		
		LoginPage loginpage=new LoginPage();
		loginpage.login(username, password);
		log.info("Performing login with username and password");
		
		boolean success=loginpage.isLoginSuccessful();
		boolean error=loginpage.isErrorDisplayed();
		
		if(expectedResult.equalsIgnoreCase("valid")) {
			softAssert.assertTrue(success,"Valid Login Failed");
			log.info("Comparing the results of login for Valid scenarios");
		}else {
			softAssert.assertTrue(error,"Invalid login didn't shows any error");
			log.info("Comparing the results of login for InValid scenarios");
		}
		softAssert.assertAll();
	}
	
}