# JSP: Java Server Programming

## Scripting Elements

In JSP (JavaServer Pages), scripting elements are used to embed Java code into the HTML content. There are three types of scripting elements:

### 1. Scriptlets (`<% %>`)

Scriptlets allow you to include Java code within the JSP. The code inside a scriptlet is executed each time the page is requested.

```jsp
<% 
    int count = 10;
    out.println("Count is: " + count);
%>
```

### 2. Declarations (`<%! %>`)

Declarations define methods and variables that get inserted into the servlet class. These are initialized when the servlet is loaded and are shared across all requests.

```jsp
<%! 
    int instanceVar = 0;

    public void incrementCount() {
        instanceVar++;
    }
%>
```

### 3. Expressions (`<%= %>`)

Expressions are used to output Java values directly into the HTML. The result of the expression is converted to a string and included in the response.

```jsp
<%= new java.util.Date() %>
```

## Directive Elements

Directives provide global information about the entire JSP page and can be categorized into three types:

### 1. Page Directive

The page directive defines attributes that apply to the entire JSP page. Common attributes include `import`, `contentType`, and `errorPage`.

```jsp
<%@ page import="java.util.*, java.text.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="error.jsp" %>
```

### 2. Include Directive

The include directive includes the content of another file (static or dynamic) during the translation phase.

```jsp
<%@ include file="header.jsp" %>
```

### 3. Taglib Directive

The taglib directive declares a custom tag library that can be used in the JSP page.

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

## CRUD Operations

CRUD (Create, Read, Update, Delete) operations are essential for managing data in a web application. In JSP, these operations typically involve interactions with a database through servlets.

### Example: Employee Management

#### Create

**HTML Form (create.jsp)**

```jsp
<form action="createEmployee" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>
    <input type="submit" value="Create Employee">
</form>
```

**Servlet (CreateEmployeeServlet.java)**

```java
@WebServlet("/createEmployee")
public class CreateEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        // Code to insert the employee into the database
        // ...

        response.sendRedirect("employeeList.jsp");
    }
}
```

#### Read

**Servlet (EmployeeListServlet.java)**

```java
@WebServlet("/employeeList")
public class EmployeeListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Code to retrieve employees from the database
        List<Employee> employees = // ...

        request.setAttribute("employees", employees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employeeList.jsp");
        dispatcher.forward(request, response);
    }
}
```

**JSP (employeeList.jsp)**

```jsp
<table>
    <tr>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.name}</td>
            <td>${employee.email}</td>
            <td><a href="updateEmployee.jsp?id=${employee.id}">Edit</a></td>
            <td><a href="deleteEmployee?id=${employee.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
```

#### Update

**HTML Form (updateEmployee.jsp)**

```jsp
<form action="updateEmployee" method="post">
    <input type="hidden" name="id" value="${employee.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${employee.name}" required><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${employee.email}" required><br>
    <input type="submit" value="Update Employee">
</form>
```

**Servlet (UpdateEmployeeServlet.java)**

```java
@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Code to update the employee in the database
        // ...

        response.sendRedirect("employeeList.jsp");
    }
}
```

#### Delete

**Servlet (DeleteEmployeeServlet.java)**

```java
@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // Code to delete the employee from the database
        // ...

        response.sendRedirect("employeeList.jsp");
    }
}
```

---

This document provides an overview of JSP scripting elements, directive elements, and demonstrates CRUD operations using JSP and servlets. It serves as a comprehensive guide for developers working with JSP in enterprise Java web applications.