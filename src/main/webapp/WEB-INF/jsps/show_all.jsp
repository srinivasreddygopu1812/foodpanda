<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "core" %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html lang="en">
    
    
<!DOCTYPE html>
<html>
<head>

 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" >



<script type="text/javascript">
function deleteUser(mail,role) {
	alert(mail);
	alert(role);
	alert("deleteUser triggered");
	 document.forms[0].action="${pageContext.request.contextPath}/delete?mailId="+mail +"&role="+role;
	document.forms[0].method="post";
	document.forms[0].submit(); 
	
	alert("deleteUser exit");
	
}
function updateUser(mail,role) {
	alert(mail);
	alert("entering with  " +mail + "  into editView");
	document.form.action="${pageContext.request.contextPath}/editView?email="+mail +"&role="+role;
	document.form.method="post";
	document.form.submit();
	
	
	
}
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html lang="en">

</head>
<body>
<a href='<core:url value="/j_spring_security_logout"/>'>LogOut</a></br>

<font color="red">${errorMsg}</font>

<div class="container">

<font color="red"><u>All User Details:</u></font></br>

<form name="form">
<table class="table table-hover"  >
<tr>
<th  align="left"><font color="blue">Name</font> </th><th  align="left"><font color="blue">Email Id</font> </th> <th  align="left"> <font color="blue">Password</font> </th>  <th  align="left"><font color="blue">Mobile Number</font></th><th  align="left"><font color="blue">Role</font></th> 
</tr>
<core:forEach var="emp" items="${empList}" >
<tr>
<td>${emp.name}</td>
<td>${emp.emailId }</td>
<td>${emp.password }</td>
<td>${emp.mobile }</td>
<td>${emp.role }</td>
<td><input type="button" class="btn btn-danger" value="Delete" onclick="deleteUser('${emp.emailId }','${emp.role}')"></td>
<td><input type="button" class="btn btn-primary" value="Update" onclick="updateUser('${emp.emailId }','${emp.role }')"></td>
</tr>
</core:forEach>
</table>
</form>


</div>
</body>
</html>