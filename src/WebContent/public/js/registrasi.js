var list = [];
function readysubmit(no, val) {
	var isValid = 0;
	if (val == '0')
		list[no] = true;
	else
		list[no] = false;
	document.getElementById("masuk").disabled = true;
	for (var i = 1; i < 6; i++) {
		if (list[i])
			isValid++;
	}
	if (isValid == 5 && (document.getElementById("cek").checked))
		document.getElementById("masuk").disabled = false;
}

function validate(text, num, pas) {
	var xmlhttp;
	var validpic = '<img src="public/img/like.png" width="15" height="15"/>';
	var invalidpic = '<img src="public/img/unlike.png" width="15" height="15"/>';
	var wait = '<img src="public/img/ajaxLoader.gif" width="15" height="15"/>';
	var valid = false;
	var temp = "" + text;
	if (temp.length == 0) {
		if (num == 1)
			document.getElementById("validasiNama").innerHTML = "";
		else if (num == 2)
			document.getElementById("validasiUser").innerHTML = "";
		else if (num == 3)
			document.getElementById("validasiPass").innerHTML = "";
		else if (num == 4)
			document.getElementById("validasiCoPass").innerHTML = "";
		else if (num == 5)
			document.getElementById("validasiEmail").innerHTML = "";
		return;
	}
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			readysubmit(num, xmlhttp.responseText);
			//alert(xmlhttp.responseText);
			switch (num) {
			case 1:
				switch (xmlhttp.responseText) {
				case '0':
					document.getElementById("validasiNama").innerHTML = validpic;
					break;
				default:
					document.getElementById("validasiNama").innerHTML = invalidpic
							+ " (Nama harus terdiri dari karakter(A-Z)(a-z). Minimal 2 kata.)";
					break;
				}
				break;
			case 2:
				switch (xmlhttp.responseText) {
				case '0':
					document.getElementById("validasiUser").innerHTML = validpic;
					break;
				case '1':
					document.getElementById("validasiUser").innerHTML = invalidpic
							+ " (Username sudah pernah terdaftar. Coba cari yang lain.)";
					break;
				case '2':
					document.getElementById("validasiUser").innerHTML = invalidpic
							+ " (Username tidak boleh sama dengan password)";
					break;
				case '3':
					document.getElementById("validasiUser").innerHTML = invalidpic
							+ " (Username minimal 5 karakter)";
					break;
				}
				break;
			case 3:
				switch (xmlhttp.responseText) {
				case '0':
					document.getElementById("validasiPass").innerHTML = validpic;
					break;
				case '1':
					document.getElementById("validasiPass").innerHTML = invalidpic
							+ " (Password tidak boleh sama dengan username)";
					break;
				case '2':
					document.getElementById("validasiPass").innerHTML = invalidpic
							+ " (Password minimal 8 karakter)";
					break;
				}
				break;
			case 4:
				switch (xmlhttp.responseText) {
				case '0':
					document.getElementById("validasiCoPass").innerHTML = validpic;
					break;
				case '1':
					document.getElementById("validasiCoPass").innerHTML = invalidpic
							+ " (Silakan isi 'Confirm Password' sama dengan Password awal)";
					break;
				}
				break;
			case 5:
				switch (xmlhttp.responseText) {
				case '0':
					document.getElementById("validasiEmail").innerHTML = validpic;
					break;
				case '1':
					document.getElementById("validasiEmail").innerHTML = invalidpic
							+ " (Invalid Email)";
					break;
				}
				break;
			}
		} else {
			switch (num) {
			case 1:
				document.getElementById("validasiNama").innerHTML = wait;
				break;
			case 2:
				document.getElementById("validasiUser").innerHTML = wait;
				break;
			case 3:
				document.getElementById("validasiPass").innerHTML = wait;
				break;
			case 4:
				document.getElementById("validasiCoPass").innerHTML = wait;
				break;
			case 5:
				document.getElementById("validasiEmail").innerHTML = wait;
				break;
			}
		}
	}
	xmlhttp.open("GET", "Validasi?q=" + temp + "&num=" + num + "&pass=" + pas,
			true);
	xmlhttp.send();
}
