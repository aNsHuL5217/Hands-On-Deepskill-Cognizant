package com.cognizant.customer_service.controller;

import com.cognizant.customer_service.dto.Account;
import com.cognizant.customer_service.dto.CustomerResponse;
import com.cognizant.customer_service.dto.Loan;
import com.cognizant.customer_service.feign.AccountClient;
import com.cognizant.customer_service.feign.LoanClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final AccountClient accountClient;
    private final LoanClient loanClient;

    public CustomerController(AccountClient accountClient,
                              LoanClient loanClient) {
        this.accountClient = accountClient;
        this.loanClient = loanClient;
    }

    @GetMapping("/customer/{customerId}")
    public CustomerResponse getCustomer(
            @PathVariable String customerId) {

        // Call Account Service via Feign
        Account account = accountClient.getAccount("1001");

        // Call Loan Service via Feign
        Loan loan = loanClient.getLoan("L001");

        // Combine response
        CustomerResponse response = new CustomerResponse();

        response.setCustomerId(customerId);
        response.setAccount(account);
        response.setLoan(loan);

        return response;
    }
}