<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Result</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="joblist" value="${jobs}" />

<a class = "btn btn-primary" href="${contextPath}/user/employee.htm" >Home Page</a>
<a class = "btn btn-primary" href="${contextPath}/employee/jobs/search.htm" >Search Job</a>
<h3>Below are the current jobs </h3>
<br>
<br>
<table class="table-condensed" border="1">

<tr>
<td id="td1"> Job ID </td>
<td id="td1"> Company </td>
<td id="td1"> Job Name </td>
<td id="td1"> Job Description </td>
<td id="td1"> Apply Job </td>


</tr>
<c:forEach items="${joblist}" var="job">

<form:form action="${contextPath}/employee/jobs/add.htm" commandName="joblist"
		method="post">
<tr>
<td> ${job.jobID} </td>
<td> ${job.company.companyName} </td>
<td> ${job.jobName} </td>
<td> ${job.jobDesc} </td>
<td align="center"><input type="hidden" name="jobID"  value="${job.jobID}"><input class = "btn btn-success" type="submit" name="action" value="apply"></td>
</tr>
</form:form>


</c:forEach>
</table> 



</body>

</html>