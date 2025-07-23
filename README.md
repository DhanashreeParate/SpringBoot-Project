# SpringBoot-Project
# Employee Management System
This is a Spring Boot-based REST API for managing employees. It provides endpoints to perform CRUD operations along with exception handling.
---
## 🚀 Technologies Used
- Java 17  
- Spring Boot 3.x  
- Spring Web  
- Spring Data JPA  
- H2 / MySQL (configurable)  
- Swagger/OpenAPI (via springdoc)  
- JUnit 5 + Mockito  
- Maven
  
## 📁 Project Structure
src/
└── main/
├── java/
│ └── com.enpointe.employeeManagementSystem
│ ├── api/ # Interface defining REST endpoints (with OpenAPI annotations)
│ ├── api.impl/ # Controller implementation
│ ├── model/ # Entity and DTO classes
│ ├── repository/ # Spring Data JPA repository interfaces
│ ├── service/ # Service interface and implementation
│ └── exception/ # Global exception handling
└── resources/
└── application.properties # Application configuration

### Prerequisites
- Java 17+  
- Maven 3.8+  
- IDE (IntelliJ, Eclipse, VSCode, etc.)

### 1. Clone the repository
```bash
git clone 

### 2. Build the project
mvn clean install

### The server will start on:
http://localhost:8080

### Access Swagger UI
http://localhost:8080/swagger-ui/index.html

### API Endpoints
Method	  Endpoint	            Description
POST	    /api/employee	        Add new employee
GET	      /api/employees	      Get all employees
GET	      /api/employee/{id}	  Get employee by ID
PUT	      /api/employee/{id}	  Update employee
DELETE	  /api/employee/{id}	  Delete employee

### Design Decisions
1. Interface-Driven Controller: The EmployeeApi interface declares the API contract. This improves modularity and enables reuse (e.g., documentation generation).
2. Exception Handling: Centralized using @RestControllerAdvice, which wraps all unhandled exceptions into consistent JSON error responses.
3. Validation: Done using jakarta.validation.constraints annotations (like @NotNull, @Email) and handled globally.
4. Separation of Concerns: Clear layers: Controller → Service → Repository to follow clean architecture principles.

---
### Running Tests
test cases written in Junit for controller class
<img width="1815" height="458" alt="image" src="https://github.com/user-attachments/assets/0d0be56a-1e99-4272-8b92-1fc1c72e7872" />
