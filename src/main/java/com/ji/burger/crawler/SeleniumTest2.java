package com.ji.burger.crawler;

import java.util.Iterator;
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
	private List<WebElement> webElement3;

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/selenium/chromedriver.exe";

	// 크롤링 할 URL
	private String base_url, base_url2;

	public SeleniumTest2() {
	        super();
	 
	        //System Property SetUp
	        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	        
	                
	        //Driver SetUp
	         ChromeOptions options = new ChromeOptions();
	         options.setCapability("ignoreProtectedModeSettings", true);
	         driver = new ChromeDriver(options);
	        
	        base_url = "https://www.burgerking.co.kr/#/login";
	        
	        
	        base_url2 = "https://www.burgerking.co.kr/#/deliveryHome";
	    }

	public void crawl() {

		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);

			webElement = driver.findElements(By.tagName("input"));
			
			 String burgerKingId = "jshag90@naver.com";
			 webElement.get(0).sendKeys(burgerKingId);
			 Thread.sleep(1000);
			
			String burgerKingPw = "####";
			webElement.get(1).sendKeys(burgerKingPw);
			Thread.sleep(1000);
			
			 List<WebElement> spans = driver.findElements(By.tagName("span"));
		     Iterator<WebElement> i = spans.iterator();
		     
			int index = 0;
			while (i.hasNext()) {
				WebElement span = i.next();
				index++;
				if (index == 39) {
					Thread.sleep(1000);
					span.click();
					break;
				}
			}
		        
		    Thread.sleep(3000);
		    
		    driver.get(base_url2);
		    Thread.sleep(3000);
			webElement2 = driver.findElements(By.className("tit"));
			Iterator<WebElement> ps = webElement2.iterator();

			while (ps.hasNext()) {
				WebElement pTag = ps.next();
				System.out.println(pTag.getText());
			}
			
			webElement3 =  driver.findElements(By.className("price"));
			Iterator<WebElement> spanTags = webElement3.iterator();
			
			while (spanTags.hasNext()) {
				WebElement span = spanTags.next();

				System.out.println(span.getText());
			}
			
//			driver.get("https://www.burgerking.co.kr/#/deliveryHome");
//
//			System.out.println(driver.getPageSource());



		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			driver.close();
		}

	}

}
