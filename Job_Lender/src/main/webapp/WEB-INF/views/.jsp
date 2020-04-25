<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:set var="companyname" value="${companyName}" />
<a class = "btn btn-primary" href="${contextPath}/user/employer.htm" >Home Page</a>
<h3>Job deleted successfully</h3>
</body>
</html>