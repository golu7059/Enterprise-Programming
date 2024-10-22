<%@page import="com.springmvc.dao.customerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Add Customer</title>
	<style>
		textarea {
			resize: none;
		}
	</style>
</head>
<body>
	<h2>Add new Customer</h2>
	
	<form action="saveCustomer" method="post">
		<b>Customer ID</b><br/>
		<input type="text" name="id" placeholder="code" size="3" value=${customer.id} ><br/><br/>
		<b>Customer name</b><br/>
		<textarea name="custName" placeholder="customer name" rows="1" cols="40">${customer.custName}</textarea><br/><br/>
		<b>Customer Address</b><br/>
		<textarea name="custAdd" rows="3" cols="30">${customer.custAdd}</textarea><br/><br/>
		<b>Customer City</b><br/>
		<input type="text" name="custCity" placeholder="city" size="30" value=${customer.custCity}><br/><br/>
		<b>Customer Mobile</b><br/>
		<input type="text" name="custMobile" placeholder="mobile" size="10" value=${customer.custMobile}><br/><br/>
		
		<input type="submit" value="Save Customer"/>
	</form>	
</body>
</html>