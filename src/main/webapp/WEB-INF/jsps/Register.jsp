<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%-- ${pageContext.request.contextPath}/
 --%></head>
<body>
<font color="red"><b>${msg}</b></font></br>
<font color="red"><h2>Welcome to Foodpanda</h2></font></br>
<form action="register" method="post">

Emailid:<input type="email" name="emailId" value="${user.emailId }"/>
</br>
Password:<input type="password" name="password" value="${user.password }"/></br>
Mobile Number:<input type="text" name="mobile" value="${user.mobile }"/></br>
Name:<input type="text" name="name" value="${user.name }" ></br>
<input type="submit"  value="Register"/>
<%-- <input type="submit" disabled="${enableUpdate}" value="Update"/> --%>
</form>
<a href="${pageContext.request.contextPath}/goToLogin">Click here</a> to Login</br>
<a href="${pageContext.request.contextPath}/showall">Click here</a>to see all user and admin details
</body>
</html>