package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericsUtils {

	public <T, H> List<H> convertGenericListIntoObjectsList(List<T> genericList, Class<H> targetClass) {
		ObjectMapper mapper = new ObjectMapper();
		List<H> convertedList = new ArrayList<>();
		for (T genericObject : genericList) {
			convertedList.add(mapper.convertValue(genericObject, targetClass));
		}
		return convertedList;
	}

	public <T> T convertJsonIntoObject(String jsonObject, Class<T> targetClass) {
		ObjectMapper mapper = new ObjectMapper();
		T object = mapper.convertValue(jsonObject, targetClass);
		return object;
	}

	public <T> String convertObjectintoJson(T object) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonObject = "";
		try {
			jsonObject = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
}
