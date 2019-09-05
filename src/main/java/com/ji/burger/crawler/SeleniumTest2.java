package com.ji.burger.crawler;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest2 {

	public static void main(String[] args) {

		SeleniumTest2 selTest = new SeleniumTest2();
		selTest.crawl();

	}

	// WebDriver
	private WebDriver driver;

	private List<WebElement> webElement;
	private List<WebElement> webElement2;

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/selenium/chromedriver.exe";

	// 크롤링 할 URL
	private String base_url;

	public SeleniumTest2() {
	        super();
	 
	        //System Property SetUp
	        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	        
	                
	        //Driver SetUp
	         ChromeOptions options = new ChromeOptions();
	         options.setCapability("ignoreProtectedModeSettings", true);
	         driver = new ChromeDriver(options);
	        
	        base_url = "https://www.burgerking.co.kr/#/login";
	        
	        
	        
	    }

	public void crawl() {

		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);

			webElement = driver.findElements(By.tagName("input"));
			
			 String burgerKingId = "jshag90@naver.com";
			 webElement.get(0).sendKeys(burgerKingId);
			
			String burgerKingPw = "####";
			webElement.get(1).sendKeys(burgerKingPw);
			
//            System.out.println(driver.getPageSource());
//			Thread.sleep(20000);

			// 로그인 버튼 클릭
//			webElement2 =  driver.findElements(By.tagName("span"));
//			webElement2.get(4).click();


		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			driver.close();
		}

	}

}
