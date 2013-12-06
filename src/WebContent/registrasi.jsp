<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="public/css/style.css">
<script src="public/js/registrasi.js"></script>
<title>Registrasi</title>

</head>
<body>
<form id="registerform" method="post" action="Registrasi">
		<strong><h2>Sign up</h2></strong><br>
		<pre>(*) Harus diisi.</pre>
		<pre>Username*			<input type="text" name="username" id="usnm" onblur="validate(usnm.value,2,pwd.value)" onblur="validate(usnm.value,2,pwd.value)"/><span id="validasiUser"></span></pre>
		<pre>Password*			<input type="password" name="password"id="pwd" onblur="validate(pwd.value,3,usnm.value)"/><span id="validasiPass"></span></pre>
		<pre>Confirm Password*		<input type="password" name="password"id="pwd2" onblur="validate(pwd2.value,4,pwd.value)" onblur="validate(pwd2.value,4,pwd.value)"/><span id="validasiCoPass"></span></pre>
		<pre>Nama Lengkap*			<input type="text" name="nama" id="nama" onblur="validate(nama.value,1,'budi')"  onblur="validate(nama.value,1,'budi')"/><span id="validasiNama"></span></pre>
		<pre>Nomor HP			<input type="text" name="nohp"></pre>
		<pre>Alamat				<input type="textarea" name="alamat"></pre>
		<pre>Provinsi			<input type="text" name="provinsi"></pre>
		<pre>Kota/Kabupaten			<input type="text" name="kota"></pre>
		<pre>Kode Pos			<input type="text" name="kodepos"></pre>
		<pre>Email*				<input type="text" name="email"id="email" onblur="validate(email.value,5,pwd.value)"/><span id="validasiEmail"></span></pre>
		<pre><input type="checkbox" name="setuju" id="cek" onclick="readysubmit(9,'1')">I love Indonesia!!!</pre>
		<input type="submit" value="Daftar" id="masuk" disabled> <a href='index.jsp'>Kembali</a></form>	
</body>
</html>