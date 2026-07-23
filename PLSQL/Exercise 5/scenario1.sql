delimiter $$

create trigger UpdateCustomerLastModified
before update on Customers
for each row
begin
    set new.LastModified = now();
end$$

delimiter ;


select CustomerID, Balance, LastModified from Customers where CustomerID = 1;

update Customers set Balance = Balance + 50 where CustomerID = 1;

select CustomerID, Balance, LastModified from Customers where CustomerID = 1;