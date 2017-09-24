package com.demo.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JSONIOUtils {
	final ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

	public <T> boolean writeListToJsonFile(String jsonDataFile, List<T> list) {
		try {
			writer.writeValue(new File(jsonDataFile), list);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public <T> boolean writeObjecctToJsonFile(String jsonDataFile, T object) {
		try {
			writer.writeValue(new File(jsonDataFile), object);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
