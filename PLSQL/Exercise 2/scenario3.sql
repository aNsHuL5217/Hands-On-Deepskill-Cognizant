select CustomerID, Name from Customers;

delimiter $$

create procedure AddNewCustomer(
    in customer_id int,
    in customer_name varchar(100),
    in date_of_birth date,
    in balance decimal(12,2)
)
begin
    declare existing_count int;

    select count(*) into existing_count from Customers where CustomerID = customer_id;

    if existing_count > 0 then
        insert into AuditLog (LogMessage)
        values (concat('Error: Customer Id ', customer_id, ' Already Exists'));
    else
        insert into Customers (CustomerID, Name, DOB, Balance, LastModified)
        values (customer_id, customer_name, date_of_birth, balance, now());
    end if;
end$$

delimiter ;

call AddNewCustomer(5, 'Customer 5', '1995-04-12', 2000.00);
call AddNewCustomer(1, 'New Customer 1', '1985-05-15', 1000.00);

select CustomerID, Name from Customers;
select * from AuditLog;