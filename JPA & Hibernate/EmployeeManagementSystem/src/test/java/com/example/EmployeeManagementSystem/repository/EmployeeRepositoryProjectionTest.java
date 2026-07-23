package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.dto.EmployeeDepartmentDTO;
import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.projection.EmployeeBasicProjection;
import com.example.EmployeeManagementSystem.projection.EmployeeSummaryProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryProjectionTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void projectionsShouldReturnSelectedFields() {
        Department department = new Department();
        department.setName("IT");
        department = departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName("Yash");
        employee.setEmail("yash101@gmail.com");
        employee.setDepartment(department);
        employeeRepository.save(employee);

        List<EmployeeBasicProjection> basic = employeeRepository.findProjectedByDepartmentId(department.getId());
        assertEquals(1, basic.size());
        assertEquals("Yash", basic.get(0).getName());

        List<EmployeeSummaryProjection> summary = employeeRepository.findSummaryProjectedByDepartmentId(department.getId());
        assertEquals(1, summary.size());
        assertEquals("Yash works in IT", summary.get(0).getEmployeeSummary());

        List<EmployeeDepartmentDTO> dtos = employeeRepository.findEmployeeDepartmentDTOs();
        assertFalse(dtos.isEmpty());
        assertEquals("IT", dtos.get(0).getDepartmentName());
    }
}
