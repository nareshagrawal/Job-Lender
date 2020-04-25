<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Jobs</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
    </head>
    <body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<a class = "btn btn-primary" href="${contextPath}/user/employee.htm" >Home Page</a>
	<h3>Please Search your favorite job</h3>
    <br>
    <br>
        <form action="${contextPath}/employee/jobs/search.htm" method="post">
           <table class="table" >
           
           <tr>
            	<td>Enter Name</td> <td> <input type="text" name="inputtext" required /> </td>
           </tr>
           <tr>
            	 <td><label>Search Type:</label></td>
           		 <td><input type="radio" name="searchkey" value="company" checked="checked"> Company
            	 <input type="radio" name="searchkey" value="jobname"> Job Name
            	 <input type="radio" name="searchkey" value="jobid"> Job ID </td>
            </tr>
            <tr>
            	<td></td>
           		<td> <!--  <input type="hidden" name="action" value="searchuser"/>  -->
           		<input type="hidden" name="opt"  value="searchjob">
            	<input class = "btn btn-success" type="submit" name="search" value="Search Job"/> </td>
           </tr>
            
           </table>
        </form>
    </body>
</html>