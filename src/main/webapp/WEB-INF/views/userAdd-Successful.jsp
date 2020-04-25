<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Register Success</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>
<br>
<h4 style="font-size:30px;"><b>Hi, <i> ${user.firstName}</i></b></h4>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<h5>Thank you for Registering</h5>
<br>
<small>  Please go back to the login page</small>
<a class = "btn btn-primary" href="${contextPath}/user/login.htm" >Return to Login Page</a> <br />



</body>
</html>