package com.demo.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.demo.dto.Account;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {
	public final String JSON_DATA_FILE = "accounts.json";
	public final ClassLoader classLoader = getClass().getClassLoader();

	public <T> Account findObject(String userId, Class<T> object) {
		ObjectMapper mapper = new ObjectMapper();
		Account returnedAccount = null;
		TypeReference<List<T>> mapType = new TypeReference<List<T>>() {
		};
		try {
			T genericObject = object.newInstance();
			List<T> objectsList = mapper.readValue(new File(JSON_DATA_FILE), mapType);
			if (genericObject instanceof Account) {
				// Now start searching in the list
				List<Account> accounts = new GenericsUtils().convertGenericListIntoObjectsList(objectsList,
						Account.class);
				returnedAccount = accounts.stream().filter(account -> account.getAccountId().equalsIgnoreCase(userId))
						.findAny().orElse(null);
				return returnedAccount;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnedAccount;
	}

	
	public <T> boolean addNewObject(T object) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		TypeReference<List<T>> mapType = new TypeReference<List<T>>() {
		};
		try {
			List<T> objectsList = objectMapper.readValue(new File(JSON_DATA_FILE), mapType);
			for (T loopObject : objectsList) {
				System.out.println(loopObject.toString());
			}
			objectsList.add(object);
			writeListToJsonFile(objectsList);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public <T> boolean writeListToJsonFile(List<T> list) {
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
			writer.writeValue(new File(JSON_DATA_FILE), list);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	

	public <T> String convertListIntoJson(List<T> list) {
		return null;
	}
}
