package com.automation.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	//creates report for entire suit once only
	public static ExtentReports getInstance() {
		if(extent == null) {
			ExtentSparkReporter reporter=new ExtentSparkReporter("reports/AutomationReport.html");
			reporter.config().setReportName("Automation Execution Report");
			reporter.config().setDocumentTitle("Automation Framework Report");
			
			extent=new ExtentReports();
			extent.attachReporter(reporter);
											
		}
		return extent;
	}
	
	
	
}
