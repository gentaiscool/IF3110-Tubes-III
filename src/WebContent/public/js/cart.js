function addToCart(quantity, id, desc, stock, price) {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if(stock < quantity){
		alert("stock is less than your request");
	} else{
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if(xmlhttp.responseText == -1){
					alert("stock is less than your request");
				} else{
					alert("cart updated by your request");
				}
			}
		}
		xmlhttp.open("GET", "Cart?quantity=" + quantity + "&id=" + id + "&desc="
				+ desc + "&stock=" + stock + "&price=" + price + "&type=1", true);
		xmlhttp.send();
	}
}

function deleteFromCart(id){
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if(xmlhttp.responseText == -1){
				alert("delete unsuccessfully");
			} else{
				alert("delete successfully");
			}
		}
	}
	xmlhttp.open("GET", "Cart?id="+ id + "&type=2", true);
	xmlhttp.send();
}

function buyCart(){
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if(xmlhttp.responseText == -1){
				alert("bought unsuccessfully");
			} else if(xmlhttp.responseText == 0){
				alert("bought not successful due stock reasons")
			} else{
				alert("bought successfully");
			}
		}
	}
	xmlhttp.open("GET", "Cart?type=3", true);
	xmlhttp.send();
}