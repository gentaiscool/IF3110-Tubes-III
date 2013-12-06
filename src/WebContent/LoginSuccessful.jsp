<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<meta http-equiv="Refresh" content="5; url=/Chintalian/index.jsp">
<link rel="stylesheet" type="text/css" href="public/css/style.css">
<title>Login Success Page</title>
</head>
<body>
<h1 id="loginreport">Login successful! wait in <span id="yea">5</span> seconds</h1>
<script>
	var k =4;
	setInterval(function(){document.getElementById("yea").innerHTML = ""+k;k--;},1000);
</script>
</body>
</html>