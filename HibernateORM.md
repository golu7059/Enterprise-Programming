# Hibernate (ORM)

## Overview

Hibernate is an Object-Relational Mapping (ORM) framework for Java, which simplifies data manipulation by mapping Java objects to database tables. It abstracts the complexity of database interactions and provides an efficient way to perform CRUD operations.

## Architecture

The core components of Hibernate architecture include:

- **Configuration**: Specifies database configurations and mappings.
- **SessionFactory**: A factory for `Session` objects.
- **Session**: A single-threaded object representing a connection to the database.
- **Transaction**: Manages transactions in Hibernate.
- **Query**: Used to perform queries on the database.

### Configuration

```xml
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/yourdb</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">password</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
    </session-factory>
</hibernate-configuration>
```

## JPA (Java Persistence API)

JPA is a specification for accessing, persisting, and managing data between Java objects and a relational database. Hibernate is a popular implementation of JPA.

### Example Entity

```java
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private String email;

    // Getters and setters
}
```

## Generator Class

Generator classes in Hibernate define strategies for generating unique identifiers for entities.

### Example

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    // Getters and setters
}
```

## Dialects

Hibernate Dialects define SQL variations for different database management systems. Some common dialects are:

- `org.hibernate.dialect.MySQLDialect`
- `org.hibernate.dialect.OracleDialect`
- `org.hibernate.dialect.PostgreSQLDialect`

## Mapping

Mapping defines the relationship between Java objects and database tables.

### XML Mapping

```xml
<hibernate-mapping>
    <class name="Employee" table="employee">
        <id name="id" column="id">
            <generator class="native"/>
        </id>
        <property name="name" column="name"/>
        <property name="email" column="email"/>
    </class>
</hibernate-mapping>
```

### Annotation Mapping

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    // Getters and setters
}
```

## Annotations

Hibernate annotations provide a way to map classes and their fields to database tables and columns using annotations.

### Example

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    // Getters and setters
}
```

## Transaction Management

Transaction management in Hibernate ensures data integrity and consistency.

### Example

```java
Session session = sessionFactory.openSession();
Transaction transaction = null;
try {
    transaction = session.beginTransaction();
    Employee employee = new Employee();
    employee.setName("John Doe");
    session.save(employee);
    transaction.commit();
} catch (Exception e) {
    if (transaction != null) transaction.rollback();
    e.printStackTrace();
} finally {
    session.close();
}
```

## HQL (Hibernate Query Language)

HQL is an object-oriented query language for Hibernate.

### Example

```java
String hql = "FROM Employee E WHERE E.id = :employee_id";
Query query = session.createQuery(hql);
query.setParameter("employee_id", 1);
List results = query.list();
```

## HCQL (Hibernate Criteria Query Language)

HCQL allows for creating queries using Criteria objects.

### Example

```java
Criteria criteria = session.createCriteria(Employee.class);
criteria.add(Restrictions.eq("id", 1));
List results = criteria.list();
```

## CRUD Operations

### Create

```java
Session session = sessionFactory.openSession();
Transaction transaction = session.beginTransaction();

Employee employee = new Employee();
employee.setName("John Doe");
employee.setEmail("john.doe@example.com");
session.save(employee);

transaction.commit();
session.close();
```

### Read

```java
Session session = sessionFactory.openSession();
Employee employee = session.get(Employee.class, 1);
System.out.println(employee.getName());

session.close();
```

### Update

```java
Session session = sessionFactory.openSession();
Transaction transaction = session.beginTransaction();

Employee employee = session.get(Employee.class, 1);
employee.setName("Jane Doe");
session.update(employee);

transaction.commit();
session.close();
```

### Delete

```java
Session session = sessionFactory.openSession();
Transaction transaction = session.beginTransaction();

Employee employee = session.get(Employee.class, 1);
session.delete(employee);

transaction.commit();
session.close();
```

---

This document provides a comprehensive overview of Hibernate ORM, including architecture, JPA, generator classes, dialects, mapping, annotations, transaction management, HQL, HCQL, and CRUD operations.