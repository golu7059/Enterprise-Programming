<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Welcome</title>
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"/>
	<style type="text/css">
		.td-align {
			text-align: justify;
		}
	</style>
</head>
<body>
	<h1>Customer Management</h1>
	<a href="addcustomer">Add Customer</a>
	<br/><br/>
	<div class="container-fluid" style="width: 70%; font-family: arial; font-size: 17px;">
		<table class="table table-bordered table-striped text-center">
			<thead>
				<tr style="background-color:#eab676">
					<th >Id</th>
					<th>Full Name</th>
					<th>Address</th>
					<th>City</th>
					<th>Mobile no</th>
					<th>Action</th>
				</tr>
			</thead>
			<c:forEach var="customer" items="${allcustomers}">
				<tr>
					<td class="td-align">${customer.id}</td>
					<td class="td-align">${customer.custName}</td>
					<td class="td-align">${customer.custAdd}</td>
					<td class="td-align">${customer.custCity}</td>
					<td class="td-align">${customer.custMobile}</td>
					<td><a href="editCustomer?id=${customer.id}" class="btn btn-primary">Edit</a> <a
						href="delCustomer?id=${customer.id}" class="btn btn-danger">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>