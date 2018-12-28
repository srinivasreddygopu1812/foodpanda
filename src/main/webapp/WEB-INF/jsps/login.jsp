<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<font color="red"><h3>${msg}</h3></font>
<form action="<c:url value='${pageContext.request.contextPath}/j_spring_security_check' />" method='POST'>
<font color="blue"><h2>User Login:</h2></font>
Emailid:<input type="email" name="emailId" value="${user.emailId }"/></br>
Password:<input type="password" name="password" value="${user.password }"/></br>
<input type="submit" value="login">
</form>

</body>
</html>