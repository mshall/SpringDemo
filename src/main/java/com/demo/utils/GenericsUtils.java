package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericsUtils {

	public <T, H> List<H> convertGenericListIntoObjectsList(List<T> genericList, Class<H> targetClass) {
		ObjectMapper mapper = new ObjectMapper();
		List<H> convertedList = new ArrayList<>();
		for (T genericObject : genericList) {
			convertedList.add(mapper.convertValue(genericObject,targetClass));
		}
		return convertedList;
	}

}
