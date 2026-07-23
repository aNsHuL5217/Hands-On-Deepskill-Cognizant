package com.cognizant.customer_service.dto;

public class Account {

    private String accountNumber;
    private String type;
    private double balance;
    private String servedByPort;

    public Account() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getServedByPort() {
        return servedByPort;
    }

    public void setServedByPort(String servedByPort) {
        this.servedByPort = servedByPort;
    }
}