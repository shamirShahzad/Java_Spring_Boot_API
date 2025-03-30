package com.exam.employee;

public class EmployeeFilterRequest {

    public String getName() {
        return name;
    }

    public void setName(String fullname) {
        this.name = fullname;
    }

    public Double getFromSalary() {
        return fromSalary;
    }

    public void setFromSalary(Double fromSalary) {
        this.fromSalary = fromSalary;
    }

    public Double getToSalary() {
        return toSalary;
    }

    public void setToSalary(Double toSalary) {
        this.toSalary = toSalary;
    }

private String name;
private Double fromSalary;
private Double toSalary;
}
