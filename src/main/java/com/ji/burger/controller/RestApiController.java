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
import com.ji.burger.util.StringUtils;

@RestController
public class RestApiController {

	@Autowired(required = false)
	GetBuergerMenuService getBurgerMenuService;
	
	@CrossOrigin("*")
	@RequestMapping(value = "getBurgerCategory", method = RequestMethod.POST)
	public Object getBurgerCategory(@RequestBody String data ) 
	{
		
		String brand = (String) StringUtils.JsonToMap(data).get("BRAND");
		
		getBurgerMenuService = new GetBuergerMenuService();
		List<Object> result = getBurgerMenuService.getBurgerCategorys(brand);

		return result;

	}

	/**
	 * 
	 * @param BRAND
	 * @param CATEGORY
	 * @param PRICEORDER ASC|DESC
	 * @return
	 */
	@CrossOrigin("*")
	@RequestMapping(value = "getBurgerMenus", method = RequestMethod.POST)
	public Object getBurgerMenus( @RequestBody String data ) 
	{
		
		String brand = (String) StringUtils.JsonToMap(data).get("BRAND");
		String category = (String) StringUtils.JsonToMap(data).get("CATEGORY");
		String priceOrder = (String) StringUtils.JsonToMap(data).get("PRICE_ORDER");

		getBurgerMenuService = new GetBuergerMenuService();
		List<Object> result = getBurgerMenuService.getBurgerMenus(brand, category, priceOrder);

		return result;

	}

}
