package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@JsonProperty("amount")
	private double amount;
}
