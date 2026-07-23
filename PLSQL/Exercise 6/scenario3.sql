select LoanID, InterestRate from Loans;

delimiter $$

create procedure UpdateLoanInterestRates(in rate_increase decimal(5,2))
begin
    declare done int default false;
    declare loan_id int;

    declare loan_cursor cursor for select LoanID from Loans;
    declare continue handler for not found set done = true;

    open loan_cursor;

    read_loop: loop
        fetch loan_cursor into loan_id;
        if done then
            leave read_loop;
        end if;

        update Loans set InterestRate = InterestRate + rate_increase where LoanID = loan_id;
    end loop;

    close loan_cursor;
end$$

delimiter ;

call UpdateLoanInterestRates(0.50);

select LoanID, InterestRate from Loans;