package com.ji.burger.crawler;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CrawlingKfcMenu {

//	public static void main(String[] args) {
//
//		CrawlingKfcMenu selTest = new CrawlingKfcMenu();
//		selTest.excuteCrawl();
//
//	}

	// WebDriver
	private WebDriver driver;

	private List<WebElement> webElement;

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/selenium/chromedriver.exe";

	// 크롤링 할 URL
	private String base_url, base_url2;

	public CrawlingKfcMenu() {
		super();

		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// Driver SetUp
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);
		driver = new ChromeDriver(options);

		base_url = "https://www.kfckorea.com";

	}

	public void excuteCrawl() {

		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);

			WebElement category = driver.findElement(By.className("category"));
			WebElement categoryList = category.findElement(By.className("category-list"));
			List<WebElement> categoryMenu = categoryList.findElements(By.xpath("//li//a"));

			Thread.sleep(1000);
			for (WebElement menu : categoryMenu) {
				if (menu.getText().equals("DELIVERY")) {
					menu.click();
					break;
				}
			}

			Thread.sleep(1000);
			for (WebElement menu : categoryMenu) {
				if (menu.getText().equals("치킨&세트")) {
					menu.click();
					break;
				}
			}

			Thread.sleep(1000);

			readProductionMenuInfo();

			Thread.sleep(1000);

			clickSlideMenu("버거&세트");

			Thread.sleep(1000);

			readProductionMenuInfo();

			Thread.sleep(1000);

			clickSlideMenu("스낵&사이드");

			Thread.sleep(1000);

			readProductionMenuInfo();

			Thread.sleep(1000);

			clickSlideMenu("음료");

			Thread.sleep(1000);

			readProductionMenuInfo();

			Thread.sleep(1000);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			driver.close();
		}

	}

	public void readProductionMenuInfo() {

		WebElement webElementPriPrice = driver.findElement(By.tagName("section"));
		List<WebElement> webPrdList = webElementPriPrice.findElements(By.tagName("li"));

		for (int i = 7; i < webPrdList.size(); i++) {

			List<WebElement> hData = webPrdList.get(i).findElements(By.tagName("h3"));
			for (WebElement h : hData) {
				System.out.println(h.getText());
			}

			List<WebElement> liData = webPrdList.get(i).findElements(By.className("price"));
			for (WebElement ld : liData) {
				System.out.println(ld.getText());
			}

		}

	}

	public void clickSlideMenu(String categoryName) {
		List<WebElement> swiperSlide = driver.findElements(By.className("swiper-slide"));
		for (WebElement sSlide : swiperSlide) {
			if (sSlide.getText().equals(categoryName)) {
				sSlide.click();
				break;
			}
		}

	}

}
