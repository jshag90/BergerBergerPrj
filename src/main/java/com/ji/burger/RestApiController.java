package com.ji.burger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	@RequestMapping("getnaversearchword")
	public String getNaverSearchWord() {

		Document doc = null;
		String result = "";
		List<String> resultList = new ArrayList<String>();

		try {
			doc = Jsoup.connect("https://datalab.naver.com/keyword/realtimeList.naver?where=main").get();
		} catch (IOException e1) {

		}

		Elements elements = doc.select("ul.rank_list li span");
		List<String> keywordList20 = new ArrayList<String>();

		int index = 0;
		for (Element e : elements) {
			index++;
			if (index > 21) {
				break;
			} else {
				keywordList20.add(e.text());
			}
		}

		for (int rank = 1; rank < keywordList20.size(); rank++) {
			System.out.println(rank + "위 : " + keywordList20.get(rank));
			result = rank + "위 : " + keywordList20.get(rank);
			resultList.add(result);
		}
		
		return String.valueOf(resultList);

	}

}
