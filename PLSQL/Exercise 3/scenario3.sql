select AccountID, Balance from Accounts where AccountID in (1, 2);

delimiter $$

create procedure TransferFunds(
    in from_account_id int,
    in to_account_id int,
    in transfer_amount decimal(12,2)
)
begin
    declare source_balance decimal(12,2);

    select Balance into source_balance from Accounts where AccountID = from_account_id;

    if source_balance >= transfer_amount then
        update Accounts set Balance = Balance - transfer_amount where AccountID = from_account_id;
        update Accounts set Balance = Balance + transfer_amount where AccountID = to_account_id;
    end if;
end$$

delimiter ;

call TransferFunds(2, 1, 500.00);

select AccountID, Balance from Accounts where AccountID in (1, 2);