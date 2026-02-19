package com.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.framework.base.DriverFactory;
import com.automation.framework.utils.SafeClickUtil;
import com.automation.framework.utils.WaitUtil;

public class LoginPage {
	
	private By username = By.name("username");
	private By password = By.name("password");
	private By loginButton= By.xpath("//button[@type='submit']");
	private By errorMsg = By.xpath("//p[contains(@class,'content-text')]");
	private By HRMLogo = By.xpath("//img[@alt='client brand banner']");
	
	
	//Enter user name
	public void enterUsername(String user) {
        WaitUtil.waitForVisibility(username).sendKeys(user);
    }
	//Enter password
    public void enterPassword(String pass) {
        WaitUtil.waitForVisibility(password).sendKeys(pass);
    }
    
    //click on login button
    public void clickLogin() {
        WebElement btn = WaitUtil.waitForClickable(loginButton);
        SafeClickUtil.click(btn);
    }

    //Check is hrm logo appeared or not?
    public boolean isLoginSuccessful() {
        return DriverFactory.getDriver().findElements(HRMLogo).size() >0;
    }

    //verify error message
    public boolean isErrorDisplayed() {
        return DriverFactory.getDriver().findElements(errorMsg).size() > 0;
    }
    
    
    //Complete login function
    public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}
}

