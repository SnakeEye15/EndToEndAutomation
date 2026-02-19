package com.automation.framework.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.automation.framework.reports.ExtentManager;
import com.automation.framework.utils.LoggerUtil;
import com.automation.framework.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentListeners implements ITestListener {
	
	private static final Logger log = LoggerUtil.getLogger(ExtentListeners.class);
	
	//Single report instance for full execution
	private static ExtentReports extent=ExtentManager.getInstance();
	
	//ThreadLocal for managing parallel execution
	private static ThreadLocal<ExtentTest> test= new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		//create entry for each test
		ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
		String testname=result.getMethod().getMethodName();
		log.info("Test Started: "+ testname);
		test.set(extentTest);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		//on passing test
		String testname=result.getMethod().getMethodName();
		log.info("Test case passed: "+testname);
		test.get().pass("Test Passed");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		//on failed test log failure message
		test.get().fail(result.getThrowable());
		String testname=result.getMethod().getMethodName();
		//Capture screenshot and attach to report
		String path=ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());
		
		log.info("Test case Failed: "+testname);
		
		test.get().addScreenCaptureFromPath(path);
		
	}
	
	 @Override
	    public void onTestSkipped(ITestResult result) {
		 String testname=result.getMethod().getMethodName();
		 log.info("Test case Skipped: "+testname);
		 
	        test.get().skip("Test Skipped");
	    }


	    @Override
	    public void onFinish(ITestContext context) {
	        // Flush writes data into html report
	        extent.flush();
	    }
	
	
	
	
}
