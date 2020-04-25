<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Job Lender</title>
<link rel="stylesheet" href="<c:url value="/resources/css/Event.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/> ">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  
<body >

<nav class="navbar navbar-dark bg-dark" style="background-color: 42403F;">
<a class = "btn btn-success" href="user/login.htm">User Login</a>
<a class = "btn btn-info" href="employer/jobs/viewalljobs.htm">View all jobs </a>
<h2><font color='white'>Job Lender: A step towards a successful future</font></h2>
</nav>

 <br>
<div class="w3-content w3-display-container">
<img  class="mySlides" src="<c:url value="/resources/css/Image/homeimg1.jpg"/> ">
<img  class="mySlides" src="<c:url value="/resources/css/Image/homeimg2.jpg"/> "> 
<img  class="mySlides" src="<c:url value="/resources/css/Image/homeimg3.jpg"/> ">
</div>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
  
<script>
var myIndex = 0;
carousel();

function carousel() {
  var i;
  var x = document.getElementsByClassName("mySlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  myIndex++;
  if (myIndex > x.length) {myIndex = 1}    
  x[myIndex-1].style.display = "block";  
  setTimeout(carousel, 2000); // Change image every 2 seconds
}
</script>  

</body>
</html>

