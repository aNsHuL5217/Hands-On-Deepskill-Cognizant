select LoanID, CustomerID, EndDate from Loans;

delimiter $$

create procedure SendLoanDueReminders()
begin
    declare done int default false;
    declare loan_id int;
    declare customer_id int;
    declare due_date date;
    declare customer_name varchar(100);

    declare loan_cursor cursor for
        select LoanID, CustomerID, EndDate from Loans
        where EndDate between curdate() and date_add(curdate(), interval 30 day);
    declare continue handler for not found set done = true;

    open loan_cursor;

    read_loop: loop
        fetch loan_cursor into loan_id, customer_id, due_date;
        if done then
            leave read_loop;
        end if;

        select Name into customer_name from Customers where CustomerID = customer_id;

        select concat(customer_name, ', Your Loan ', loan_id, ' Is Due On ', due_date) as Reminder;
    end loop;

    close loan_cursor;
end$$

delimiter ;

call SendLoanDueReminders();

select LoanID, CustomerID, EndDate from Loans;