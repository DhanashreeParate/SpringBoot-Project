package com.enpointe.employeeManagementSystem.api.impl;

import com.enpointe.employeeManagementSystem.api.EmployeeApi;
import com.enpointe.employeeManagementSystem.model.Employee;
import com.enpointe.employeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class EmployeeApiController implements EmployeeApi {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<Employee> addEmployee(Employee employee) {
        Employee createEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(createEmployee);
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Override
    public ResponseEntity<Employee> updateEmployee(Long id, Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(id, employee));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
