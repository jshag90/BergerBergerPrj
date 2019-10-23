package com.ji.burger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ji.burger.service.GetBuergerMenuService;

@RestController
public class RestApiController {

	@Autowired(required = false)
	GetBuergerMenuService getBurgerMenuService;

	@RequestMapping(value = "getBergerMenus", method = RequestMethod.POST)
	public Object getBergerMenus(@RequestParam("brand") String brand, 
			@RequestParam("category") String category) {

		getBurgerMenuService = new GetBuergerMenuService();
		List<Object> result = getBurgerMenuService.getBergerMenus(brand, category);

		return result;

	}

}
