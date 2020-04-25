<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jobs Add</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">

</head>
<body>
<c:set var="companyname" value="${companyName}" />
<c:set var="job" value="${jobList}" />

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/employer.htm" >Home Page</a>
<h3>Update the job for ${companyname}</h3>
<br/>
<br/>
	<form:form action="${contextPath}/employer/jobs/update.htm"
		method="post">

		<table class="table">
			<tr>
				<td>Job ID:</td>
				<td><input type ="text" name="jobId" value="${job.jobID}" readonly></td>
			</tr>
			
			<tr>
				<td>Job Name:</td>
				<td><input type ="text" name = "jobName" value="${job.jobName}" required></td>
			</tr>
			
			<tr>
				<td>Job Description:</td>
				<td><input type ="text" name = "jobDesc" value="${job.jobDesc}" required></td>
			</tr>
			
			<tr>
				<td>Required Skills:</td>
				<td><input type ="text" name = "reqSkill" value="${job.reqSkill}" required></td>
			</tr>
			
			

			<tr>
				<td></td>
				<td colspan="2"><input type="hidden" name="opt"  value="updatejob"><input class = "btn btn-success" type="submit" value="Update Job" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>