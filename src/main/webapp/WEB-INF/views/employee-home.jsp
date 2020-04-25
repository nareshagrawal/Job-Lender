<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Home</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

</head>
<body>

<c:set var="joblist" value="${jobs}" />

<nav class="navbar navbar-light" style="background-color: 42403F;">
<a class = "btn btn-primary" href="${contextPath}/employee/profile.htm" >Profile</a>
<a class = "btn btn-primary" href="${contextPath}/employee/jobs/search.htm" >Search Job</a>
<a class = "btn btn-primary" href="${contextPath}/employee/jobs/recommended.htm" >Job Recommendation</a>
<a class = "btn btn-primary" href="${contextPath}/employee/jobs/applied.htm" >Applied Jobs</a>
<a id ="a1" class = "btn btn-danger" href="${contextPath}" > Logout</a>
<a id ="a1" class = "btn btn-danger" href="${contextPath}/employee/deleteaccount" onclick = "if (! confirm('Account will be deleted permanently, want to continue?')) return false;" > Delete Account </a>
</nav>
<h3>Welcome ${user.firstName}</h3>
<br>
<br>
<table class="table-condensed" border="1">

<tr>
<td id="td1"> Job ID </td>
<td id="td1"> Company </td>
<td id="td1"> Job Name </td>
<td id="td1"> Job Description </td>
<td id="td1"> Required Skills</td>
<td id="td1"> Job Posted By </td>
<td id="td1"> Job Posted Date </td>
<td id="td1"> Apply Job </td>
<td id="td1"> Withdraw Application</td>


</tr>
<c:forEach items="${joblist}" var="job">

<form:form action="${contextPath}/employee/jobs/add.htm" commandName="joblist"
		method="post">
<tr>
<td> ${job.jobID} </td>
<td> ${job.company.companyName} </td>
<td> ${job.jobName} </td>
<td> ${job.jobDesc} </td>
<td> ${job.reqSkill} </td>
<td> ${job.postedName} </td>
<td> ${job.postedDate} </td> 
<td align="center"><input type="hidden" name="jobID"  value="${job.jobID}"><input class = "btn btn-success" type="submit" name="action" value=" Apply "></td>
<td align="center"><a class = "btn btn-danger" href="${contextPath}/employee/jobs/unapply.htm?id=${job.jobID}" onclick = "if (! confirm('Application will be withdrawn, want to continue?')) return false;" > Withdraw</a></td>
</tr>
</form:form>

</c:forEach>
</table> 

</body>
</html>