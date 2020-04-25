<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Recommendation</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="joblist" value="${recom}" />
<a class = "btn btn-primary" href="${contextPath}/user/employee.htm" >Home Page</a>

<c:choose>
 <c:when test="${empty joblist}">
			<h3>Sorry, your skillset not matched with available jobs on the portal</h3>
		</c:when>
<c:otherwise>
<h3>Recommended Jobs </h3>
<br>
<br>
<table class="table-condensed" border="1">

<tr>
<td id="td1"> Job ID </td>
<td id="td1"> Company </td>
<td id="td1"> Job Name </td>
<td id="td1"> Job Description </td>
<td id="td1"> Required Skills</td>
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
<td> ${job.reqSkill} </td>
<td align="center"><input type="hidden" name="jobID"  value="${job.jobID}"><input class = "btn btn-success" type="submit" name="action" value="apply"></td>
</tr>
</form:form>


</c:forEach>
</table> 
</c:otherwise>
</c:choose> 

</body>
</html>