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
<body class="container" alink=#FFFFFF vlink=#FFFFFF>
	<%@ page import="helloJsp.model.ModelInventori"%>
	<%@ include file="templates/header.jsp"%>
	<%@ page import="java.util.ArrayList"%>
	<%
		int idBarang = Integer.parseInt(request.getParameter("idBarang"));

		ModelInventori barang = new ModelInventori();
		ArrayList<ModelInventori> TabelBarang = (ArrayList<ModelInventori>) session.getAttribute("tabel");

		for(int i=0; i<TabelBarang.size(); i++){
			if(TabelBarang.get(i).getId_inventori() == idBarang){
				barang = TabelBarang.get(i);
				break;
			}
		}
		
		String category = "";
		int idKat = barang.getId_kategori();
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

		out.println("<div class='title'>KATEGORI</div>");
		out.println("<div class=\"detailBarang\">");
		out.println("<div class='segment' style='position:relative;top:10%;left:0%'>");
		out.println("<img width=300px height=300px src=public/img/" + category.toLowerCase() + "/" + barang.getGambar() + " alt=" + barang.getNama_inventori() + " width = 150 height=300>");
		out.println("</div>");
		out.println("<div class='segment'>");
		out.println("<h2>" + barang.getNama_inventori());
		out.println("</h2><p>" + barang.getDescription());
		out.println("</p><br>Harga: " + barang.getHarga());
		out.println("<br/>Stok: " + barang.getJumlah());
		if (session.getAttribute("user") != null){
			if (!session.getAttribute("user").equals("admin")){
				out.println("<br/><br/><textarea id='desc'></textarea>");
				out.println("<input type='number' id='jumlah' size=10 placeholder='jumlah'>");
				out.println("<button onClick=\"addToCart(document.getElementById('jumlah').value,"+barang.getId_inventori()+",document.getElementById('desc').value, "+barang.getJumlah()+","+barang.getHarga()+")\">beli</button>");
			}
		}
		out.println("</div>");
		out.println("</div>");
	%>
	<%@include file="templates/footer.jsp"%>
</body>
</html>