<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resume</title>

<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">

<style>
  .container{padding: 30px;}
</style>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
<c:set var="employee" value="${emp}" />
<a class = "btn btn-primary" href="${contextPath}/user/employer.htm" >Home Page</a>
<div class="container">
                	<embed src="/img/${employee.resumeName}" type="application/pdf" width="1000" height="600" />
</div>

</body>
</html>