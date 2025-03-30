The program will run by going to the EmployeeApplication.java file and right clicking and running it after which you can use Postman or insomnia rest to run the apis



The apis are create Employee using localhost:8080/employees/add and including all the necessary fields in the body this include firstname,lastname,dateofjoining,salary,dateofbirth and department if all the fields are not provided the api will not create a new Employee

Then we have the get all employees which can be tested with localhost:8080/employees


Then the get employee by id localhost:8080/employees/{id} note:{id} can be any number 1-infinity this will return the correct employee given that they exist in the file


We also have getEmployee by filter which can be tested with localhost:8080/employees?name=name&fromSalary=salary&toSalary=salary, this can be used with all the queries or a single one


Lastly the file with data is stored in src/main/resources/employees.json


Thank you :) 
