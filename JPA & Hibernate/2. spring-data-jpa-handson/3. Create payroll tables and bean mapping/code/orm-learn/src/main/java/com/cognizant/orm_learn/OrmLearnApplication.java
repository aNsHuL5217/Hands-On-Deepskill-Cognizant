package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Department;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.service.DepartmentService;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);

		testGetEmployee();
		testGetDepartment();
		testGetSkill();
	}

	private static void testGetEmployee() {
		LOGGER.info("Start Employee");

		Employee employee = employeeService.getEmployee(1);

		LOGGER.debug("Employee: {}", employee);
		LOGGER.debug("Department: {}", employee.getDepartment());
		LOGGER.debug("Skills: {}", employee.getSkillList());

		LOGGER.info("End Employee");
	}

	private static void testGetDepartment() {
		LOGGER.info("Start Department");

		Department department = departmentService.getDepartment(1);

		LOGGER.debug("Department: {}", department);

		LOGGER.info("End Department");
	}

	private static void testGetSkill() {
		LOGGER.info("Start Skill");

		Skill skill = skillService.getSkill(1);

		LOGGER.debug("Skill: {}", skill);

		LOGGER.info("End Skill");
	}
}