package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.dto.EmployeeDepartmentDTO;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.projection.EmployeeBasicProjection;
import com.example.EmployeeManagementSystem.projection.EmployeeSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<EmployeeSummaryProjection> findAllProjectedBy();

    List<Employee> findByDepartmentId(Long departmentId);

    Employee findByEmail(String email);

    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Employee findEmployeeByEmailNamed(@Param("email") String email);

    List<EmployeeBasicProjection> findProjectedByDepartmentId(Long departmentId);

    List<EmployeeSummaryProjection> findSummaryProjectedByDepartmentId(Long departmentId);

    @Query("SELECT new com.example.EmployeeManagementSystem.dto.EmployeeDepartmentDTO(e.id, e.name, e.email, e.department.name) FROM Employee e")
    List<EmployeeDepartmentDTO> findEmployeeDepartmentDTOs();
}