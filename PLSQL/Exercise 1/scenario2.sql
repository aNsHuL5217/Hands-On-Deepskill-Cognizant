select CustomerID, Name, Balance, IsVIP from Customers;

delimiter $$

create procedure UpdateVIPStatus()
begin
    declare done int default false;
    declare customer_id int;
    declare customer_balance decimal(12,2);

    declare customer_cursor cursor for select CustomerID, Balance from Customers;
    declare continue handler for not found set done = true;

    open customer_cursor;

    read_loop: loop
        fetch customer_cursor into customer_id, customer_balance;
        if done then
            leave read_loop;
        end if;

        if customer_balance > 10000 then
            update Customers set IsVIP = true where CustomerID = customer_id;
        end if;
    end loop;

    close customer_cursor;
end$$

delimiter ;

call UpdateVIPStatus();

select CustomerID, Name, Balance, IsVIP from Customers;