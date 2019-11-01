package com.ji.burger.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class StringUtils {

	public static Map JsonToMap(String data) {
		Map<String, Object> result = new HashMap<String, Object>();

		Gson gson = new Gson();
		String json = data;
		result = (Map<String, Object>) gson.fromJson(json, result.getClass());

		return result;
	}
}
