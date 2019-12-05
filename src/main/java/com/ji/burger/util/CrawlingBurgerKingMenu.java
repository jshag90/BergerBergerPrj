package com.ji.burger.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.ji.burger.util.InitCrawlingSeleniumDriver;

public class CrawlingBurgerKingMenu {

	public static void main(String[] args) {

//		CrawlingBergerKingMenu selTest = new CrawlingBergerKingMenu("jshag90@naver.com","goffhdn90#");
//		List<Object> result = selTest.excuteCrawl();
//		
//		for(Object data: result) {
//			System.out.println(data);
//		}

	}

	
	private InitCrawlingSeleniumDriver initCSDriver;
	// WebDriver
	private WebDriver driver;

	private List<WebElement> webElement;

	// 크롤링 할 URL
	private String base_url = "https://www.burgerking.co.kr/#/login";
	private String base_url2= "https://www.burgerking.co.kr/#/deliveryHome";
	
	private String loginId, loginPw;

	public CrawlingBurgerKingMenu(String loginId, String loginPw) {
		super();
		
		this.loginId = loginId;
		this.loginPw = loginPw;
		
		initCSDriver = new InitCrawlingSeleniumDriver();
		driver = initCSDriver.initDriverSetup();
	}

	public List<Object> excuteCrawl() {
		
		List<Object> result = new ArrayList<Object>();
		
		try {
			driver.get(base_url);

			webElement = driver.findElements(By.tagName("input"));

			String burgerKingId =loginId;
			webElement.get(0).sendKeys(burgerKingId);
			Thread.sleep(1000);

			String burgerKingPw = loginPw;
			webElement.get(1).sendKeys(burgerKingPw);
			Thread.sleep(1000);

			WebElement webElement = driver.findElement(By.className("btn01"));
			webElement.click();

			driver.get(base_url2);
			Thread.sleep(3000);

			JavascriptExecutor js = null;

			if (driver instanceof JavascriptExecutor) {
				js = (JavascriptExecutor) driver;
			}
			js.executeScript("return document.getElementsByClassName('btn_close')[0].click();");

			Thread.sleep(1000);

			List<Object> specialList = readProductionMenuInfo("스폐셜", 2);
			for(Object sList:specialList) {
				Map<String,Object> data = (Map<String, Object>) sList;
				result.add(data);
			}
			
			Thread.sleep(1000);

			clickMenu(1);

			Thread.sleep(1000);

			List<Object> primiumList  = readProductionMenuInfo("프리미엄", 1);
			for(Object pList:primiumList) {
				Map<String,Object> data = (Map<String, Object>) pList;
				result.add(data);
			}
			
			Thread.sleep(1000);

			clickMenu(2);

			Thread.sleep(1000);

			List<Object> wapperAndBurgerList  = readProductionMenuInfo("와퍼&버거", 1);
			for(Object wABList:wapperAndBurgerList) {
				Map<String,Object> data = (Map<String, Object>) wABList;
				result.add(data);
			}
			
			Thread.sleep(1000);

			clickMenu(3);

			Thread.sleep(1000);

			List<Object> chickenBurgerList  = readProductionMenuInfo("치킨버거", 1);
			for(Object cBList:chickenBurgerList) {
				Map<String,Object> data = (Map<String, Object>) cBList;
				result.add(data);
			}
			
			Thread.sleep(1000);

			clickMenu(4);

			Thread.sleep(1000);

			List<Object> sideList  = readProductionMenuInfo("사이드", 1);
			for(Object sList:sideList) {
				Map<String,Object> data = (Map<String, Object>) sList;
				result.add(data);
			}
			
			Thread.sleep(1000);

			clickMenu(5);

			Thread.sleep(1000);

			List<Object> drinkList  = readProductionMenuInfo("음료", 1);
			for(Object dList:drinkList) {
				Map<String,Object> data = (Map<String, Object>) dList;
				result.add(data);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			driver.close();
		}
		
		return result;

	}

	public List<Object> readProductionMenuInfo(String category, int lastIndex) {
		
		List<Object> result = new ArrayList<Object>();
		
		WebElement webElementPriPrice = driver.findElement(By.className("prdmenu_list"));
		List<WebElement> webPrdList = webElementPriPrice.findElements(By.tagName("li"));

		for (WebElement list : webPrdList) {
			Map<String,Object> infoMap= new HashMap<String, Object>();
			
			WebElement img = list.findElement(By.tagName("img"));
			String src = img.getAttribute("src");
			String[] productInfo = list.getText().split("\n");
//			System.out.println(productInfo[0]);
			String price = "";
			try {
				price = productInfo[lastIndex].replace("~", "");
			}catch(Exception e) {
				price = productInfo[1].replace("~", "");
			}
			
			price = price.replace("₩", "");
//			System.out.println(price);
			
			
			infoMap.put("CATEGORY", category);
			infoMap.put("IMG", src);
			infoMap.put("NAME", productInfo[0]);
			infoMap.put("PRICE", price);
			
			result.add(infoMap);
		}
		
		return result;

	}

	public void clickMenu(int index) {
		List<WebElement> welItem = driver.findElements(By.className("item3"));

		for (WebElement el : welItem) {
			el.findElements(By.tagName("li")).get(index).click();
		}

	}

}
