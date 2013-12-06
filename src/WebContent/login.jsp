<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="public/css/style.css">
<title>Login</title>
</head>
<body>
<form id="loginform" method="post" action="Login">
		<strong><h2>Login</h2></strong><br>
		<pre>Username			<input type="text" name="username" placeholder="username" id="username" /><span id="validasiUser"></span></pre>
		<pre>Password			<input type="password" name="password" placeholder="password" id="password" /><span id="validasiPass"></span></pre>
		<input type="submit" value="Login"> <a href='index.jsp'>Kembali</a></form>	
</body>
</html>