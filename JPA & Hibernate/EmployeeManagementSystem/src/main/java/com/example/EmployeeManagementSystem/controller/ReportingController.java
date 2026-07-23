package com.example.EmployeeManagementSystem.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingController {

    private final JdbcTemplate reportingJdbcTemplate;

    public ReportingController(
            @Qualifier("reportingJdbcTemplate") JdbcTemplate reportingJdbcTemplate
    ) {
        this.reportingJdbcTemplate = reportingJdbcTemplate;
    }

    @GetMapping("/api/reporting/test")
    public String testReportingDataSource() {
        reportingJdbcTemplate.execute("CREATE TABLE IF NOT EXISTS report_test (id INT PRIMARY KEY, message VARCHAR(255))");
        reportingJdbcTemplate.update("MERGE INTO report_test KEY(id) VALUES (1, 'Reporting datasource working')");
        String message = reportingJdbcTemplate.queryForObject(
                "SELECT message FROM report_test WHERE id = 1",
                String.class
        );

        return message;
    }
}
