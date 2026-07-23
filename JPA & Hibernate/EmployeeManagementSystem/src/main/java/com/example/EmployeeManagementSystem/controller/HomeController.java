package com.example.EmployeeManagementSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("message", "Employee Management System API is running");
        response.put("employees", "/api/employees");
        response.put("departments", "/api/departments");
        response.put("h2Console", "/h2-console");
        return response;
    }

    @GetMapping("/error")
    public Map<String, String> handleError() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("message", "Requested resource was not found");
        response.put("status", "404");
        return response;
    }
}
