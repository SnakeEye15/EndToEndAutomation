package com.automation.framework.utils;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateUtil {
	
	// Returns date after given number of days from today
    //Used for dynamic calendar selection
	
	public static LocalDate getFutureDate(int daysToAdd) {
		return LocalDate.now().plusDays(daysToAdd);
		
	}
	
	//returns day value (in range 1 to 31)
	public static String getDay(LocalDate date) {
		return String.valueOf(date.getDayOfMonth());
		
	}
	
	//Returns month name
	public static String getMonth(LocalDate date) {
		//return date.format(DateTimeFormatter.ofPattern("MMMM"));
		return date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	}
	
	//Returns year 
	public static String getYear(LocalDate date) {
		return String.valueOf(date.getYear());
	}
}
