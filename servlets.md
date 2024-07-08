# Servlets

## Basics of Web

Web development involves creating and maintaining websites and web applications that run on web browsers. Web applications typically follow a client-server architecture where the client (web browser) sends requests to the server (web server), which processes the requests and sends back responses.

## Servlet Lifecycle

A servlet's lifecycle is managed by the servlet container (e.g., Tomcat). The lifecycle consists of the following phases:

1. **Loading and Instantiation**: The servlet class is loaded and an instance of the servlet is created.
2. **Initialization (`init` method)**: The `init` method is called once when the servlet is first loaded to perform any necessary initialization.
3. **Request Handling (`service` method)**: The `service` method is called to handle each client request. It dispatches the request to the appropriate `doGet`, `doPost`, `doPut`, `doDelete`, etc., methods.
4. **Destruction (`destroy` method)**: The `destroy` method is called once when the servlet is being taken out of service to perform any necessary cleanup.

```java
public class MyServlet extends HttpServlet {
    public void init() throws ServletException {
        // Initialization code
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Request handling code
    }

    public void destroy() {
        // Cleanup code
    }
}
```

## Servlets API

The Servlet API provides a set of interfaces and classes that support the development of servlets. Key components include:

- **Servlet**: The central interface for all servlets.
- **GenericServlet**: An abstract class that implements the Servlet interface and provides basic functionality.
- **HttpServlet**: An abstract class that extends GenericServlet and adds support for HTTP-specific features.

### Important Interfaces and Classes

- `Servlet`
- `ServletRequest`
- `ServletResponse`
- `HttpServletRequest`
- `HttpServletResponse`
- `ServletConfig`
- `ServletContext`

## HTTP Servlets with XML and Annotation

### XML Configuration

You can configure servlets in the `web.xml` file located in the `WEB-INF` directory of your web application.

```xml
<web-app>
    <servlet>
        <servlet-name>MyServlet</servlet-name>
        <servlet-class>com.example.MyServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name>
        <url-pattern>/myservlet</url-pattern>
    </servlet-mapping>
</web-app>
```

### Annotation Configuration

Annotations provide an alternative to XML configuration. You can annotate your servlet class with `@WebServlet`.

```java
import javax.servlet.annotation.WebServlet;

@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {
    // Implementation
}
```

## Servlets Configuration

Servlets can be configured using the `ServletConfig` interface. `ServletConfig` allows you to pass initialization parameters to a servlet.

```java
public class MyServlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        String paramValue = config.getInitParameter("myParam");
        // Use paramValue
    }
}
```

In `web.xml`:

```xml
<servlet>
    <servlet-name>MyServlet</servlet-name>
    <servlet-class>com.example.MyServlet</servlet-class>
    <init-param>
        <param-name>myParam</param-name>
        <param-value>paramValue</param-value>
    </init-param>
</servlet>
```

## Servlets Context

The `ServletContext` interface provides information about the web application and allows servlets to communicate with each other.

```java
public class MyServlet extends HttpServlet {
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String contextParam = context.getInitParameter("contextParam");
        // Use contextParam
    }
}
```

In `web.xml`:

```xml
<context-param>
    <param-name>contextParam</param-name>
    <param-value>value</param-value>
</context-param>
```

## Servlets Collaboration

Servlets can collaborate by sharing data using `ServletContext` attributes or by forwarding requests.

### Sharing Data

```java
// Set attribute
ServletContext context = getServletContext();
context.setAttribute("sharedData", data);

// Get attribute
Object data = context.getAttribute("sharedData");
```

### Request Forwarding

```java
RequestDispatcher dispatcher = request.getRequestDispatcher("/otherServlet");
dispatcher.forward(request, response);
```

## Session Tracking

Session tracking is a mechanism to maintain state about a series of requests from the same user (session).

### Using HttpSession

```java
HttpSession session = request.getSession();
session.setAttribute("username", "JohnDoe");

// Retrieving session data
String username = (String) session.getAttribute("username");
```

### URL Rewriting

```java
String url = response.encodeURL("profile.jsp");
```

## CRUD Operations

CRUD (Create, Read, Update, Delete) operations are essential for managing data in web applications.

### Example of CRUD Operations in Servlets

#### Create

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String name = request.getParameter("name");
    // Code to save the data (e.g., insert into database)
}
```

#### Read

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Code to retrieve data from database
    request.setAttribute("data", data);
    RequestDispatcher dispatcher = request.getRequestDispatcher("data.jsp");
    dispatcher.forward(request, response);
}
```

#### Update

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String id = request.getParameter("id");
    String newName = request.getParameter("name");
    // Code to update data in database
}
```

#### Delete

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String id = request.getParameter("id");
    // Code to delete data from database
}
```

---

This document provides a comprehensive overview of servlets, including their lifecycle, API, configuration, and practical use cases like session tracking and CRUD operations. It serves as a quick reference guide for developers working on enterprise-level Java web applications.