package com.automation.framework.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	// Stores all key=value configuration in memory
	// Static because configuration is global and should be loaded only once
	private static Properties properties;

	// Static block executes automatically when class is first loaded by JVM
	// Used to initialize framework configuration at startup
	static {
		try {
			properties = new Properties();

			// ClassLoader loads file from classpath instead of local filesystem
			InputStream stream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");

			// If file is missing in classpath, stop execution immediately (fail fast)
			if (stream == null) {
				throw new RuntimeException("config.properties not found in classpath");
			}

			// Load all key=value pairs into Properties object
			properties.load(stream);

		} catch (Exception e) {
			throw new RuntimeException("Failed to load config.properties file");
		}
	}

	// Return browser
	public static String getBrowser() {
		String browser = properties.getProperty("browser");

		if (browser == null || browser.isEmpty())
			throw new RuntimeException("browser not defined in config.properties file");

		return browser;
	}

	// return application url
	public static String getURL() {
		String url = properties.getProperty("url");

		if (url == null || url.isEmpty()) {
			throw new RuntimeException("URL for application isn't defined in config.properties file");
		}
		return url;
	}

	// return timeout limit for implicit wait
	public static int getImplcitWaitTime() {
		return Integer.parseInt(properties.getProperty("ImplicitWait"));
	}

	// return timeout limit for explicit wait
	public static int getExplicitWaitTime() {
		return Integer.parseInt(properties.getProperty("ExplicitWait"));
	}

	// return the position on which we need to select for station
	public static int getStationPosition() {
		return Integer.parseInt(properties.getProperty("Position"));
	}

	// return the number of days of journey
	public static int getNumberOfDays() {
		return Integer.parseInt(properties.getProperty("Days"));
	}

	// return url for orange hrm site
	public static String getHRMUrl() {
		String value = properties.getProperty("orangeUrl");
		if (value == null)
			throw new RuntimeException("Orange Hrm's URL not found in config.properties");
		return value;
	}
	
	public static boolean get(String str) {
		return Boolean.parseBoolean(properties.getProperty(str));
	}

}
