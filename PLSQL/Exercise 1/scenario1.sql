select l.LoanID, c.Name, c.DOB, l.InterestRate from Loans l join Customers c on l.CustomerID = c.CustomerID;

delimiter $$

create procedure ApplySeniorLoanDiscount()
begin
    declare done int default false;
    declare customer_id int;
    declare date_of_birth date;
    declare age int;

    declare customer_cursor cursor for select CustomerID, DOB from Customers;
    declare continue handler for not found set done = true;

    open customer_cursor;

    read_loop: loop
        fetch customer_cursor into customer_id, date_of_birth;
        if done then
            leave read_loop;
        end if;

        set age = timestampdiff(year, date_of_birth, curdate());

        if age > 60 then
            update Loans
            set InterestRate = InterestRate - (InterestRate * 0.01)
            where CustomerID = customer_id;
        end if;
    end loop;

    close customer_cursor;
end$$

delimiter ;

call ApplySeniorLoanDiscount();

select l.LoanID, c.Name, c.DOB, l.InterestRate from Loans l join Customers c on l.CustomerID = c.CustomerID;