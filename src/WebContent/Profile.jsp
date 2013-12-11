<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="public/css/style.css">
<script type="text/javascript" src="public/js/registrasi.js"></script>
<title>Profile Page</title>

<%@ page import="java.util.ArrayList"%>

<script>
	function updateprofile(nama, uname, pwd, email, nohp, alamat, provinsi,
			kota, kodepos) {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				alert("Data :\n" + xmlhttp.responseText
						+ "tidak berubah. Data berhasil disimpan.");
				location.replace("index.jsp");
			}
		}
		xmlhttp.open("GET", "UpdateProfile?nama2=" + nama + "&uname=" + uname
				+ "&pwd2=" + pwd + "&email2=" + email + "&nohp2=" + nohp
				+ "&kodepos2=" + kodepos + "&kota2=" + kota + "&provinsi2="
				+ provinsi, true);
		xmlhttp.send();

	}

	function editprofile(temp) {
		//document.getElementById("heads").innerHTML="<form id='updateform' method='post' action='UpdateProfile'>";
		document.getElementById("sname").innerHTML = "<input name='nama2' value='${user.getName()}' type='text' id='nama2' onblur='validate(nama2.value,1,nohp2.value)'></input><span id='validasiNama2'></span>";
		document.getElementById("suser").innerHTML = "<input id='uname' value='${user.getUsername()}' readonly></input>";
		document.getElementById("spassword").innerHTML = "<input name='pwd2' type='password' value='${user.getPassword()}' id='pwd2' onblur='validate(pwd2.value,3,uname.value)'></input><span id='validasiPass2'></span>";
		document.getElementById("snohp").innerHTML = "<input name='nohp2' id='nohp2' value='${user.getNohp()}'></input>";
		document.getElementById("salamat").innerHTML = "<input id='alamat2' name='alamat2' value='${user.getAlamat()}'></input>";
		document.getElementById("sprovinsi").innerHTML = "<input id='provinsi2' name='provinsi2' value='${user.getProvinsi()}'></input>";
		document.getElementById("skota").innerHTML = "<input id='kota2' name='kota2' value='${user.getKota()}'></input>";
		document.getElementById("skodepos").innerHTML = "<input id='kodepos2' name='kodepos2' value='${user.getKodepos()}'>";
		document.getElementById("semail").innerHTML = "<input name='email2' value='${user.getEmail()}' id='email2' onblur='validate(email2.value,5,pwd2.value)'></input><span id='validasiEmail2'></span>";
		document.getElementById("edits").innerHTML = "<button onClick='updateprofile(nama2.value,uname.value,pwd2.value,email2.value,nohp2.value,alamat2.value,provinsi2.value,kota2.value,kodepos2.value)'>Save</button><a href='index.jsp'>Cancel</a>";
	}
</script>
</head>
<body>

	<h2 id="headername"></h2>
	<div id="info"></div>
	<span id="foto"><img src='public/img/minion.jpg'></span>
	<span id="heads"></span>
	<pre id="nama">Nama Lengkap 		:<span id="sname">${user.getName()}</span>
	</pre>
	<pre id="usernamep">Username		:<span id="suser">${user.getUsername()}</span>
	</pre>
	<pre id="pass">Password		:<span id="spassword">${user.getPassword()}</span>
	</pre>
	<pre id="nomorhp">Nomor Hp		:<span id="snohp">${user.getNohp()}</span>
	</pre>
	<pre id="alamat">Alamat			:<span id="salamat">${user.getAlamat()}</span>
	</pre>
	<pre id="provinsi">Provinsi		:<span id="sprovinsi">${user.getProvinsi()}</span>
	</pre>
	<pre id="kota">Kota/Kabupaten		:<span id="skota">${user.getKota()}</span>
	</pre>
	<pre id="kodepos">Kode Pos		:<span id="skodepos">${user.getKodepos()}</span>
	</pre>
	<pre id="email">Email			:<span id="semail">${user.getEmail()}</span>
	</pre>
	<span id="edits"><button id="editprof"
			onClick="editprofile('${user.getUsername()}')">Edit Profil</button>
		<button onClick="location.href='index.jsp'">Cancel</button></span>
	<span id="tails"></span>
</body>
</html>