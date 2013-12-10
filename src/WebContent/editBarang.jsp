<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="public/css/style.css">
<title>Chintalian &#9733 &#9733 &#9733 &#9733 &#9733 dinner italian cuisine</title>
</head>
<body class="container" alink=#FFFFFF vlink=#FFFFFF>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="helloJsp.model.ModelInventori"%>
	<%
		//ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
			ModelInventori barang = new ModelInventori();
	%>
	<%@include file="templates/header.jsp"%>
	<%
		String s = request.getParameter("idBarang");
			int idBarang = Integer.parseInt(s);
	%>
	<%
		barang = (ModelInventori) session.getAttribute("barang");
			
			int idKat = barang.getId_kategori();
			String category = "";
			if(idKat == 1)
		category = "APPETIZER";
			else if(idKat == 2)
		category = "PIZZA";
			else if(idKat == 3)
		category = "PASTA";
			else if(idKat == 4)
		category = "DESSERTS";
			else if(idKat == 5)
		category = "BEVERAGES";
			out.println("<h3>"+barang.getNama_inventori()+"</h3>");
			
			//for (int i=0; i<TabelBarang.size(); i++){
		//if (TabelBarang.get(i).getId_kategori() == idKat){
			out.println("<div class=\"barang\">");
			out.println("<form action='UpdateBarang?idBarang="+idBarang+"&type=0' method='get'>");
			out.println("<input type='hidden' name='idBarang' value="+idBarang+">");
			out.println("<input type='hidden' name='kategori' value="+idKat+">");
			out.println("<input type='hidden' name='type' value=0>");
			out.println("<img width=100px height=100px src=public/img/"+category.toLowerCase()+"/"+barang.getGambar()+" alt="+barang.getNama_inventori()+" width = 150 height=300>");
			out.println("<br/><pre>Gambar : </pre><input type='text' id='gambar' name='gambar' value='"+barang.getGambar()+"'>");
			out.println("<br/><pre>Nama : </pre><input type='text' id='nama' name='nama' value='"+barang.getNama_inventori()+"'>");
			out.println("<br/><pre>Harga : </pre><input type='number' id='harga' name='harga' value='"+barang.getHarga()+"'>");
			out.println("<br/><pre>Jumlah : </pre><input type='number' id='jumlah' name='jumlah' value='"+barang.getJumlah()+"'>");
			out.println("<br/><pre>Deskripsi : </pre><textarea id='description' name='description'>"+barang.getDescription()+"</textarea>");
			out.println("<input type='submit' value='update'></form>");
			out.println("<button onClick=\"location.href='UpdateBarang?idBarang="+idBarang+"&type=1'\">delete</button>");
			out.println("</div>");
		//}
			//}
	%>
	<script>
		
	<%String message = request.getParameter("msg");
		if (message != null)%>
		Alert(
	<%=message%>
		);
	</script>
	<%@include file="templates/footer.jsp"%>
</body>
</html>
