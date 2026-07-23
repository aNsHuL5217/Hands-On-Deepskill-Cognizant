delimiter $$

create function CalculateAge(date_of_birth date)
returns int
deterministic
begin
    return timestampdiff(year, date_of_birth, curdate());
end$$

delimiter ;

select CustomerID, Name, DOB, CalculateAge(DOB) as Age from Customers;