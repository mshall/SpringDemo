package com.demo.service;

import java.util.List;

import com.demo.dto.AccountDTO;

public interface AccountService {

	AccountDTO getAccountDetails(String id);

	AccountDTO addAccount(AccountDTO account);

	AccountDTO updateAccount(String accountId, AccountDTO account);

	List<AccountDTO> getAllAccounts();

}
