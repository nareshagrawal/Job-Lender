<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Email</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/employer.htm" >Home Page</a>
<h3>Drafting mail to ${name} </h3>
<br/>
<br/>
	<form action="${contextPath}/employee/contact.htm" id="usrform" method="post">

		<table class="table">
			<tr>
				<td>To: </td>
				<td><input type ="text" name="to" value="${To}" readonly></td>
			</tr>
			
			<tr>
				<td>From: </td>
				<td><input type ="text" name = "from" value="${From}" readonly></td>
			</tr>
			
			<tr>
				<td>Subject:</td>
				<td><input type ="text" name = "subject" required></td>
			</tr>
			
			<tr>
				<td>Body:</td>
				<td><textarea  rows="4" cols="50" name = "body"  form="usrform" required> </textarea></td>
			</tr>
			
			<tr>
				<td></td>
				<td colspan="2"><input class = "btn btn-success" type="submit" value="Send" /></td>
			</tr>
		</table>

	</form>
</body>
</html>