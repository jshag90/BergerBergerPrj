package com.ji.burger.controller;

import com.ji.burger.util.CrawlingBurgerKingMenu;
import com.ji.burger.util.CrawlingKfcMenu;
import com.ji.burger.util.CrawlingMacdonaldMenu;

public class CrawlerController {

	private static CrawlingBurgerKingMenu bergerKing;
	private static CrawlingMacdonaldMenu macdonald;
	private static CrawlingKfcMenu kfc;

	public static void main(String[] args) {

		String pw = "##";

		bergerKing = new CrawlingBurgerKingMenu("jshag90@naver.com", pw);
		for (Object data : bergerKing.excuteCrawl()) {
			System.out.println(data);
		}

		macdonald = new CrawlingMacdonaldMenu("wltjsgkr90@hanmail.net", pw);
		for (Object data : macdonald.excuteCrawl()) {
			System.out.println(data);
		}
//		
		kfc = new CrawlingKfcMenu();
		for (Object data : kfc.excuteCrawl()) {
			System.out.println(data);
		}
//		

	}

}
