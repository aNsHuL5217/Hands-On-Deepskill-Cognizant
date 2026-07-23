delimiter $$

create procedure GenerateMonthlyStatements()
begin
    declare done int default false;
    declare customer_name varchar(100);
    declare account_id int;
    declare transaction_date datetime;
    declare amount decimal(12,2);
    declare transaction_type varchar(10);

    declare transaction_cursor cursor for
        select c.Name, t.AccountID, t.TransactionDate, t.Amount, t.TransactionType
        from Transactions t
        join Accounts a on t.AccountID = a.AccountID
        join Customers c on a.CustomerID = c.CustomerID
        where month(t.TransactionDate) = month(curdate())
          and year(t.TransactionDate) = year(curdate());
    declare continue handler for not found set done = true;

    open transaction_cursor;

    read_loop: loop
        fetch transaction_cursor into customer_name, account_id, transaction_date, amount, transaction_type;
        if done then
            leave read_loop;
        end if;

        select concat('Statement - ', customer_name, ', Account ', account_id, ', ', transaction_type, ' Of ', amount, ' On ', transaction_date) as Statement;
    end loop;

    close transaction_cursor;
end$$

delimiter ;

call GenerateMonthlyStatements();