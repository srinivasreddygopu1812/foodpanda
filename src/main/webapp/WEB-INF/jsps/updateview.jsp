<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<font color="red">${errorMsg}</font> 
<form action="${pageContext.request.contextPath}/updateView" method="post">
Emailid:<input type="email" value="${empList.emailId}" readonly="readonly" name="emailId"/></br>
Password:<input type="text" name="password" value="${empList.password}" name="password"/></br>
Mobile Number:<input type="text" name="mobile"  value="${empList.mobile}" name="mobile" /></br>
Name:<input type="text" name="name" value="${empList.name}" readonly="readonly"  name="name"/></br>
<input type="hidden" name="role" value="${empList.role}" >
<input type="hidden" name="version" value="${empList.version}" /></br>
<input type="submit" value="updateView">
</form>


</body>
</html>