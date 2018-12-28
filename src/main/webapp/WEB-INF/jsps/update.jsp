<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<font color="red">${msg}</font>
<form action="update" method="post">
<!-- Emailid:<input type="email" name="emailId" required="required"/>(You cant modify EmailId)</br> -->
Set your new Password<input type="password" name="password" required="required"/></br>
Enter Your New Mobile Number:<input type="text" name="mobile" required="required"></br>
<input type="submit" value="Update">
</form>


</body>
</html>