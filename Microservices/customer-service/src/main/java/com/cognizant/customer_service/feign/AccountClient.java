package com.cognizant.customer_service.feign;

import com.cognizant.customer_service.dto.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountClient {

    @GetMapping("/accounts/{number}")
    Account getAccount(@PathVariable("number") String number);
}