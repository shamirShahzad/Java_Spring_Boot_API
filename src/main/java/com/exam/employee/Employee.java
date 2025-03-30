
package com.exam.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class Employee {

    public Employee(int id, String firstname, String lastname, String department, LocalDate dateofbirth, LocalDate joiningdate, double salary) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.dateofbirth = dateofbirth;
        this.joiningdate = joiningdate;
        this.salary = salary;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public LocalDate getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(LocalDate joiningdate) {
        this.joiningdate = joiningdate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", department=" + department + ", dateofbirth=" + dateofbirth + ", joiningdate=" + joiningdate + ", salary=" + salary + '}';
    }
    
    private int id;
    private String firstname;
    private String lastname;
    private String department;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dateofbirth;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate joiningdate;
    private double salary;
    
}
