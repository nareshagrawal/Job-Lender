<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Home Page</title>

<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
    
</head>
<body>

 <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-light" style="background-color: 42403F;">
<a class = "btn btn-primary" href="${contextPath}/admin/company/register.htm" >Register New Company</a>
<a id ="a1" class = "btn btn-danger" href="${contextPath}" > Logout</a>
</nav>
<h3>The Below Companies are already present in Portal</h3>
<br>
<br>
<table class="table-condensed" border="1">
<tr>
<td id="td1"> Company ID </td>
<td id="td1"> Company Name </td>
<td id="td1"> Company Description </td>
<td id="td1"> Company Address </td>
<td id="td1"> Jobs Posted </td>
<td id="td1"> Update Company </td>
<td id="td1"> Delete Company </td>


</tr>
<c:forEach items="${companies}" var="comp">

<tr>
<td> ${comp.companyId} </td>
<td> ${comp.companyName} </td>
<td> ${comp.companyDescription} </td>
<td> ${comp.companyAddress} </td>
<td align="center"><a class = "btn btn-success" href="${contextPath}/admin/company/jobposted.htm?id=${comp.companyId}" > View </a></td>
<td align="center"><a class = "btn btn-success" href="${contextPath}/admin/company/update.htm?id=${comp.companyId}" > Update </a></td>
<td align="center"><a class = "btn btn-danger" href="${contextPath}/admin/company/delete.htm?id=${comp.companyId}" onclick = "if (! confirm('Company will be deleted permanently, want to continue?')) return false;" > Delete </a></td>

</tr>

</c:forEach>
</table>

</body>
</html>