package com.enpointe.employeeManagementSystem.service;

import com.enpointe.employeeManagementSystem.model.Employee;
import com.enpointe.employeeManagementSystem.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(); //batch operation
    }

    public Employee getEmployeeById(Long id) {
        // check if given employee id is present in db or else throw EntityNotFoundException.
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found in db."));
    }

    public Employee updateEmployeeById(Long id, Employee updateEmployee) {
        // check if given employee id is present in db or else throw EntityNotFoundException.
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        existingEmployee.setName(updateEmployee.getName());
        existingEmployee.setDepartment(updateEmployee.getDepartment());
        existingEmployee.setSalary(updateEmployee.getSalary());

        return employeeRepository.save(existingEmployee); // Save updated entity
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}
