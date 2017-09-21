package com.demo.controller;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dto.Account;
import com.demo.dto.AccountDTO;
import com.demo.service.AccountService;
import com.demo.utils.JsonUtils;

@Path("/accounts/account")
public class AccountRestService {

	protected Logger logger = Logger.getLogger(AccountRestService.class.getName());

	@Autowired
	private AccountService accountService;

	// --------------------------------------------------------------------------------
	// This method is to get customer service balances
	// --------------------------------------------------------------------------------
	@GET
	@Path("/{accountId}")
	@Produces("application/json")
	public Response getAccount(@PathParam("accountId") String accountId) {
		Response response = null;
		AccountDTO account = accountService.getAccountDetails(accountId);
		// if (account == null) {
		// response = Response.status(200).entity(new ErrorResponse(404,
		// "Account not found")).build();
		// } else {
		// response = Response.status(200).entity(account).build();
		// }
		response = Response.status(200).entity(account).build();
		return response;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createAccount(AccountDTO account) {
		Response response = null;
		AccountDTO accountDTO = accountService.addAccount(account);

		response = Response.status(200).entity(accountDTO).build();
		return response;
	}

}
