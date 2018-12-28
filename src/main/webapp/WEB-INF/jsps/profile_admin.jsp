<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<font color="red">Your Details are:</font> </br>
	EmailId:${empDetails.emailId }
	</br> password:${empDetails.password}
	</br> MobileNumber:${empDetails.mobile}
	</br> Name:${empDetails.name}</br>
	
	<font color="red"><h1>Hello Admin</h1></font>

	<form action="${pageContext.request.contextPath}/showall">
		<input type="submit"value="Show All User Details">
	</form>

</body>
</html>