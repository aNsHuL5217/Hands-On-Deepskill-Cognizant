package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Department;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.service.DepartmentService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.service.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrmLearnApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private SkillService skillService;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldLoadEmployee() {
		Employee employee = employeeService.get(1);

		assertThat(employee).isNotNull();
		assertThat(employee.getName()).isNotBlank();
	}

	@Test
	void shouldLoadDepartment() {
		Department department = departmentService.get(1);

		assertThat(department).isNotNull();
		assertThat(department.getName()).isNotBlank();
	}

	@Test
	void shouldLoadSkill() {
		Skill skill = skillService.get(1);

		assertThat(skill).isNotNull();
		assertThat(skill.getName()).isNotBlank();
	}

	@Test
	void shouldLoadEmployeeDepartmentRelationship() {
		Employee employee = employeeService.get(1);

		assertThat(employee.getDepartment()).isNotNull();
	}

	@Test
	void shouldLoadEmployeeSkillsRelationship() {
		Employee employee = employeeService.get(1);

		assertThat(employee.getSkillList()).isNotEmpty();
	}
}