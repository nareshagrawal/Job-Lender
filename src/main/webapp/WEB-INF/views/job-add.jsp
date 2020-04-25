<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jobs Add</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">

</head>
<body>
<c:set var="companyname" value="${companyName}" />


<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/employer.htm" >Home Page</a>
<h3>Add a New Job for ${companyname}</h3>
<br/>
<br/>
	<form:form action="${contextPath}/employer/job/register.htm" commandName="joblist"
		method="post">

		<table class="table">
			<tr>
				<td>Job Name:</td>
				<td><form:input path="jobName" size="30" required="required" />
					<font color="red"><form:errors path="jobName" /></font></td>
			</tr>

			<tr>
				<td>Job Description</td>
				<td><form:input path="jobDesc" size="30" required="required" />
					<font color="red"><form:errors path="jobDesc" /></font></td>
			</tr>
			
			<tr>
				<td>Required Skills</td>
				<td><form:input path="reqSkill" size="30" required="required" />
					<font color="red"><form:errors path="reqSkill" /></font></td>
			</tr>

			<tr>
			<td></td>
				<td colspan="2"><input class = "btn btn-success" type="submit" value="ADD" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>