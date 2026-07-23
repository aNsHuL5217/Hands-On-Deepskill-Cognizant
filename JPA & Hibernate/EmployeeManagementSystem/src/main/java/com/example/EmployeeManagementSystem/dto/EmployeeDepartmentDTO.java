package com.example.EmployeeManagementSystem.dto;

public class EmployeeDepartmentDTO {

    private Long id;
    private String name;
    private String email;
    private String departmentName;

    public EmployeeDepartmentDTO(Long id, String name, String email, String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
