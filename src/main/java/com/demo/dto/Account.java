package com.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	// Required
	@JsonProperty("accountId")
	private String accountId;
	@JsonProperty("customerName")
	private String customerName;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("accountType")
	private String accountType;

	// Optional
	private double amount;

	private Account(AccountBuilder builder) {
		this.accountId = builder.accountId;
		this.customerName = builder.customerName;
		this.currency = builder.currency.toString();
		this.accountType = builder.accountType.toString();
		this.amount = builder.amount;
	}

	public Account() {
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public static class AccountBuilder {
		// Required
		private String accountId;
		private String customerName;
		private Currency currency;
		private AccountType accountType;
		private double amount;

		public enum Currency {
			USD("USD"), EURO("Euro");
			private final String currency;

			private Currency(String currency) {
				this.currency = currency;
			}

			@Override
			public String toString() {
				return currency;
			}
		}

		public enum AccountType {
			PERSONAL("Personal"), BUSINESS("Business");

			private final String accountType;

			private AccountType(String accountType) {
				this.accountType = accountType;
			}

			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return accountType;
			}
		}

		public Account build() {
			Account account = new Account(this);
			return account;
		}

		public String getAccountId() {
			return accountId;
		}

		public AccountBuilder setAccountId(String accountId) {
			this.accountId = accountId;
			return this;
		}

		public String getCustomerName() {
			return customerName;
		}

		public AccountBuilder setCustomerName(String customerName) {
			this.customerName = customerName;
			return this;
		}

		public Currency getCurrency() {
			return currency;
		}

		public AccountBuilder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public AccountType getAccountType() {
			return accountType;
		}

		public AccountBuilder setAccountType(AccountType accountType) {
			this.accountType = accountType;
			return this;
		}

		public double getAmount() {
			return amount;
		}

		public AccountBuilder setAmount(double amount) {
			this.amount = amount;
			return this;
		}

	}

}
