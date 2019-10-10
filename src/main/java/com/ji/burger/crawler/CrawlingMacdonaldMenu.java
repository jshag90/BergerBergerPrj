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

	// WebDriver
	private WebDriver driver;

	private List<WebElement> webElement;

	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/selenium/chromedriver.exe";

	// 크롤링 할 URL
	private String base_url, base_url2;
	
	private String loginId, loginPw;

	public CrawlingMacdonaldMenu(String loginId, String loginPw) {
		super();
		this.loginId = loginId;
		this.loginPw = loginPw;

		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// Driver SetUp
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);
		driver = new ChromeDriver(options);

		base_url = "https://www.mcdelivery.co.kr/kr/home.html";

		base_url2 = "https://www.mcdelivery.co.kr/kr/menu.html";
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
			result.add(bergerAndSetList);
			
			Thread.sleep(1000);
			
			clickMenu(2);
			
			Thread.sleep(1000);
			
			List<Object> snackAndSideList = readProductionMenuInfo("스낵&사이드");
			result.add(snackAndSideList);
			
			Thread.sleep(1000);
			
			clickMenu(3);
			
			Thread.sleep(1000);
			
			List<Object> drinkList  = readProductionMenuInfo("음료");
			result.add(drinkList);
			
			Thread.sleep(1000);
			
			clickMenu(4);
			
			Thread.sleep(1000);
			
			List<Object> desertList = readProductionMenuInfo("디저트");
			result.add(desertList);
			
			clickMenu(5);
			
			Thread.sleep(1000);
			
			List<Object> happymealList =readProductionMenuInfo("해피밀®");
			result.add(happymealList);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			driver.close();
		}

		return result;
	}

	public List<Object> readProductionMenuInfo(String category) {
		
		List<Object> result = new ArrayList<Object>();
		Map<String,Object> infoMap= new HashMap<String, Object>();
		
		List<WebElement> prductTitle =  driver.findElements(By.className("product-title"));
		List<WebElement> prductPrice =  driver.findElements(By.className("starting-price"));
		
		for(int index = 0; index<prductTitle.size(); index++) {
//			System.out.println(prductTitle.get(index).getText());
			String priceStr = prductPrice.get(index).getText().trim().replace(" ", "");
//			System.out.println(priceStr.replace("₩", ""));
			
			infoMap.put("CATEGORY", category);
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
