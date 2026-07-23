delimiter $$

create trigger CheckTransactionRules
before insert on Transactions
for each row
begin
    declare account_balance decimal(12,2);

    select Balance into account_balance from Accounts where AccountID = new.AccountID;

    if new.TransactionType = 'Withdrawal' and new.Amount > account_balance then
        signal sqlstate '45000' set message_text = 'Withdrawal Exceeds Balance';
    end if;

    if new.TransactionType = 'Deposit' and new.Amount <= 0 then
        signal sqlstate '45000' set message_text = 'Deposit Must Be Positive';
    end if;
end$$

delimiter ;

select * from Transactions;

insert into Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
values (4, 1, now(), 100.00, 'Withdrawal');

select * from Transactions;