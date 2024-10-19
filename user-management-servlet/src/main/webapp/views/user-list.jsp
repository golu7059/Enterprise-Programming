<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.webapp.User" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h2>User List</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <%
            List<User> listUser = (List<User>) request.getAttribute("listUser");
            if (listUser != null) {
                for (User user : listUser) {
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getCountry() %></td>
            <td>
                <a href="user?action=edit&id=<%= user.getId() %>">Edit</a>
                <a href="user?action=delete&id=<%= user.getId() %>">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No users found.</td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <a href="user?action=new">Add New User</a>
</body>
</html>
