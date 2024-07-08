# Spring Framework

## Overview

Spring is a comprehensive framework for enterprise Java development. It provides infrastructure support for developing robust Java applications. Spring is famous for its dependency injection, modular architecture, and extensive ecosystem of modules.

## Architecture

The core components of the Spring architecture include:

- **Spring Core**: Provides fundamental features of the framework, including dependency injection.
- **Spring AOP**: Supports aspect-oriented programming.
- **Spring DAO**: Provides data access functionality.
- **Spring ORM**: Supports integration with ORM frameworks like Hibernate.
- **Spring Web**: Contains functionalities for web-based applications.
- **Spring MVC**: A Model-View-Controller framework for building web applications.

### Spring Architecture Diagram

```
+-------------------------+
|      Spring Core        |
+-------------------------+
|        Spring AOP       |
+-------------------------+
|        Spring DAO       |
+-------------------------+
|        Spring ORM       |
+-------------------------+
|        Spring Web       |
+-------------------------+
|        Spring MVC       |
+-------------------------+
```

## Modules

### Core Container

- **Core**: Provides fundamental parts of the framework, including the IoC (Inversion of Control) container.
- **Beans**: Contains the `BeanFactory` which is a sophisticated implementation of the factory pattern.
- **Context**: Builds on the core and beans modules, providing a framework for object creation and configuration.
- **Expression Language**: A powerful expression language for querying and manipulating an object graph at runtime.

### Data Access/Integration

- **JDBC**: Simplifies JDBC (Java Database Connectivity) operations.
- **ORM**: Provides integration layers for popular ORM frameworks like Hibernate, JPA, JDO, etc.
- **OXM**: Supports Object/XML mapping implementations.
- **JMS**: Supports Java Message Service (JMS).
- **Transaction**: Supports programmatic and declarative transaction management.

### Web

- **Web**: Provides basic web-oriented integration features.
- **Web-Servlet**: Contains Spring's MVC framework.
- **Web-Struts**: Supports integration with the Struts framework.
- **Web-Portlet**: Provides the MVC implementation for portlet environments.

## Dependency Injection

Dependency Injection (DI) is a design pattern used to implement IoC, allowing the Spring container to manage dependencies.

### Example

```xml
<bean id="employeeService" class="com.example.EmployeeService">
    <property name="employeeDao" ref="employeeDao"/>
</bean>

<bean id="employeeDao" class="com.example.EmployeeDao"/>
```

```java
public class EmployeeService {
    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    // Business methods
}
```

## Autowire

Spring supports autowiring to inject bean dependencies automatically.

### Autowire Modes

- **no**: Default, no autowiring.
- **byName**: Autowires by property name.
- **byType**: Autowires by property data type.
- **constructor**: Autowires the constructor with arguments.
- **autodetect**: Automatically selects between constructor or byType.

### Example

```xml
<bean id="employeeService" class="com.example.EmployeeService" autowire="byName"/>
```

## Application Context

The `ApplicationContext` is a central interface to the Spring IoC container.

### Example

```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);
```

## Annotation-Based Configuration

Spring supports annotations for dependency injection and configuration.

### Common Annotations

- `@Component`: Marks a Java class as a Spring bean.
- `@Autowired`: Autowires bean dependencies.
- `@Qualifier`: Specifies which bean to inject when there are multiple candidates.
- `@Configuration`: Indicates that a class is a source of bean definitions.
- `@Bean`: Indicates that a method produces a bean to be managed by the Spring container.

### Example

```java
@Configuration
public class AppConfig {
    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService();
    }

    @Bean
    public EmployeeDao employeeDao() {
        return new EmployeeDao();
    }
}

@Component
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    // Business methods
}
```

## Spring MVC

Spring MVC is a framework for building web applications. It follows the Model-View-Controller (MVC) pattern.

### Key Components

- **DispatcherServlet**: Front controller that dispatches requests to handlers.
- **Controller**: Handles user requests and returns a model and view.
- **Model**: Holds business data.
- **View**: Renders the model data.

### Example

```java
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employeeList";
    }
}
```

## CRUD Operations

### Create

```java
@RequestMapping(value = "/employee", method = RequestMethod.POST)
public String createEmployee(@ModelAttribute Employee employee) {
    employeeService.save(employee);
    return "redirect:/employees";
}
```

### Read

```java
@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
public String getEmployee(@PathVariable("id") int id, Model model) {
    Employee employee = employeeService.findById(id);
    model.addAttribute("employee", employee);
    return "employeeDetail";
}
```

### Update

```java
@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
public String updateEmployee(@PathVariable("id") int id, @ModelAttribute Employee employee) {
    employeeService.update(id, employee);
    return "redirect:/employees";
}
```

### Delete

```java
@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
public String deleteEmployee(@PathVariable("id") int id) {
    employeeService.delete(id);
    return "redirect:/employees";
}
```

---

This document provides an overview of the Spring Framework, including its architecture, modules, dependency injection, autowiring, application context, annotation-based configuration, MVC, and CRUD operations.