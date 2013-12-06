<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="public/css/style.css">
<title>Pendaftaran Kartu Kredit</title></head>
<body>
	<table class="ds_box" cellpadding="0" cellspacing="0" id="ds_conclass" style="display: none;">
<tr><td id="ds_calclass">
</td></tr>
</table>
	<script src="public/js/Script.js"></script>
	
	<form id="registerform" method="post" action="Registercard" name="myform">
	<strong><h2>Pendaftaran Kartu Kredit</h2></strong><br>
	<p><%=session.getAttribute("user")%> bisa mendaftarkan kartu kredit sekarang atau nanti</p>
	<pre>Card Number	<input type="text" id="cn" name="cardnumber"></pre>
	<pre>Name on Card	<input type="text" id="noc" name="nama"></pre>
	<pre>Expired Date 	<input id="ed" onclick="ds_sh(this);" name="expired" readonly="readonly" style="cursor: text" /></pre>
	<input type="hidden" name="username" id="usernamep" value="">
	<div id="cek"><input type="button" value="Ok" onclick="cekkartu(noc.value,cn.value,ed.value)"> <a href="index.jsp">Skip</a></div>
	</form>
	<script>
	<% if(session.getAttribute("user") == null){ %>
		alert("BELUM ADA");
		var s = "<strong>Maaf, halaman ini tidak bisa diakses jika kamu belum login.</strong><br>";
		s += "<p>Halaman akan segera dialihkan ke halaman registrasi...</p>";
		document.getElementById("registerform").innerHTML = s;
	<%	
	}
	else{%>
			document.getElementById("usernamep").value=<%=session.getAttribute("user")%>;
		<%}%>
	
	function cekkartu(nama,cardnum,expired){
		var xmlhttp;
		if (cardnum.length==0||expired.length==0||nama.length==0){
			alert("Masih ada form yang belum Anda isi");
		}else{
			if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp=new XMLHttpRequest();
			}else{// code for IE6, IE5
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}	
			xmlhttp.onreadystatechange=function(){
				if (xmlhttp.readyState==4 && xmlhttp.status==200){
					if(xmlhttp.responseText=="valid"){
						alert("Selamat! Registrasi kartu berhasil!");
						location.replace("index.jsp");
					}else if(xmlhttp.responseText=="invalid"){
						alert("Pendaftaran kartu kredit gagal!\nNomor Kartu atau Nama Anda tidak valid");
					}else alert("exception!");
					document.getElementById("cek").innerHTML='<input type="button" value="Ok" onclick="cekkartu(noc.value,cn.value,ed.value);"> <a href="index.jsp">Skip</a>';
				}else{
					document.getElementById("cek").innerHTML="<img src='public/img/mini-loader.gif'> Harap tunggu! kami sedang memvalidasi kartu kredit Anda...";
				}
			}
			xmlhttp.open("GET","CardValidation?cardnumber="+cardnum+"&names="+nama+"&expired="+expired,true);
			xmlhttp.send();
		}
	}
	</script>
</body>
</html>