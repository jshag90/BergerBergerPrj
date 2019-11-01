package com.ji.burger.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InitCrawlingSeleniumDriver {

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/selenium/chromedriver.exe";

	// WebDriver
	private WebDriver driver;

	// System Property SetUp
	public InitCrawlingSeleniumDriver() {
		super();
		
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
	}

	// Driver SetUp
	public WebDriver initDriverSetup() {

		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);
		driver = new ChromeDriver(options);

		return driver;
	}

}
