package com.employeemanagement.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.employeemanagement.entities.Employee;
import com.employeemanagement.exception.ResourceNotFoundException;
import com.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public List<Employee> getAllEmployees(String firstName) {
        return employeeRepository.findAll().stream().filter(e1->e1.getFirstName().equals(firstName)).collect(Collectors.toList());
    }
    
    public Optional<Employee> getEmployeeById(Long employeeId)
        throws ResourceNotFoundException {
    	Optional<Employee> employee = employeeRepository.findById(employeeId);   
    	        return employee;
    }
   
    public Employee createEmployee( @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }


    public void deleteEmployee(Employee employee)
         throws ResourceNotFoundException {
    	
        employeeRepository.delete(employee);
        //Map<String, Boolean> response = new HashMap<>();
        //response.put("deleted", Boolean.TRUE);
    }

	
}
