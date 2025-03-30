
package com.exam.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
     private final EmployeeServiceStub employeeServiceStub;

    public EmployeeController(EmployeeServiceStub employeeServiceStub) {
        this.employeeServiceStub = employeeServiceStub;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        
        Employee employee = employeeServiceStub.getEmployeeById(id);
        if(employee == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
//        return employeeServiceStub.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> addEmployee(@RequestBody Employee employee){
        int newEmployeeId = employeeServiceStub.saveEmployee(employee);
        if(newEmployeeId == -1){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("id", newEmployeeId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<Employee>> getManyEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double fromSalary,
            @RequestParam(required = false) Double toSalary)
    {
        
        List<Employee> employees; 
        
        if (name != null || fromSalary != null || toSalary != null) {
            EmployeeFilterRequest filterRequest = new EmployeeFilterRequest();
            if(name!=null){
                filterRequest.setName(name);
            }
            if(fromSalary!=null){
                filterRequest.setFromSalary(fromSalary);
            }
            
            if(toSalary!=null){
                filterRequest.setToSalary(toSalary);
            }
            
            employees = employeeServiceStub.getFilteredEmployee(filterRequest);
           
        }else{
            employees = employeeServiceStub.readAllEmployees(); 
        }
        if(employees.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        
        return ResponseEntity.ok(employees);
    }
    
}
