package com.ji.burger.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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

	private InitCrawlingSeleniumDriver initCSDriver;
	private WebDriver driver;
	private String base_url = "https://www.kfckorea.com";;

	public CrawlingKfcMenu() {
		super();

		initCSDriver = new InitCrawlingSeleniumDriver();
		driver = initCSDriver.initDriverSetup();

	}

	public List<Object> excuteCrawl() {

		List<Object> result = new ArrayList<Object>();

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

			List<Object> chickenAndSetList = readProductionMenuInfo("치킨&세트");
			result.add(chickenAndSetList);

			Thread.sleep(1000);

			clickSlideMenu("버거&세트");

			Thread.sleep(1000);

			List<Object> bergerAndSetList = readProductionMenuInfo("버거&세트");
			result.add(bergerAndSetList);

			Thread.sleep(1000);

			clickSlideMenu("스낵&사이드");

			Thread.sleep(1000);

			List<Object> snackAndSideList = readProductionMenuInfo("스낵&사이드");
			result.add(snackAndSideList);

			Thread.sleep(1000);

			clickSlideMenu("음료");

			Thread.sleep(1000);

			List<Object> drinkList = readProductionMenuInfo("음료");
			result.add(drinkList);

			Thread.sleep(1000);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			driver.close();
		}
		
		return result;

	}

	public List<Object> readProductionMenuInfo(String category) {

		List<Object> result = new ArrayList<Object>();

		WebElement webElementPriPrice = driver.findElement(By.tagName("section"));
		List<WebElement> webPrdList = webElementPriPrice.findElements(By.tagName("li"));

		for (int i = 7; i < webPrdList.size(); i++) {

			List<WebElement> imgData = webPrdList.get(i).findElements(By.tagName("img"));
			List<WebElement> hData = webPrdList.get(i).findElements(By.tagName("h3"));
			List<WebElement> liData = webPrdList.get(i).findElements(By.className("price"));
			
			for (int index = 0; index < hData.size(); index++) {
				Map<String, Object> infoMap = new HashMap<String, Object>();
				infoMap.put("IMG", imgData.get(index).getAttribute("src"));
				infoMap.put("NAME", hData.get(index).getText());
				infoMap.put("PRICE", liData.get(index).getText());
				infoMap.put("CATEGORY", category);

				result.add(infoMap);
			}
		}

		return result;
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
