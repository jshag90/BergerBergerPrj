package com.ji.burger.crawler;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ch.qos.logback.classic.Logger;

public class CrawlingBergerKingMenu {

	public static void main(String[] args) {
		Document doc = null;

		try {
			doc = Jsoup.connect("https://namu.wiki/w/%EB%B2%84%EA%B1%B0%ED%82%B9/%EB%A9%94%EB%89%B4").get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Elements elements = doc.select("table.wiki-table tbody tr");
		
		System.out.println("버거이름/단품/세트/칼로리");
		for (int i = 1; i < elements.size(); i++) {
			
			if (i >= 16 && i <= 40) { //와퍼류
				Element row = elements.get(i);
				Elements cols = row.select("td");
				
				if(cols.size() > 1) {
					if (cols.size()==5) {
						System.out.println(cols.get(0).text() + "/" + cols.get(1).text()+"/"+cols.get(2).text()+"/"+cols.get(4).text());
					}else if(cols.size()==4) {
						System.out.println(cols.get(0).text() + "/" + cols.get(1).text()+"/"+cols.get(2).text()+"/"+" 0/0");
					}else if(cols.size()==3) {
						System.out.println(cols.get(0).text() + "/" + cols.get(1).text()+"/"+cols.get(2).text()+"/"+" 0/0");
					}else {
						
					}
				}
			}
			
		}

	}
}
