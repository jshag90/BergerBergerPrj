package com.ji.burger.sample;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

public class JsoupClickTest {

	public static void main(String[] args) {

		try {
			
			Connection.Response res = Jsoup.connect("https://www.burgerking.co.kr/#/login")
															.data("ds_email", "jshag90@naver.com", "passwd", "goffhdn90#")
															.method(Connection.Method.POST)
															.execute();
			
			System.out.println(res.parse());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
