<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Applied Candidate</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
<c:set var="id" value="${JOBID}" />
<nav class="navbar navbar-dark bg-dark" style="background-color: 42403F;">
<a class = "btn btn-primary" href="${contextPath}/user/employer.htm" >Home Page</a>
</nav>

<c:choose>

		<c:when test="${empty list }">
			<h3>No candidate apply for the job with Job ID: ${id}</h3>
		</c:when>
<c:otherwise>
<h3>Candidates applied for Job ID: ${id}</h3>
<br>
<br>

<table class="table-condensed" border="1">

<tr>
<td id="td1"> First Name </td>
<td id="td1"> Last Name </td>
<td id="td1"> Email ID </td>
<td id="td1"> View Profile </td>
<td id="td1"> View Resume </td>
<td id="td1"> Send Mail </td>
</tr>

<c:forEach items="${list}" var="list">
<tr>
<td> ${list.firstName} </td>
<td> ${list.lastName} </td>
<td> ${list.email} </td>
<td align="center"><a class = "btn btn-success" href="${contextPath}/employee/profile.pdf?id=${list.userID}" > View </a></td>
<td align="center"><a class = "btn btn-success" href="${contextPath}/employee/resume.pdf?id=${list.userID}" > View </a></td>
<td align="center"><a class = "btn btn-success" href="${contextPath}/employee/contact.htm?id=${list.userID}" > Mail </a></td> 
</tr>
			</c:forEach>
		</table>
  </c:otherwise>
</c:choose>
</body>
</html>