package com.demo.service.impl;

import java.util.logging.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dto.Account;
import com.demo.dto.Account.AccountBuilder;
import com.demo.dto.Account.AccountBuilder.AccountType;
import com.demo.dto.Account.AccountBuilder.Currency;
import com.demo.dto.AccountDTO;
import com.demo.service.AccountService;
import com.demo.utils.JsonUtils;

@Service
public class AccountServiceImpl implements AccountService {
	protected Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());
	JsonUtils JsonUtils = new JsonUtils();
	@Autowired
	private DozerBeanMapper dozerMapper;

	@Override
	public AccountDTO getAccountDetails(String accountId) {
		Account account = JsonUtils.findObject(accountId, Account.class);
		AccountDTO accountDTO = new AccountDTO();	
		if(account == null){
			accountDTO.setCode(404);
			accountDTO.setMessage("Error, Account doesn't exists");
		}else{
			accountDTO.setCode(101);
			accountDTO.setMessage("Account has been successfully retrieved");
			dozerMapper.map(account, accountDTO);
		}
		return accountDTO;
	}

	@Override
	public AccountDTO addAccount(AccountDTO accountDTO) {
		AccountBuilder accountBuilder = new AccountBuilder();
		AccountDTO accountDTOResult = new AccountDTO();
		Account account = accountBuilder.setAccountId(accountDTO.getAccountId())
				.setAccountType(AccountType.valueOf(accountDTO.getAccountType().toUpperCase()))
				.setCurrency(Currency.valueOf(accountDTO.getCurrency().toUpperCase()))
				.setCustomerName(accountDTO.getCustomerName()).build();
		if (getAccountDetails(accountDTO.getAccountId()).getCode() == 404) {
			if (new JsonUtils().addNewObject(account)) {
				// Return the success response or make dozer mapper
				dozerMapper.map(account, accountDTOResult);
				accountDTOResult.setCode(100);
				accountDTOResult.setMessage("Account has been successfully created");
			} else {
				accountDTOResult.setCode(500);
				accountDTOResult.setMessage("Error, Unable to create account");
			}
		} else {
			accountDTOResult.setCode(300);
			accountDTOResult.setMessage("Error, Account already exists");
			accountDTOResult.setCustomerName("");
			accountDTOResult.setCurrency("");
			accountDTOResult.setAccountType("");
		}
		return accountDTOResult;
	}

	@Override
	public boolean updateAccount(AccountDTO account) {
		return false;
	}

}
