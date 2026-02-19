package com.automation.framework.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.framework.base.DriverFactory;
import com.automation.framework.utils.DateUtil;
import com.automation.framework.utils.SafeClickUtil;
import com.automation.framework.utils.WaitUtil;

public class HomePage {
	
	//Locator for FROM input field
	private By fromField = By.id("txtStationFrom");
	//private By fromField = By.xpath("//input[contains(@placeholder,'From')]");
	
	//Locator for dropdown station options 
	private By stationList = By.xpath("//div[contains(@class,'autocomplete')]//div[@title]");
	
	// Journey date textbox (Sort on Date)
	//private By dateField = By.xpath("//input[contains(@title,'Select Date')] | //input[contains(@placeholder,'Date')]");
	private By dateField=By.xpath("//input[starts-with(@title,'Select Departure')]");
	

	//Enter station name in from Filed
	public void enterFromStation(String station) {
		// Wait until field is clickable to avoid typing before page ready
        WebElement field = WaitUtil.waitForClickable(fromField);
        field.click();
        field.clear(); //to remove text already present in filed
        field.sendKeys(station); //enter our custom station name
        
	}
	
	  // Returns all station names from dropdown
	public List<String> getAllStations(){
		WaitUtil.waitForAllVisible(stationList);
		
		List<WebElement> elements=DriverFactory.getDriver().findElements(stationList);
		
		// Creating new list to store station names
        List<String> stationNames = new ArrayList<>();
        
        //Iterating throw each element and storing station name in list
        for(WebElement element: elements) {
        	stationNames.add(element.getAttribute("title"));
        }
        
        return stationNames;
	}
	
	//Select station by index (4th station = index 3)
	public String selectStationByIndex(int position) {
		int index=position-1;
		
		WaitUtil.waitForAllVisible(stationList);

        List<WebElement> elements =
                DriverFactory.getDriver().findElements(stationList);
        
        
        WebElement station=elements.get(index);
        String stationName=station.getAttribute("title");
        SafeClickUtil.click(station);
       
        return stationName;
	}
	
	
	//Finds correct month table then clicks correct day
	public void selectJourneyDate(int daysAhead) {
		
		//open Calendar
		WaitUtil.waitForVisibility(dateField).click();
		
		//calculate future date
		java.time.LocalDate targetDate= DateUtil.getFutureDate(daysAhead);
		
		String day=DateUtil.getDay(targetDate);
		String month=DateUtil.getMonth(targetDate);
		String year=DateUtil.getYear(targetDate);
		
		//Convert into UI format
		String shortYear= year.substring(2,4);
		String monthYear=month.substring(0,3)+"-"+shortYear;
		
		// Build dynamic xpath
	    String xpath =
	            "//td[text()='" + monthYear + "']" +
	            "/ancestor::table[@class='Month']" +
	            "//td[@onclick and text()='" + day + "']";
	    
	    WebElement dateElement=DriverFactory.getDriver().findElement(By.xpath(xpath));
	    SafeClickUtil.click(dateElement);
		
	}
	
	
	
}
