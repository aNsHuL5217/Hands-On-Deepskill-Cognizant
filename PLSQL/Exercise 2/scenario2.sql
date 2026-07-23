select EmployeeID, Name, Salary from Employees;

delimiter $$

create procedure UpdateSalary(
    in employee_id int,
    in increase_percentage decimal(5,2)
)
begin
    declare employee_count int;

    select count(*) into employee_count from Employees where EmployeeID = employee_id;

    if employee_count = 0 then
        insert into AuditLog (LogMessage)
        values (concat('Error: Employee Id ', employee_id, ' Does Not Exist'));
    else
        update Employees
        set Salary = Salary + (Salary * increase_percentage / 100)
        where EmployeeID = employee_id;
    end if;
end$$

delimiter ;

call UpdateSalary(1, 10.00);
call UpdateSalary(999, 10.00);

select EmployeeID, Name, Salary from Employees;
select * from AuditLog;