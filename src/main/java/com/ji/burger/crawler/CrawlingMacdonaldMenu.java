package com.ji.burger.crawler;

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

public class CrawlingMacdonaldMenu {

//	public static void main(String[] args) {
//
//		CrawlingMacdonaldMenu selTest = new CrawlingMacdonaldMenu();
//		selTest.excuteCrawl();
//
//	}

	private InitCrawlingSeleniumDriver initCSDriver;
	
	// WebDriver
	private WebDriver driver;
	private List<WebElement> webElement;
	private String base_url=  "https://www.mcdelivery.co.kr/kr/home.html";
	private String base_url2 ="https://www.mcdelivery.co.kr/kr/menu.html";
	
	private String loginId, loginPw;

	public CrawlingMacdonaldMenu(String loginId, String loginPw) {
		super();
		this.loginId = loginId;
		this.loginPw = loginPw;

		initCSDriver = new InitCrawlingSeleniumDriver();
		driver = initCSDriver.initDriverSetup();

	}

	public List<Object> excuteCrawl() {
		
		List<Object> result = new ArrayList<Object>();

		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);

			webElement = driver.findElements(By.tagName("input"));

			String macdonaldId  =loginId;
			webElement.get(0).sendKeys(macdonaldId);
			Thread.sleep(1000);

			String macdonaldPw = loginPw;
			webElement.get(1).sendKeys(macdonaldPw);
			Thread.sleep(1000);

			WebElement webElement = driver.findElement(By.className("form-actions"));
			webElement.submit();

			Thread.sleep(3000);
			
			WebElement newOrderBtn = driver.findElement(By.xpath("//fieldset//a"));
			newOrderBtn.click();
			
			driver.get(base_url2);
			
			Thread.sleep(1000);
			
			clickMenu(1);
			
			Thread.sleep(1000);
			
			List<Object> bergerAndSetList = readProductionMenuInfo("버거&세트");
			for(Object bASList:bergerAndSetList) {
				Map<String,Object> data = (Map<String, Object>) bASList;			
				result.add(data);
			}
			
			Thread.sleep(1000);
			
			clickMenu(2);
			
			Thread.sleep(1000);
			
			List<Object> snackAndSideList = readProductionMenuInfo("스낵&사이드");
			for(Object sASList:snackAndSideList) {
				Map<String,Object> data = (Map<String, Object>) sASList;			
				result.add(data);
			}
			
			Thread.sleep(1000);
			
			clickMenu(3);
			
			Thread.sleep(1000);
			
			List<Object> drinkList  = readProductionMenuInfo("음료");
			for(Object dList:drinkList) {
				Map<String,Object> data = (Map<String, Object>) dList;			
				result.add(data);
			}
			
			Thread.sleep(1000);
			
			clickMenu(4);
			
			Thread.sleep(1000);
			
			List<Object> desertList = readProductionMenuInfo("디저트");
			for(Object dsList:desertList) {
				Map<String,Object> data = (Map<String, Object>) dsList;			
				result.add(data);
			}
			
			clickMenu(5);
			
			List<Object> happymealList =readProductionMenuInfo("해피밀®");
			for(Object hList:happymealList) {
				Map<String,Object> data = (Map<String, Object>) hList;			
				result.add(data);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			driver.close();
		}

		return result;
	}

	public List<Object> readProductionMenuInfo(String category) {
		
		List<Object> result = new ArrayList<Object>();
		
		List<WebElement> prductImg =  driver.findElements(By.tagName("img"));
		List<WebElement> prductTitle =  driver.findElements(By.className("product-title"));
		List<WebElement> prductPrice =  driver.findElements(By.className("starting-price"));
		
		for(int index = 0; index<prductTitle.size(); index++) {
			Map<String,Object> infoMap= new HashMap<String, Object>();
//			System.out.println(prductTitle.get(index).getText());
			String priceStr = prductPrice.get(index).getText().trim().replace(" ", "");
//			System.out.println(priceStr.replace("₩", ""));
			String img = prductImg.get(index+1).getAttribute("src");
			infoMap.put("CATEGORY", category);
			infoMap.put("IMG", img);
			infoMap.put("NAME", prductTitle.get(index).getText());
			infoMap.put("PRICE", priceStr.replace("₩", ""));
			
			result.add(infoMap);
		}
		
		return result;

	}

	public void clickMenu(int index) {
		WebElement webElementSecondaryMenu= driver.findElement(By.className("secondary-menu"));
		List<WebElement> webMenuList = webElementSecondaryMenu.findElements(By.tagName("li"));
		WebElement menuEl = webMenuList.get(index);
		menuEl.findElement(By.className("secondary-menu-item-target")).click();

	}

}
