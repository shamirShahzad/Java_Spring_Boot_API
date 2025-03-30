package com.exam.employee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceStub {
    
    public static final String FILE_PATH = "src/main/resources/employees.json";
    private final ObjectMapper objectMapper;
    
    
    public EmployeeServiceStub(){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }
    
    public List<Employee> readAllEmployees(){
        File file = new File(FILE_PATH);
        if(!file.exists()){
            return new ArrayList<>();
        }
        
        try{
            return objectMapper.readValue(file, new TypeReference<List<Employee>>(){});
        } catch(IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public Employee getEmployeeById(int id){
     List<Employee> employees = readAllEmployees();
     
        Optional<Employee> employee = employees.stream().filter(e -> e.getId() == id ).findFirst();
        
        return employee.orElse(null);
    }

    public Employee saveEmployee(Employee newEmployee){
    
        File file = new File(FILE_PATH);
        List<Employee> employees = new ArrayList<>();
        if(file.exists()){
            employees = readAllEmployees();
        }
        if(newEmployee.getFirstname() == null || 
                newEmployee.getLastname() == null ||
                newEmployee.getSalary() == 0 ||
                newEmployee.getJoiningdate() == null ||
                newEmployee.getDateofbirth() == null ||
                newEmployee.getDepartment() == null){
            return null;
        }
        newEmployee.setId(employees.size());        
        employees.add(newEmployee);
        
        try{
            objectMapper.writeValue(file, employees);
            return newEmployee;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
        
    }
    
    public List<Employee> getFilteredEmployee(EmployeeFilterRequest filterRequest){
    
        List<Employee> employees = readAllEmployees();
        if(filterRequest.getName() !=null && !filterRequest.getName().isEmpty()){
        String fullname = filterRequest.getName().toLowerCase();
        
        employees = employees.stream()
                .filter(e -> (e.getFirstname().toLowerCase()+e.getLastname().toLowerCase()).contains(fullname)).collect(Collectors.toList());
        } 
        
        if(filterRequest.getFromSalary()!= null && filterRequest.getToSalary() != null){
            employees = employees.stream().filter(e -> e.getSalary() >= filterRequest.getFromSalary() && e.getSalary() <=filterRequest.getToSalary()).collect(Collectors.toList());
        }
        
        if (filterRequest.getFromSalary() != null) {
        employees = employees.stream()
                .filter(e -> e.getSalary() >= filterRequest.getFromSalary())
                .collect(Collectors.toList());
    }

    if (filterRequest.getToSalary() != null) {
        employees = employees.stream()
                .filter(e -> e.getSalary() <= filterRequest.getToSalary())
                .collect(Collectors.toList());
    }
    
    return employees;
    }
    
}
