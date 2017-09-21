package com.demo.service;

import com.demo.dto.Account;
import com.demo.dto.AccountDTO;

public interface AccountService {

	AccountDTO getAccountDetails(String id);

	AccountDTO addAccount(AccountDTO account);

	boolean updateAccount(AccountDTO account);
}
