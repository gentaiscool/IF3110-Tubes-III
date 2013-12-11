<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="helloJsp.object.ShoppingCart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="public/css/style.css">
<script type="text/javascript" src="public/js/cart.js"></script>
<title>Chintalian &#9733 &#9733 &#9733 &#9733 &#9733 dinner italian cuisine</title>
</head>
<body class="container" alink=#000000 vlink=#000000>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="helloJsp.model.ModelInventori"%>
	<%
		ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
	%>
	<%@include file="templates/header.jsp"%>
	<%
		String s = request.getParameter("idPage");
		//int pagi = Integer.parseInt(request.getParameter("pagi"));
		int pagi = 1;
		int idKat = Integer.parseInt(s);
	%>
	<%
		TabelBarang = (ArrayList<ModelInventori>) session.getAttribute("tabel");

		String category = "";
		if (idKat == 1)
			category = "APPETIZER";
		else if (idKat == 2)
			category = "PIZZA";
		else if (idKat == 3)
			category = "PASTA";
		else if (idKat == 4)
			category = "DESSERTS";
		else if (idKat == 5)
			category = "BEVERAGES";

		out.println("<div class='leftbar'>");
		out.println("<h2>" + category + "</h2>");
	%>
	<div class="sort">
		<%
		/*
		String result = "";
		if (session.getAttribute("result") != null){
			result = (String) session.getAttribute("result");
			out.println(result);
		}
		*/
		
		out.println("<form action='Search' method='get'>");
		out.println("Search : <input type='text' size=50 name='item' onkeyup='autoComplete(this.value)' placeholder='find names, price, or category here :)'>");
		out.println("</form><br/>");
		out.println("<p id='searchresult'></p>");
		%>
		<button
			onClick="location.href='Category?idPage=<%out.print(idKat);%>&pagi=<%out.print(pagi); %>&type=1'">Sort
			by Nama</button>
		<button
			onClick="location.href='Category?idPage=<%out.print(idKat);%>&pagi=<%out.print(pagi); %>&type=2'">Sort
			by Harga</button>
		<% 
		if(session.getAttribute("user") != null){
			if (session.getAttribute("user").equals("admin")) {
				//out.print("<button onClick=\"location.href='UpdateBarang?type=2'\">add NEW item</button>");
				//out.println("<button onClick=\"location.href='DeleteSelected.jsp?idPage="+idKat+"'\">DELETE Multiple Items</button>");
				//out.print("<form id='formadmin' name='foradmin' action='UpdateBarang?idBarang="+0+"&type=3' onsubmit='return konfirmasidelete())'>");
				//out.print("<input type='submit' name='buttondelete' value='DELETE Selected Item'>");
			}
		}
		%>
		
		<br /> <br />
	</div>

	<%
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int count = 0;
		int pagination_divide = 5;
		for (int i = 0; i < TabelBarang.size(); i++) {
			if (TabelBarang.get(i).getId_kategori() == idKat) {
				count++;
				arr.add(i);
			}
		}
		
		
		if (session.getAttribute("user") != null) {
			if (session.getAttribute("user").equals("admin")) {
				out.println("<form action='UpdateBarang?idBarang="+3+"&type=3' method='get'>");
				for(int i=pagination_divide * (pagi-1); i<pagination_divide*(pagi); i++){
					if(i>=count) break;
					out.println("<div class=\"barang\">");
					out.println("<img width=100px height=100px src=public/img/" + category.toLowerCase() + "/" + TabelBarang.get(arr.get(i)).getGambar() + " alt=" + TabelBarang.get(arr.get(i)).getNama_inventori() + " width = 150 height=300>");
					out.println("<br/><a href=detailBarang.jsp?idBarang=" + TabelBarang.get(arr.get(i)).getId_inventori() + ">" + TabelBarang.get(arr.get(i)).getNama_inventori() + "</a><br>");
					out.println("Harga: " + TabelBarang.get(arr.get(i)).getHarga());
					out.println("<br/>Stok: " + TabelBarang.get(arr.get(i)).getJumlah());
					//out.println("<button onClick=\"location.href='EditBarang?idBarang=" + TabelBarang.get(arr.get(i)).getId_inventori() + "'\">EDIT</button>");	
				//	out.println("<form method=post action='Category?idPage="+idKat+"&pagi=1'>");	
						out.println("<input type='checkbox' name='cdelete' value= " + TabelBarang.get(arr.get(i)).getId_inventori() + ">");
						//out.println("<input type=submit value='CHECK'>");
					//out.println("</form>");
					out.println("</div>");
				}
				out.print("<br/><input type='submit' name='buttondelete' value='DELETE Selected Item'>");
				out.print("</form>");
			}else{
				for(int i=pagination_divide * (pagi-1); i<pagination_divide*(pagi); i++){
					if(i>=count) break;
					out.println("<div class=\"barang\">");
					out.println("<img width=100px height=100px src=public/img/" + category.toLowerCase() + "/" + TabelBarang.get(arr.get(i)).getGambar() + " alt=" + TabelBarang.get(arr.get(i)).getNama_inventori() + " width = 150 height=300>");
					out.println("<br/><a href=detailBarang.jsp?idBarang=" + TabelBarang.get(arr.get(i)).getId_inventori() + ">" + TabelBarang.get(arr.get(i)).getNama_inventori() + "</a><br>");
					out.println("Harga: " + TabelBarang.get(arr.get(i)).getHarga());
					out.println("<br/>Stok: " + TabelBarang.get(arr.get(i)).getJumlah());
					out.println("<br/><input id='num"+i+"' type='number' size=5 placeholder='jumlah'>");
					out.println("<input type='hidden' id='idPage' name='idPage' value=" + idKat + ">");
					out.println("<input type='submit' value='beli' onClick=\"addToCart(document.getElementById('num"+arr.get(i)+"').value,"+(arr.get(i)+1)+",'', "+TabelBarang.get(arr.get(i)).getJumlah()+","+TabelBarang.get(arr.get(i)).getHarga()+")\">");
					out.println("</div>");
				}
			}
		}else{
			for(int i=pagination_divide * (pagi-1); i<pagination_divide*(pagi); i++){
			if(i>=count) break;
			out.println("<div class=\"barang\">");
			out.println("<img width=100px height=100px src=public/img/" + category.toLowerCase() + "/" + TabelBarang.get(arr.get(i)).getGambar() + " alt=" + TabelBarang.get(arr.get(i)).getNama_inventori() + " width = 150 height=300>");
			out.println("<br/><a href=detailBarang.jsp?idBarang=" + TabelBarang.get(arr.get(i)).getId_inventori() + ">" + TabelBarang.get(arr.get(i)).getNama_inventori() + "</a><br>");
			out.println("Harga: " + TabelBarang.get(arr.get(i)).getHarga());
			out.println("<br/>Stok: " + TabelBarang.get(arr.get(i)).getJumlah());
			out.println("</div>");
			}
		}
		
		int totals = count/pagination_divide;
		if(count % pagination_divide > 0) totals++;
		
		out.println("<br/><div class='pagination'>");
		for(int i = 1; i<=totals; i++){
			if(i == pagi)
				out.println("<b><strong> " + i + " </strong></b>");
			else
				out.println("<a href='category.jsp?idPage="+idKat+"&pagi="+i+"'> "+ i +" </a>");
		}
		out.println("</div></div>");
	%>


	<div class="centerbar">
		<h2>YOUR CART</h2>
		<%
			int total = 0;
			if (session.getAttribute("shoppingCart") != null) {
				ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingCart");
				out.println("");
				for (int i = 0; i < sc.getItems().size(); i++) {
					int temp = 0;
					for (int j = 0; j < TabelBarang.size(); j++) {
						if(TabelBarang.get(j).getId_inventori() == sc.getItems().get(i).getIdItem()){
							temp = j;
							break;
						}
					}
					out.println("(" + (i + 1) + ") " + sc.getItems().get(i).getQuantity() + "x " + TabelBarang.get(temp).getNama_inventori()+ " Rp. "+sc.getItems().get(i).getQuantity()*sc.getItems().get(i).getPrice() +",- <button onClick='deleteFromCart("+ (i) +")'>delete</button><br/>");
					
					if(sc.getItems().get(i).getDescription().equals("")){
						out.println("No special order<br/>");
					} else
						out.println("Special order : " + sc.getItems().get(i).getDescription());
					
					total += sc.getItems().get(i).getQuantity()*sc.getItems().get(i).getPrice();
				}
			}
			out.println("<br/><b>Total price: Rp. " + total+",-</b>");
		%>
	</div>

	<%@include file="templates/footer.jsp"%>
	<script>
		function autoComplete(str){
			var xmlhttp;
			if (str.length==0)
			  { 
			  document.getElementById("searchresult").innerHTML="";
			  return;
			  }
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
				{
				document.getElementById("searchresult").innerHTML=xmlhttp.responseText;
				}
			  }
			xmlhttp.open("GET","Search?item="+str,true);
			xmlhttp.send();
		}
	</script>
	<script>
	function konfirmasidelete(){
		var user_choice = window.confirm('Delete Selected Item?');
		if(user_choice==true) {
				alert("delete sukses");
			return true;
		} else {
			return false;
		}		
	}
	</script>
</body>
</html>