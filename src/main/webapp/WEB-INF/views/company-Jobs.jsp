<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job List for a company</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>
<c:set var="companyname" value="${companyName1}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/admin.htm" >Home Page</a>

<c:choose>

		<c:when test="${empty CompanyJobs }">
			<h3>No job posting available for the ${companyname}</h3>
		</c:when>
<c:otherwise>
<h3>Job posted by the ${companyname}</h3>
<br>
<br>
<table class="table-condensed" border="1">

<tr>
<td id="td1"> Job ID </td>
<td id="td1"> Job Name </td>
<td id="td1"> Job Description </td>
<td id="td1"> Required Skills</td>
</tr>

<c:forEach items="${CompanyJobs}" var="job">

<tr>
<td> ${job.jobID} </td>
<td> ${job.jobName} </td>
<td> ${job.jobDesc} </td>
<td> ${job.reqSkill} </td>
</tr>

			</c:forEach>
		</table> 
 	</c:otherwise>
</c:choose>

</body>
</html>