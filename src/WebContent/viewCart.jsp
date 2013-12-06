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
	<%@include file="templates/header.jsp"%>

	<%
		ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
		TabelBarang = (ArrayList<ModelInventori>) session.getAttribute("tabel");
	%>
	<div class="fullbar">
		<h2>YOUR CART</h2>
		<%
			if(session.getAttribute("card") == null){
				if(session.getAttribute("user") == null)
					response.sendRedirect("registrasi.jsp");
				else
					response.sendRedirect("registerCardForm.jsp");
			}
		
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
					out.println("(" + (i + 1) + ") <input type='number' value=" + sc.getItems().get(i).getQuantity() + " size=5 > " + TabelBarang.get(temp).getNama_inventori()+ " Rp. "+sc.getItems().get(i).getQuantity()*sc.getItems().get(i).getPrice() +",- <button onClick='deleteFromCart("+ (i) +")'>delete</button><br/>");
					if(sc.getItems().get(i).getDescription().equals("")){
						out.println("No special order<br/>");
					} else
						out.println("Special order : " + sc.getItems().get(i).getDescription());
					total += sc.getItems().get(i).getQuantity()*sc.getItems().get(i).getPrice();
				}
			}
			out.println("<br/><b>Total price: Rp. " + total+",-</b>");
			
			out.println("<button onClick=buyCart()>buy</button>");
		%>
	</div>
	</div>
	<%@include file="templates/footer.jsp"%>
</body>
</html>