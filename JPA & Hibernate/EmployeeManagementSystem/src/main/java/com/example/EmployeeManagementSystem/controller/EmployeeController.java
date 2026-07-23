package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.projection.EmployeeSummaryProjection;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        if (employee.getDepartment() == null || employee.getDepartment().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/summary")
    public List<EmployeeSummaryProjection> getEmployeeSummaries() {
        return employeeRepository.findAllProjectedBy();
    }

    @GetMapping("/projection/basic/department/{departmentId}")
    public List<?> getBasicProjectionByDepartment(@PathVariable Long departmentId) {
        return employeeRepository.findProjectedByDepartmentId(departmentId);
    }

    @GetMapping("/projection/summary/department/{departmentId}")
    public List<?> getSummaryProjectionByDepartment(@PathVariable Long departmentId) {
        return employeeRepository.findSummaryProjectedByDepartmentId(departmentId);
    }

    @GetMapping("/projection/dto")
    public List<?> getEmployeeDepartmentDTOs() {
        return employeeRepository.findEmployeeDepartmentDTOs();
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createEmployeesBulk(@RequestBody List<Employee> employees) {

        for (Employee employee : employees) {
            if (employee.getDepartment() == null || employee.getDepartment().getId() == null) {
                return ResponseEntity.badRequest().body("Department id is required for all employees");
            }

            Department department = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            employee.setDepartment(department);
        }

        List<Employee> savedEmployees = employeeRepository.saveAll(employees);
        return ResponseEntity.ok(savedEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee
    ) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setEmail(updatedEmployee.getEmail());

                    if (updatedEmployee.getDepartment() != null &&
                            updatedEmployee.getDepartment().getId() != null) {

                        Department department = departmentRepository
                                .findById(updatedEmployee.getDepartment().getId())
                                .orElseThrow(() -> new RuntimeException("Department not found"));

                        employee.setDepartment(department);
                    }

                    return ResponseEntity.ok(employeeRepository.save(employee));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    // Exercise 5: Derived Query
    @GetMapping("/search/name")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // Exercise 5: Department ID Query
    @GetMapping("/department/{departmentId}")
    public List<Employee> getEmployeesByDepartment(@PathVariable Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    // Exercise 5: JPQL @Query
    @GetMapping("/search/department-name")
    public List<Employee> searchByDepartmentName(@RequestParam String departmentName) {
        return employeeRepository.findEmployeesByDepartmentName(departmentName);
    }

    // Exercise 6: Pagination and Sorting
    @GetMapping("/page")
    public Page<Employee> getEmployeesWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable);
    }
}