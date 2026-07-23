package com.example.EmployeeManagementSystem.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeSummaryProjection {

    Long getId();

    String getName();

    String getEmail();

    @Value("#{target.name + ' works in ' + target.department.name}")
    String getEmployeeSummary();
}
