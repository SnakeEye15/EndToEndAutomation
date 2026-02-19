package com.automation.tests;

import java.util.List;

import org.apache.logging.log4j.Logger;
//import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.framework.base.BaseTest;
import com.automation.framework.base.DriverFactory;
import com.automation.framework.config.ConfigReader;
import com.automation.framework.pages.HomePage;
import com.automation.framework.utils.ExcelUtil;
import com.automation.framework.utils.LoggerUtil;

public class StationSelectionTest extends BaseTest{
	private static final Logger log = LoggerUtil.getLogger(HomePage.class);
	SoftAssert soft=new SoftAssert();
	
	@Test
	public void verifyStationDropDownData() {
		HomePage homepage=new HomePage();
		
		//Step1: Hitting url
		DriverFactory.getDriver().get(ConfigReader.getURL());
		log.info("Entering URL for train website");
		
		//Step 2–4 : Enter DEL in FROM field
		homepage.enterFromStation("DEL");
		log.info("Entering 'DEL' text in filed");
		
		//Step 7: Capture dropdown station name in UI
		List<String> actualStations= homepage.getAllStations();
		
		//save actual UI to compare
		ExcelUtil.writeColumn("src/test/resources/testdata/actual/actualStations.xlsx",
				"ActualStatin", actualStations);
		log.info("Capturing acutal Stations shown on site");
		//Read expected excel sheet
		List<String> expectedStations= ExcelUtil.readColumn("src/test/resources/testdata/expected/expectedStations.xlsx", "Stations");
		
		//Compare the results
		List<String> missing=ExcelUtil.compareList(actualStations, expectedStations);
		log.info("Comparing expected stations with actual stations");
		
		//assert to verify that missing sheet is blank
		soft.assertTrue(missing.isEmpty(), "Missing stations are:"+missing);
		
		//Step 5: click on index 4 and print it
		String selectedStation=homepage.selectStationByIndex(ConfigReader.getStationPosition());
		log.info("Selected station: {}", selectedStation);
		
		//select date dynmaically
		homepage.selectJourneyDate(ConfigReader.getNumberOfDays());
		log.info("Selecting the date in calender");
		
		soft.assertAll();
	}

}
