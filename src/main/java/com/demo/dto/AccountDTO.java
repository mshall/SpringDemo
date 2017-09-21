package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountDTO {
	@JsonProperty("code")
	private int code;
	@JsonProperty("message")
	private String message;
	// Required
	@JsonProperty("accountId")
	private String accountId;
	@JsonProperty("customerName")
	private String customerName;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("accountType")
	private String accountType;
}
