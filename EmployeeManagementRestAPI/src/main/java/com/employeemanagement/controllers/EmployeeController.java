package com.employeemanagement.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.repository.RolesRepository;
import com.employeemanagement.service.EmployeeService;
import com.employeemanagement.entities.Employee;
import com.employeemanagement.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    
    @GetMapping("/employees/search/{gl}")
    public List<Employee> getAllEmployees(@PathVariable(value = "gl") String firstName) {
        return employeeService.getAllEmployees(firstName);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
        throws ResourceNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId)
          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
    
    @GetMapping("/employees/sort")
    public ResponseEntity<List<Employee>> getEmployeeByOrder(@RequestParam(name="order")String order )
        throws ResourceNotFoundException {
        List<Employee> employee = employeeService.getAllEmployees();
        if(order.equals("asc")) {
        Collections.sort(employee,(e1,e2)->e1.getFirstName().compareTo(e2.getFirstName()));
        }else {
        	Collections.sort(employee,(e1,e2)->e2.getFirstName().compareTo(e1.getFirstName()));	
        }
        
        return ResponseEntity.ok().body(employee);
    }
    @PostMapping("/addemployees")
    public Employee createEmployee( @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
         @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee =employeeService.getEmployeeById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long employeeId)
         throws ResourceNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeService.deleteEmployee(employee);
        //Map<String, Boolean> response = new HashMap<>();
        //response.put("deleted", Boolean.TRUE);
        return "Deleted employee id- "+employeeId;
    }
    
}
