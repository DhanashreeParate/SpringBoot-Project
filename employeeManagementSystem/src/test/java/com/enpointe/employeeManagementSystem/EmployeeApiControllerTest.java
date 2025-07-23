package com.enpointe.employeeManagementSystem;

import com.enpointe.employeeManagementSystem.api.impl.EmployeeApiController;
import com.enpointe.employeeManagementSystem.model.Employee;
import com.enpointe.employeeManagementSystem.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeApiController.class)
class EmployeeApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void setup() {
        employee = Employee.builder()
                .id(1L)
                .name("John Doe")
                .department("IT")
                .salary(50000.0)
                .build();
    }

    @Test
    void testAddEmployee() throws Exception {
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employee") // adjust the URL if needed
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        List<Employee> list = Arrays.asList(employee);
        when(employeeService.getAllEmployees()).thenReturn(list);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.department").value("IT"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        when(employeeService.updateEmployeeById(eq(1L), any(Employee.class))).thenReturn(employee);

        mockMvc.perform(put("/api/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/api/employee/1"))
                .andExpect(status().isNoContent());
    }
}
