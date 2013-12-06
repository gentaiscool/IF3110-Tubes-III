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
	<%@include file="templates/header.jsp"%>
	
	<%
		out.println("<div class=\"barang\">");
		out.println("<form action='UpdateBarang?type=2' method='get'>");
		out.println("<input type='hidden' name='idBarang' value=-1>");
		out.println("<input type='hidden' name='type' value=2>");
		out.println("<br/><pre>Gambar : </pre><input type='text' id='gambar' name='gambar'>");
		out.println("<br/><pre>Nama : </pre><input type='text' id='nama' name='nama'>");
		out.println("<br/><pre>Kategori : </pre><input type='number' id='kategori' name='kategori'>");
		out.println("<br/><pre>Harga : </pre><input type='number' id='harga' name='harga'>");
		out.println("<br/><pre>Jumlah : </pre><input type='number' id='jumlah' name='jumlah'>");
		out.println("<br/><pre>Deskripsi : </pre><textarea id='description' name='description'></textarea>");
		out.println("<input type='submit' value='add'></form>");
		out.println("<button onClick=\"location.href='newBarang.jsp'\">reset</button>");
		out.println("</div>");
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
