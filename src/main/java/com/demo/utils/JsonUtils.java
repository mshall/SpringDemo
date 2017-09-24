package com.demo.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dto.Account;
import com.demo.dto.AccountDTO;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.flipkart.zjsonpatch.JsonDiff;

public class JsonUtils {
	@Autowired
	private DozerBeanMapper dozerMapper;
	public final String JSON_DATA_FILE = "accounts.json";
	public final ClassLoader classLoader = getClass().getClassLoader();
	ObjectMapper objectMapper = new ObjectMapper();
	GenericsUtils genericsUtils = new GenericsUtils();
	DozerUtils dozerUtils = new DozerUtils();
	JSONIOUtils jsonioUtils = new JSONIOUtils();

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
				List<Account> accounts = genericsUtils.convertGenericListIntoObjectsList(objectsList, Account.class);
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

	public <T> List<T> getAllJsonObjects(Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		List<T> objectsList = new ArrayList<>();
		TypeReference<List<T>> mapType = new TypeReference<List<T>>() {
		};
		try {
			objectsList = mapper.readValue(new File(JSON_DATA_FILE), mapType);
			return objectsList;
		} catch (Exception e) {
			e.printStackTrace();
			return objectsList;
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

	public <T> AccountDTO updateAccount(String accountId, AccountDTO accountDTO, Class<T> clazz) {
		TypeReference<List<T>> mapType = new TypeReference<List<T>>() {
		};
		AccountDTO accountDTOResult = new AccountDTO();
		Account account = findObject(accountId, Account.class);
		try {
			if (findObject(accountDTO.getAccountId(), Account.class) == null) {
				accountDTOResult.setCode(404);
				accountDTOResult.setMessage("Error, Account doesn't exists");
				return accountDTOResult;
			} else {
				List<T> objectsList = objectMapper.readValue(new File(JSON_DATA_FILE), mapType);
				List<Account> accounts = genericsUtils.convertGenericListIntoObjectsList(objectsList, Account.class);
				accounts.stream()
						.filter(filterAccount -> filterAccount.getAccountId().equalsIgnoreCase(account.getAccountId()))
						.map(value -> {
							Account resultAccount = new Account();
							System.out.println("Account DTO: -->" + accountDTO.getCustomerName());
							value.setAccountType(accountDTO.getAccountType());
							value.setAmount(accountDTO.getAmount());
							value.setCustomerName(accountDTO.getCustomerName());
							value.setCurrency(accountDTO.getCurrency());
//							dozerMapper.map(accountDTO, value);
							return value;
						}).collect(Collectors.toList());

				// Now write the newly updated list to the file
				jsonioUtils.writeListToJsonFile(JSON_DATA_FILE, accounts);
				accountDTO.setCode(100);
				accountDTO.setMessage("Account has been successfully updated!");
				return accountDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JsonNode patchJson(String jsonSource, String jsonTarget) {
		JsonNode source = constructJsonNode(jsonSource);
		JsonNode target = constructJsonNode(jsonTarget);
		JsonNode patch = JsonDiff.asJson(source, target);
		return patch;
	}

	public JsonNode constructJsonNode(String jsonString) {
		JsonFactory factory = objectMapper.getFactory();

		try {
			JsonParser parser = factory.createParser(jsonString);
			JsonNode actualObj = objectMapper.readTree(parser);
			return actualObj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
