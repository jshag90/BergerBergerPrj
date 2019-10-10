package com.ji.burger;

import java.util.List;

import com.ji.burger.crawler.CrawlingBergerKingMenu;
import com.ji.burger.crawler.CrawlingKfcMenu;
import com.ji.burger.crawler.CrawlingMacdonaldMenu;

public class CrawlerController {
	
	private static CrawlingBergerKingMenu bergerKing;
	private static CrawlingMacdonaldMenu macdonald;
	private static CrawlingKfcMenu kfc;
	
	public static void main(String[] args) {
		
		String pw ="####";
		
		bergerKing= new CrawlingBergerKingMenu("jshag90@naver.com", pw);
		for(Object data: bergerKing.excuteCrawl()) {
			System.out.println(data);
		}
		
		macdonald = new CrawlingMacdonaldMenu("wltjsgkr90@hanmail.net", pw);
		for(Object data: macdonald.excuteCrawl()) {
			System.out.println(data);
		}
//		
//		kfc = new CrawlingKfcMenu();
//		kfc.excuteCrawl();
		
	}
	
}
