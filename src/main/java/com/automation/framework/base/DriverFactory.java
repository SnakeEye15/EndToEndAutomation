package com.automation.framework.base;

import com.automation.framework.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Dimension;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class DriverFactory {

	// ThreadLocal ensures each test thread gets its own driver instance
	// Ensuring parallel execution runs smooth
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	// Initialize browser based on config.properties file
	public static void initDriver() {
		String browser = ConfigReader.getBrowser();
		boolean headless=ConfigReader.get("headless");
		// making switch statement to decide on browser to initialize
		switch (browser.toLowerCase()) {
		
		case "chrome":
			ChromeOptions options=new ChromeOptions();
			if(headless) {
			options.addArguments("--headless=new");
			//options.addArguments("--window-size=1920,1080");
			}
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(options));
			break;

		case "edge":
			try {
		        WebDriverManager.edgedriver().setup();
		    } catch (Exception e) {
		        System.out.println("WDM failed → using local EdgeDriver");
		        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
		    }
			EdgeOptions edgeOption= new EdgeOptions();
			if(headless) {
				edgeOption.addArguments("--headless=true");
			}
		    driver.set(new EdgeDriver(edgeOption));
		    break;

		case "firefox":
			FirefoxOptions fireOption= new FirefoxOptions();
			if(headless) {
				fireOption.addArguments("--headless=new");
			}
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			break;

		default:
			throw new RuntimeException("Unsupported browser: " + browser);
		}
		if(headless) {
			Dimension windowSize= new Dimension(1920,1080);
			getDriver().manage().window().setSize(windowSize);
		}else {
		getDriver().manage().window().maximize();}

		// Global implicit wait for mitigating few initial no element found error
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplcitWaitTime()));

	}

	private static org.openqa.selenium.Dimension Dimensions(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	// Returns driver for current executing thread
	public static WebDriver getDriver() {
		return driver.get();
	}

	// Closes browser and removes driver from thread memory
	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

}
