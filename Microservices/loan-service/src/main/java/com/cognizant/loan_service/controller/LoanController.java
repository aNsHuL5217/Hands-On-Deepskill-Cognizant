package com.cognizant.loan_service.controller;

import com.cognizant.loan_service.model.Loan;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    @GetMapping("/loans/{number}")
    public Loan getLoan(@PathVariable String number) {

        return new Loan(
                number,
                "Car Loan",
                400000,
                3258,
                18
        );
    }
}