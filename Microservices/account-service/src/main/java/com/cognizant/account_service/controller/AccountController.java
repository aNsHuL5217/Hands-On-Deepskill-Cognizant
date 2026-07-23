package com.cognizant.account_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/accounts/{number}")
    public Map<String, Object> getAccount(@PathVariable String number) {

        Map<String, Object> response = new HashMap<>();

        response.put("accountNumber", number);
        response.put("type", "Savings");
        response.put("balance", 50000);
        response.put("servedByPort", port);

        return response;
    }
}