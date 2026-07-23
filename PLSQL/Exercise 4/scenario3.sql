delimiter $$

create function HasSufficientBalance(account_id int, amount decimal(12,2))
returns boolean
deterministic
begin
    declare account_balance decimal(12,2);

    select Balance into account_balance from Accounts where AccountID = account_id;

    return account_balance >= amount;
end$$

delimiter ;

select AccountID, Balance, HasSufficientBalance(AccountID, 500) as HasFiveHundred from Accounts;