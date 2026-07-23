create table Customers (
    CustomerID int primary key,
    Name varchar(100),
    DOB date,
    Balance decimal(12,2),
    LastModified datetime,
    IsVIP boolean default false
);

create table Accounts (
    AccountID int primary key,
    CustomerID int,
    AccountType varchar(20),
    Balance decimal(12,2),
    LastModified datetime,
    foreign key (CustomerID) references Customers(CustomerID)
);

create table Transactions (
    TransactionID int primary key,
    AccountID int,
    TransactionDate datetime,
    Amount decimal(12,2),
    TransactionType varchar(10),
    foreign key (AccountID) references Accounts(AccountID)
);

create table Loans (
    LoanID int primary key,
    CustomerID int,
    LoanAmount decimal(12,2),
    InterestRate decimal(5,2),
    StartDate date,
    EndDate date,
    foreign key (CustomerID) references Customers(CustomerID)
);

create table Employees (
    EmployeeID int primary key,
    Name varchar(100),
    Position varchar(50),
    Salary decimal(12,2),
    Department varchar(50),
    HireDate date
);

create table AuditLog (
    LogID int auto_increment primary key,
    LogMessage varchar(255),
    LogDate datetime default current_timestamp
);


insert into Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP) values
(1, 'Customer 1', '1960-05-15', 1000.00, now(), false),
(2, 'Customer 2', '1990-07-20', 15000.00, now(), false),
(3, 'Customer 3', '1955-02-10', 8000.00, now(), false),
(4, 'Customer 4', '2000-11-30', 500.00, now(), false);

insert into Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) values
(1, 1, 'Savings', 1000.00, now()),
(2, 2, 'Checking', 15000.00, now()),
(3, 3, 'Savings', 8000.00, now()),
(4, 4, 'Checking', 500.00, now());

insert into Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) values
(1, 1, now(), 200.00, 'Deposit'),
(2, 2, now(), 300.00, 'Withdrawal');

insert into Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) values
(1, 1, 5000.00, 5.00, curdate(), date_add(curdate(), interval 60 month)),
(2, 3, 10000.00, 6.50, curdate(), date_add(curdate(), interval 15 day)),
(3, 2, 20000.00, 4.75, curdate(), date_add(curdate(), interval 20 day));

insert into Employees (EmployeeID, Name, Position, Salary, Department, HireDate) values
(1, 'Manager 1', 'Manager', 70000.00, 'HR', '2015-06-15'),
(2, 'Developer 1', 'Developer', 60000.00, 'IT', '2017-03-20');

