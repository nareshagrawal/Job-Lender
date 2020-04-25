<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Company Registration</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
    
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/admin.htm" >Home Page</a>
<h3>Register a New Company</h3>
<br>
<br>
	<form:form action="${contextPath}/admin/company/register.htm" commandName="company"
		method="post">

		<table class="table">
			<tr>
				<td>Company Name:</td>
				<td><form:input path="companyName" size="30" required="required" />
					<font color="red"><form:errors path="companyName" /></font></td>
			</tr>

			<tr>
				<td>Company Description:</td>
				<td><form:input path="companyDescription" size="30" required="required" />
					<font color="red"><form:errors path="companyDescription" /></font></td>
			</tr>
			
			<tr>
				<td>Company Address:</td>
				<td><form:input path="companyAddress" size="30" required="required" />
					<font color="red"><form:errors path="companyAddress" /></font></td>
			</tr>

			<tr>
				<td></td>
				<td colspan="2"><input class = "btn btn-success" type="submit" value="Register Company" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>