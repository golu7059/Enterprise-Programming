package com.example;

import com.example.dao.EmployeeDAO;
import com.example.model.Employee;

public class App {

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // CREATE
        Employee employee = new Employee("Golu", "Kuamr", "golu@google.com");
        employeeDAO.saveEmployee(employee);

        // READ
        Employee e = employeeDAO.getEmployee(employee.getId());
        System.out.println("Employee retrieved: " + e.getFirstName() + " " + e.getLastName());

        // UPDATE
        // e.setFirstName("Jane");
        // employeeDAO.updateEmployee(e);

        // DELETE
        // employeeDAO.deleteEmployee(e.getId());

        // LIST ALL EMPLOYEES
        employeeDAO.getAllEmployees().forEach(emp -> System.out.println(emp.getFirstName()));
    }
}
