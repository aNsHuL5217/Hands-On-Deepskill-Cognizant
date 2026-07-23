package com.cognizant.customer_service.feign;

import com.cognizant.customer_service.dto.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LOAN-SERVICE")
public interface LoanClient {

    @GetMapping("/loans/{number}")
    Loan getLoan(@PathVariable("number") String number);
}