delimiter $$

create trigger LogTransaction
after insert on Transactions
for each row
begin
    insert into AuditLog (LogMessage)
    values (concat(new.TransactionType, ' Of ', new.Amount, ' On Account ', new.AccountID));
end$$

delimiter ;

select * from AuditLog;

insert into Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
values (3, 3, now(), 750.00, 'Deposit');

select * from AuditLog;