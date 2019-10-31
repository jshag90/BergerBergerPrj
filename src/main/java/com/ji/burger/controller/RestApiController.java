package com.ji.burger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ji.burger.service.GetBuergerMenuService;

@RestController
public class RestApiController {

	@Autowired(required = false)
	GetBuergerMenuService getBurgerMenuService;
	
	@CrossOrigin("*")
	@RequestMapping(value = "getBurgerCategory", method = RequestMethod.POST)
	public Object getBurgerCategory(@RequestBody String data ) 
	{
		
		JsonParser jp = new JsonParser();
		Object obj = jp.parse( data );
		JsonObject jsonObj = (JsonObject) obj;
		JsonElement brand = jsonObj.get("BRAND");
		getBurgerMenuService = new GetBuergerMenuService();
		List<Object> result = getBurgerMenuService.getBurgerCategorys(brand.getAsString());

		return result;

	}

	/**
	 * 
	 * @param BRAND
	 * @param CATEGORY
	 * @param PRICEORDER ASC|DESC
	 * @return
	 */
	@RequestMapping(value = "getBurgerMenus", method = RequestMethod.POST)
	public Object getBurgerMenus(@RequestParam("BRAND") String brand, 
												  @RequestParam("CATEGORY") String category, 
												  @RequestParam("PRICE_ORDER") String priceOrder) 
	{

		getBurgerMenuService = new GetBuergerMenuService();
		List<Object> result = getBurgerMenuService.getBurgerMenus(brand, category, priceOrder);

		return result;

	}

}
