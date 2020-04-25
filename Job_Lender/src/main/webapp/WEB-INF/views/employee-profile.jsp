<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidate Profile</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
	<link rel="stylesheet"
	href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<a class = "btn btn-primary" href="${contextPath}/user/employee.htm" >Home Page</a>
	<h3>Profile</h3>
	<br>
	<br>
	<form:form action="${contextPath}/employee/profile.htm" commandName="profile" enctype="multipart/form-data" method="post" >

		<table class="table">
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" readonly="true"/>
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" readonly="true" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email" size="30"
						type="email" readonly="true" /> <font color="red"><form:errors
							path="email" /></font></td>
			</tr>
			
			
			<tr>
				<td>Phone Number:</td>
				<td><form:input path="phoneNo" size="30" required="required" />
					<font color="red"><form:errors path="phoneNo" /></font></td>
			</tr>
			
			<tr>
				<td>Address:</td>
				<td><form:input path="address" size="30" required="required" />
					<font color="red"><form:errors path="address" /></font></td>
			</tr>
			
			
			<tr>
				<td>City:</td>
				<td><form:input path="city" size="30" required="required" />
					<font color="red"><form:errors path="city" /></font></td>
			</tr>
			
			<tr>
				<td>ZIP Code:</td>
				<td><form:input path="zip" size="30" required="required" />
					<font color="red"><form:errors path="zip" /></font></td>
			</tr>
			
			<tr>
				<td>Country:</td>
				<td><form:input path="country" size="30" required="required" />
					<font color="red"><form:errors path="country" /></font></td>
			</tr>
			
			<tr>
				<td>Upload Resume*</td>
				<td> <input type="file" name="resume" required="required" />
			</tr>
			
			<tr>
				<td>School:</td>
				<td><form:input path="school" size="30" required="required" />    
				<font color="red"><form:errors path="school" /></font></td>
			</tr>
	
			
			<tr>
				<td>Degree:</td>
				<td><form:input path="degree" size="30" required="required" />    
				<font color="red"><form:errors path="degree" /></font></td>
			</tr>
			
			
			<tr>
				<td>Major:</td>
				<td><form:input path="major" size="30" required="required" />    
				<font color="red"><form:errors path="major" /></font></td>
			</tr>
			
			<tr>
				<td>GPA:</td>
				<td><form:input path="GPA" size="30" required="required" />    
				<font color="red"><form:errors path="GPA" /></font></td>
			</tr>
			
			<tr>
				<td>Top Skills:</td>
				<td><form:input path="skills" size="30" required="required" />
					<font color="red"><form:errors path="skills" /></font></td>
			</tr>
			
			<tr>
				<td>LinkedIn:</td>
				<td><form:input path="linkedin" size="30" required="required" />
					<font color="red"><form:errors path="linkedin" /></font></td>
			</tr>
			
			<tr>
				<td>About Yourself:</td>
				<td><form:input path="about" size="30" required="required" />
					<font color="red"><form:errors path="about" /></font></td>
			</tr>
			
			<tr>
			<td></td>
				<td colspan="2"><input class = "btn btn-success" type="submit" value="Submit" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>