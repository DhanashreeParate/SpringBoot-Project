package com.enpointe.employeeManagementSystem.api;

import com.enpointe.employeeManagementSystem.model.Employee;
import com.enpointe.employeeManagementSystem.model.SyncResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Tag(name = "Employee API", description = "CRUD operations for managing employees")
@RequestMapping("/api")
public interface EmployeeApi {

    @Operation(
            summary = "Add a new employee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SyncResponse.class)))
            }
    )
    @PostMapping("/employee")
    ResponseEntity<Employee> addEmployee(@RequestBody Employee employee);

    @Operation(
            summary = "Get All employees",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of Employees",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SyncResponse.class)))
            }
    )
    @GetMapping("/employees")
    ResponseEntity<List<Employee>> getAllEmployees();

    @Operation(
            summary = "Get employee by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SyncResponse.class)))
            }
    )
    @GetMapping("/employee/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable Long id);

    @Operation(
            summary = "Update employee by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee Updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SyncResponse.class)))
            }
    )
    @PutMapping("/employee/{id}")
    ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee);

    @Operation(
            summary = "Delete employee by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee Deleted",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SyncResponse.class)))
            }
    )
    @DeleteMapping("/employee/{id}")
    ResponseEntity<Void> deleteEmployee(@PathVariable Long id);

}
