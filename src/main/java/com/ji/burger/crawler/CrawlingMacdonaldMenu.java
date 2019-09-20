package com.ji.burger.crawler;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CrawlingMacdonaldMenu {

	public static void main(String[] args) {

		CrawlingMacdonaldMenu selTest = new CrawlingMacdonaldMenu();
		selTest.crawl();

	}

	// WebDriver
	private WebDriver driver;

	private List<WebElement> webElement;

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/selenium/chromedriver.exe";

	// 크롤링 할 URL
	private String base_url, base_url2;

	public CrawlingMacdonaldMenu() {
		super();

		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// Driver SetUp
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);
		driver = new ChromeDriver(options);

		base_url = "https://www.mcdelivery.co.kr/kr/home.html";

		base_url2 = "https://www.mcdelivery.co.kr/kr/menu.html";
	}

	public void crawl() {

		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);

			webElement = driver.findElements(By.tagName("input"));

			String macdonaldId = "wltjsgkr90@hanmail.net";
			webElement.get(0).sendKeys(macdonaldId);
			Thread.sleep(1000);

			String macdonaldPw = "goffhdn90#";
			webElement.get(1).sendKeys(macdonaldPw);
			Thread.sleep(1000);

			WebElement webElement = driver.findElement(By.className("form-actions"));
			webElement.submit();

			Thread.sleep(3000);
			
			WebElement newOrderBtn = driver.findElement(By.xpath("//fieldset//a"));
			newOrderBtn.click();
			
			driver.get(base_url2);
			
			Thread.sleep(3000);

//			JavascriptExecutor js = null;
//
//			if (driver instanceof JavascriptExecutor) {
//				js = (JavascriptExecutor) driver;
//			}
//			js.executeScript("return document.getElementsByClassName('btn_close')[0].click();");
//
//			Thread.sleep(1000);
//
//			readProductionMenuInfo("스폐셜", 2);
//
//			Thread.sleep(1000);
//
//			clickMenu(1);
//
//			Thread.sleep(1000);
//
//			readProductionMenuInfo("프리미엄", 1);
//
//			Thread.sleep(1000);
//
//			clickMenu(2);
//
//			Thread.sleep(1000);
//
//			readProductionMenuInfo("와퍼&버거", 1);
//
//			Thread.sleep(1000);
//
//			clickMenu(3);
//
//			Thread.sleep(1000);
//
//			readProductionMenuInfo("치킨버거", 1);
//
//			Thread.sleep(1000);
//
//			clickMenu(4);
//
//			Thread.sleep(1000);
//
//			readProductionMenuInfo("사이드", 1);
//
//			Thread.sleep(1000);
//
//			clickMenu(5);
//
//			Thread.sleep(1000);
//
//			readProductionMenuInfo("음료", 1);
			
			// System.out.println(driver.getPageSource());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			driver.close();
		}

	}

	public void readProductionMenuInfo(String category, int lastIndex) {
		System.out.println("///////////////////" + category + "///////////////////////");
		WebElement webElementPriPrice = driver.findElement(By.className("prdmenu_list"));
		List<WebElement> webPrdList = webElementPriPrice.findElements(By.tagName("li"));

		for (WebElement list : webPrdList) {
			String[] productInfo = list.getText().split("\n");
			System.out.println(productInfo[0]);
			String price = productInfo[lastIndex].replace("~", "");
			price = price.replace("₩", "");
			System.out.println(price);
		}

	}

	public void clickMenu(int index) {
		List<WebElement> welItem = driver.findElements(By.className("item3"));

		for (WebElement el : welItem) {
			el.findElements(By.tagName("li")).get(index).click();
		}

	}

}