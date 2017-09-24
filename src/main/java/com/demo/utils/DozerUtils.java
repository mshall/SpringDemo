package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

public class DozerUtils {
	public <T, U> List<U> mapList(final Mapper mapper, final List<T> source, final Class<U> destType) {

		final List<U> dest = new ArrayList<>();

		for (T element : source) {
			dest.add(mapper.map(element, destType));
		}

		return dest;
	}
	
	public <T,U> U mapObjects(final Mapper mapper, final T source, final Class<U> destType ){
		U destinationObject = null;
		destinationObject = mapper.map(source, destType);
		return destinationObject;
		
	}
}
