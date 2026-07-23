select AccountID, Balance from Accounts;

delimiter $$

create procedure ApplyAnnualFee(in fee_amount decimal(12,2))
begin
    declare done int default false;
    declare account_id int;

    declare account_cursor cursor for select AccountID from Accounts;
    declare continue handler for not found set done = true;

    open account_cursor;

    read_loop: loop
        fetch account_cursor into account_id;
        if done then
            leave read_loop;
        end if;

        update Accounts set Balance = Balance - fee_amount where AccountID = account_id;
    end loop;

    close account_cursor;
end$$

delimiter ;

call ApplyAnnualFee(25.00);

select AccountID, Balance from Accounts;