<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="formUser" name="formUser" method="post" action="user.jsp">
 User Level:
<input id="user_level" name="user_level" type="text"
 class="form-control form-control-sm">
<br> Email:
<input id="email" name="email" type="text"
 class="form-control form-control-sm">
<br> First Name:
<input id="fname" name="fname" type="text"
 class="form-control form-control-sm">
<br> Last Name:
<input id="lname" name="lname" type="text"
 class="form-control form-control-sm">
<br> Date Of Birth:
<input id="dob" name="dob" type="text"
 class="form-control form-control-sm">
<br> Address:
<input id="address" name="address" type="text"
 class="form-control form-control-sm">
<br> Telephone Number:
<input id="tp_number" name="tp_number" type="text"
 class="form-control form-control-sm"> 
<br> 
<input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>


</body>
</html>