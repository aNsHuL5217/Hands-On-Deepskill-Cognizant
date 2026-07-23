select AccountID, AccountType, Balance from Accounts where AccountType = 'Savings';

delimiter $$

create procedure ProcessMonthlyInterest()
begin
    update Accounts
    set Balance = Balance + (Balance * 0.01)
    where AccountType = 'Savings';
end$$

delimiter ;

call ProcessMonthlyInterest();

select AccountID, AccountType, Balance from Accounts where AccountType = 'Savings';