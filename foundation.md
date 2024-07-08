# Foundations of Enterprise Programming

## JDBC (Java Database Connectivity)

JDBC is an API (Application Programming Interface) in Java that allows Java programs to interact with relational databases. It provides methods for querying and updating data in a database.

### JDBC Architecture

JDBC architecture consists of two main layers:

1. **JDBC API**: Provides the application-to-JDBC Manager connection.
2. **JDBC Driver API**: Provides the JDBC Manager-to-Driver connection.

The architecture includes four components:
- **DriverManager**: Manages a list of database drivers.
- **Driver**: Interface that handles the communications with the database server.
- **Connection**: Interface with all methods for connecting a database.
- **Statement**: Interface for executing SQL statements.
- **ResultSet**: Interface that represents the result set of a query.
- **SQLException**: Class for handling any errors that occur in a database application.

### Steps to Use JDBC

1. **Register the Driver**: Load the JDBC driver class using `Class.forName()` method.
2. **Create a Connection**: Establish a connection using `DriverManager.getConnection()` method.
3. **Create a Statement**: Use the `Connection` object to create a `Statement` object.
4. **Execute a Query**: Execute SQL queries using `Statement` object.
5. **Process the Result**: Process the `ResultSet` returned by executing the query.
6. **Close the Connection**: Close the connection to free up resources.

### JDBC with Oracle

To connect Java applications with an Oracle database, follow these steps:

1. **Add Oracle JDBC Driver**: Ensure the Oracle JDBC driver (`ojdbc.jar`) is added to the project's classpath.
2. **Register the Driver**:
    ```java
    Class.forName("oracle.jdbc.driver.OracleDriver");
    ```
3. **Create a Connection**:
    ```java
    Connection connection = DriverManager.getConnection(
        "jdbc:oracle:thin:@localhost:1521:xe", "username", "password");
    ```
4. **Create and Execute Statements**:
    ```java
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM my_table");
    while (rs.next()) {
        System.out.println(rs.getString(1) + " " + rs.getString(2));
    }
    ```
5. **Close the Connection**:
    ```java
    connection.close();
    ```

### JDBC with MySQL

To connect Java applications with a MySQL database, follow these steps:

1. **Add MySQL JDBC Driver**: Ensure the MySQL JDBC driver (`mysql-connector-java.jar`) is added to the project's classpath.
2. **Register the Driver**:
    ```java
    Class.forName("com.mysql.cj.jdbc.Driver");
    ```
3. **Create a Connection**:
    ```java
    Connection connection = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/mydb", "username", "password");
    ```
4. **Create and Execute Statements**:
    ```java
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM my_table");
    while (rs.next()) {
        System.out.println(rs.getString(1) + " " + rs.getString(2));
    }
    ```
5. **Close the Connection**:
    ```java
    connection.close();
    ```

## Maven

Maven is a build automation tool used primarily for Java projects. It helps manage project dependencies, build processes, and documentation.

### Integration with Eclipse

1. **Install Maven**: Install Maven plugin for Eclipse if not already installed.
2. **Create Maven Project**: Go to File > New > Other > Maven Project.
3. **Configure Project**: Follow the wizard to set up the Maven project.
4. **Add Dependencies**: Add necessary dependencies in the `pom.xml` file.

### POM.xml (Project Object Model)

The `pom.xml` file is the core of a Maven project. It contains information about the project and configuration details used by Maven to build the project.

#### Basic Structure of POM.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.22</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.2.9.RELEASE</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

- **groupId**: Identifier for the project group.
- **artifactId**: Name of the project.
- **version**: Version of the project.
- **dependencies**: List of project dependencies.
- **build**: Build configuration, including plugins.

### Common Maven Commands

- **Compile**: `mvn compile`
- **Test**: `mvn test`
- **Package**: `mvn package`
- **Install**: `mvn install`
- **Clean**: `mvn clean`

---
