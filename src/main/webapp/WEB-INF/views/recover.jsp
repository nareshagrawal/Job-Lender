<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recover Account</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
<h3><font color="red">${errorMessage}</font></h3>
<br>
<h4>Want to reactivate account, please enter following details </h4>
<br>
<form action="${contextPath}/user/reactive.htm" method="post">
	
		<table class="table" >
		<tr>
		    <td>User Name:</td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		<tr>
		    <td>Email:</td>
		    <td><input type="email" name="email" size="30" required="required"/></td>
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