# Spring Boot

Spring Boot is an extension of the Spring framework that simplifies the development of Spring applications. It provides a convention-over-configuration approach and allows developers to create standalone, production-grade Spring-based applications with minimal configuration.

## Key Features

- **Dependency Injection**: Simplified dependency management with the Spring Boot starter dependencies.
- **Embedded Servers**: Embedded Tomcat, Jetty, or Undertow servers, eliminating the need for separate server deployment.
- **Auto-Configuration**: Automatically configures Spring and third-party libraries based on the project dependencies.
- **Spring Boot CLI**: Command-line tool for quickly creating Spring applications.
- **Spring Initializr**: Web-based tool for generating Spring Boot projects.

## Dependency Injection

Spring Boot makes dependency injection straightforward by leveraging Spring's IoC container. Spring Boot's starter dependencies simplify the inclusion of required libraries.

### Example

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService();
    }
}
```

### Using `@Autowired`

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Use employeeService in your methods
}
```

## Web App Using Spring Boot

Spring Boot simplifies web application development with embedded servers and auto-configuration.

### Example

#### Application Class

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

#### Controller

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, Spring Boot!";
    }
}
```

#### `application.properties`

```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
```

## Spring Boot AOP

Aspect-Oriented Programming (AOP) in Spring Boot allows you to separate cross-cutting concerns like logging, security, and transaction management from business logic.

### Example

#### Aspect Class

```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void logBeforeMethod() {
        System.out.println("Method execution started");
    }
}
```

## Spring Boot Database

Spring Boot simplifies database interactions with Spring Data JPA, providing a powerful and easy-to-use data access layer.

### Example

#### Entity Class

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    // Getters and setters
}
```

#### Repository Interface

```java
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
```

#### Service Class

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Other CRUD methods
}
```

## Spring Boot REST

Spring Boot provides comprehensive support for building RESTful web services.

### Example

#### Controller

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
```

### `application.properties` for REST

```properties
spring.jackson.serialization.indent_output=true
```

## Conclusion

Spring Boot simplifies the development of Java-based enterprise applications. With features like dependency injection, AOP, integrated databases, and RESTful web services, Spring Boot enables developers to create robust and scalable applications with minimal effort. The convention-over-configuration approach and extensive ecosystem of modules further enhance productivity and ease of development.