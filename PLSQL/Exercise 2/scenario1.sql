select AccountID, Balance from Accounts where AccountID in (1, 2, 4);

delimiter $$

create procedure SafeTransferFunds(
    in from_account_id int,
    in to_account_id int,
    in transfer_amount decimal(12,2)
)
begin
    declare source_balance decimal(12,2);

    declare exit handler for sqlexception
    begin
        rollback;
        insert into AuditLog (LogMessage)
        values (concat('Error: Transfer From Account ', from_account_id, ' Failed'));
    end;

    start transaction;

    select Balance into source_balance from Accounts where AccountID = from_account_id;

    if source_balance < transfer_amount then
        signal sqlstate '45000' set message_text = 'Insufficient Funds';
    end if;

    update Accounts set Balance = Balance - transfer_amount where AccountID = from_account_id;
    update Accounts set Balance = Balance + transfer_amount where AccountID = to_account_id;

    commit;
end$$

delimiter ;

call SafeTransferFunds(2, 1, 100.00);
call SafeTransferFunds(4, 1, 999999.00);

select AccountID, Balance from Accounts where AccountID in (1, 2, 4);
select * from AuditLog;