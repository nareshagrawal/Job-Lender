<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Home</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet"
	href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="joblist" value="${user.joblists}" />

	<a class = "btn btn-primary" href="${contextPath}/user/employee.htm" >Home Page</a>
	<a class="btn btn-primary" href="${contextPath}/employee/jobs/search.htm">Search Job</a>
		
<c:choose>

		<c:when test="${empty appliedJob }">
			<h3>Please apply to jobs for viewing this section</h3>
		</c:when>
<c:otherwise>
<h3>Applied Jobs for ${user.firstName}</h3>
<br/>
<br/>
			<table class="table-condensed" border="1">

				<tr>
					<td id="td1"> JOb ID </td>
					<td id="td1"> Company </td>
					<td id="td1"> Job Name </td>
					<td id="td1"> Job Description </td>
					<td id="td1"> Required Skills</td>
					<td id="td1"> Job Posted on </td>
					<td id="td1"> Withdraw Application</td>

				</tr>
				<c:forEach items="${appliedJob}" var="job">
						<tr>
							<td> ${job.jobID} </td>
							<td> ${job.company.companyName} </td>
							<td> ${job.jobName} </td>
							<td> ${job.jobDesc} </td>
							<td> ${job.reqSkill} </td>
							<td> ${job.postedDate} </td>
							<td align="center"><a class = "btn btn-danger" href="${contextPath}/employee/jobs/unapply.htm?id=${job.jobID}" onclick = "if (! confirm('Application will be withdrawn, want to continue?')) return false;" > Withdraw</a></td>
						</tr>
					
				</c:forEach>
			</table>

		</c:otherwise>
	</c:choose>
</body>

</html>