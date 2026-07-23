delimiter $$

create function CalculateMonthlyInstallment(
    loan_amount decimal(12,2),
    interest_rate decimal(5,2),
    duration_years int
)
returns decimal(12,2)
deterministic
begin
    declare monthly_rate decimal(10,6);
    declare number_of_payments int;

    set monthly_rate = (interest_rate / 100) / 12;
    set number_of_payments = duration_years * 12;

    return round((loan_amount * monthly_rate) / (1 - power(1 + monthly_rate, -number_of_payments)), 2);
end$$

delimiter ;

select LoanID, LoanAmount, InterestRate,
       CalculateMonthlyInstallment(LoanAmount, InterestRate, 5) as MonthlyInstallment
from Loans;