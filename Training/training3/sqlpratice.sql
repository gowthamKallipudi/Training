use classicmodels;
select * from customers;
select * from employees;
select * from customers join employees on customers.salesRepEmployeeNumber=employees.employeeNumber;
select * from customers inner join employees on customers.salesRepEmployeeNumber=employees.employeeNumber;
select * from customers left join employees on customers.salesRepEmployeeNumber=employees.employeeNumber;
select * from customers right join employees on customers.salesRepEmployeeNumber=employees.employeeNumber;
-- select * from customers  union all select * from employees;
-- (results error because both tables don't have same columns)
select * from customers where customers.customerNumber < 234 
union
select * from customers where customers.customerNumber > 233;
select * from customers where customers.customerNumber < 300 
union
select * from customers where customers.customerNumber > 200;
select * from customers where customers.customerNumber < 300 
union all
select * from customers where customers.customerNumber > 200;
-- select * from customers where customers.customerNumber < 300 
-- INTERSECT 
-- select * from customers where customers.customerNumber > 200;
-- intersect can't be acheived directly so we can use 

select distinct customerNumber from 
(select customerNumber from customers where customers.customerNumber < 300)
inner join 
(select customerNumber from customers where customers.customerNumber > 200)
using(customerNumber);
-- alternate 1 for intersect

select distinct customerNumber from 
(select customerNumber from customers where customers.customerNumber < 300)
where customerNumber in 
(select customerNumber from customers where customers.customerNumber > 200);
 select customerNumber from customers where customers.customerNumber > 200;
 select customerNumber from customers where customers.customerNumber < 300;
 -- alternate 2 for intersect