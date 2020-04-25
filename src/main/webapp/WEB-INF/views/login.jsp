<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job lender</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">

<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-dark bg-dark" style="background-color: 42403F;">
	<a class = "btn btn-info" href="${contextPath}">Home</a>
	<a class = "btn btn-primary" href="${contextPath}/user/register.htm">Register a new User</a><br/>
</nav>
	<h2>Existing User <small>Please Enter Your Credentials</small></h2>
	<br/>
	<form action="${contextPath}/user/login.htm" method="post">
	
		<table class="table" >
		<tr>
		    <td>User Name:</td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		<td></td>
		    <td colspan="2"><input class = "btn btn-success" type="submit" value="Login" /></td>
		</tr>
				
		</table>

	</form>

</body>
</html>