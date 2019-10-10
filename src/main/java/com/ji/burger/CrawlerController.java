package com.ji.burger;

import com.ji.burger.crawler.CrawlingBergerKingMenu;
import com.ji.burger.crawler.CrawlingKfcMenu;
import com.ji.burger.crawler.CrawlingMacdonaldMenu;

public class CrawlerController {
	
	private static CrawlingBergerKingMenu bergerKing;
	private static CrawlingMacdonaldMenu macdonald;
	private static CrawlingKfcMenu kfc;
	
	public static void main(String[] args) {
		
		String pw = "####";
		
		bergerKing = new CrawlingBergerKingMenu("jshag90@naver.com",pw);
		bergerKing.excuteCrawl();
		
		macdonald = new CrawlingMacdonaldMenu("wltjsgkr90@hanmail.net", pw);
		macdonald.excuteCrawl();
		
		kfc = new CrawlingKfcMenu();
		kfc.excuteCrawl();
		
	}
	
}
