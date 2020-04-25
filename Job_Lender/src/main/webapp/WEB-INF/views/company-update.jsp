<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Company Update</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>
<c:set var="companyname" value="${companyName}" />
<c:set var="company" value ="${comp}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/admin.htm" >Home Page</a>
<h3>Updating ${companyname}</h3>
<br>
<br>
<form:form action="${contextPath}/admin/company/update.htm" method="post">

		<table class="table">
			<tr>
				<td>Company ID:</td>
				<td><input type ="text" name="companyId" value="${company.companyId}" readonly></td>
			</tr>
			
			<tr>
				<td>Company Name:</td>
				<td><input type ="text" name = "CompanyName" value="${company.companyName}" required></td>
			</tr>
			
			<tr>
				<td>Company Description:</td>
				<td><input type ="text" name = "companyDesc" value="${company.companyDescription}" required></td>
			</tr>
            <tr>
				<td>Company Address:</td>
				<td><input type ="text" name = "CompanyAddress" value="${company.companyAddress}" required ></td>
			</tr>
			
			

			<tr>
			<td></td>
				<td colspan="2"><input type="hidden" name="opt"  value="updatecomp"><input class = "btn btn-success" type="submit" value="Update Company" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>