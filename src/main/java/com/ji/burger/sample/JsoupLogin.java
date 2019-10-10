package com.ji.burger.sample;

import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupLogin {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
				 try
			        {
			            Response initialResponse;

			            initialResponse = Jsoup.connect("https://www.burgerking.co.kr/#/login")
			                    .execute();

			            Response loginResponse = Jsoup
			                    .connect("https://www.burgerking.co.kr/#/login")
			                    .cookies(initialResponse.cookies())
			                    .data("UserName", "jshag90")
			                    .data("Password", "goffhdn90#")
			                    .data("IsRememberMe", "false")
			                                    .method(Method.POST)
			                    .execute();
			                    //example of internal moody's page.
			            Document doc = Jsoup.connect("https://www.burgerking.co.kr/#/deliveryHome")
			                    .cookies(loginResponse.cookies()).timeout(3000000).get();

			                    //simple div selection example
			            System.out.println(doc);
			        }
			        catch (IOException e)
			        {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        }

	}

}
