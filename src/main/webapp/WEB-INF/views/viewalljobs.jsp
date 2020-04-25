<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View All Jobs</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="joblist" value="${jobs}" />
<a class = "btn btn-success" href="${contextPath}" >Home</a>
<a class = "btn btn-success" href="${contextPath}/user/login.htm" >Login And Start Applying</a>
<br>
<br>
<c:choose>
 <c:when test="${empty joblist1}">
			<h3>No job posted yet</h3>
		</c:when>
<c:otherwise>
<table class="table-condensed" border="1">

<tr>
<td id="td1"> Job ID </td>
<td id="td1"> Company </td>
<td id="td1"> Job Name </td>
<td id="td1"> Job Description </td>
<td id="td1"> Required Skills</td>
<td id="td1"> Posted By </td>
<td id="td1"> Posted Date </td>
</tr>

<c:forEach items="${joblist1}" var="job">

<tr>
<td> ${job.jobID} </td>
<td> ${job.company.companyName} </td>
<td> ${job.jobName} </td>
<td> ${job.jobDesc} </td>
<td> ${job.reqSkill} </td>
<td> ${job.postedName} </td>
<td> ${job.postedDate} </td>
</tr>
</c:forEach>
</table>
	</c:otherwise>
	</c:choose> 
</body>
</html>