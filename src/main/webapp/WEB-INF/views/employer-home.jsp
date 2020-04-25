<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employer Home Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="joblist" value="${jobs}" />
<nav class="navbar navbar-light" style="background-color: 42403F;">
<a class = "btn btn-primary" href="${contextPath}/employer/job/register.htm" >Add a new Job for ${user.company.companyName}</a>
<a id ="a1" class = "btn btn-danger" href="${contextPath}" > Logout</a>
</nav>
 <h3>The Below Jobs are already present for ${user.company.companyName}</h3>
<br>
<br>
<table class="table-condensed" border="1">

<tr>
<td id="td1"> Job ID </td>
<td id="td1"> Job Name </td>
<td id="td1"> Job Description </td>
<td id="td1"> Required Skills</td>
<td id="td1"> Posted By </td>
<td id="td1"> Posted Date </td>
<td id="td1"> Applied Candidate </td>
<td id="td1"> Update Job </td>
<td id="td1"> Delete Job </td>

</tr>
<c:forEach items="${joblist}" var="job">

<tr>
<td> ${job.jobID} </td>
<td> ${job.jobName} </td>
<td> ${job.jobDesc} </td>
<td> ${job.reqSkill} </td>
<td> ${job.postedName} </td>
<td> ${job.postedDate} </td>
<td align="center"><a class = "btn btn-success" href="${contextPath}/employer/appliedlist.htm?id=${job.jobID}" > View List </a></td>
<td align="center"><a class = "btn btn-success" href="${contextPath}/employer/jobs/update.htm?id=${job.jobID}" > Update </a></td>
<td align="center"><a class = "btn btn-danger" href="${contextPath}/employer/jobs/delete.htm?id=${job.jobID}" onclick = "if (! confirm('Job post will be deleted permanently, want to continue?')) return false;" > Delete </a></td>
 </tr>

</c:forEach>
</table> 

</body>
</html>