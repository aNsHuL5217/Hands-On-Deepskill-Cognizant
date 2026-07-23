select EmployeeID, Name, Department, Salary from Employees where Department = 'IT';

delimiter $$

create procedure UpdateEmployeeBonus(
    in department_name varchar(50),
    in bonus_percentage decimal(5,2)
)
begin
    update Employees
    set Salary = Salary + (Salary * bonus_percentage / 100)
    where Department = department_name;
end$$

delimiter ;

call UpdateEmployeeBonus('IT', 5.00);

select EmployeeID, Name, Department, Salary from Employees where Department = 'IT';