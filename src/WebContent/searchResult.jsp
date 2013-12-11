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
		int pagisearch = Integer.parseInt(request.getParameter("pagisearch"));
	%>
	<%
		out.println("<div class='leftbar'>");
		out.println("<h2>SEARCH RESULT</h2>");
	%>

	<%
		ArrayList<String> result = new ArrayList<String>();
		if (session.getAttribute("result") != null){
			result = (ArrayList<String>) session.getAttribute("result");
		}
	
		/*int size = result.size();
		for (int i = 0; i < size/5; i++) {
			out.print(result.get(i));
		}*/
		
		ArrayList<String> arr = new ArrayList<String>();
		int count = 0;
		int pagination_divide = 10;
		for (int i = 0; i < result.size(); i++) {
				count++;
				arr.add(result.get(i));
		}

		for(int i=pagination_divide * (pagisearch-1); i<pagination_divide*(pagisearch); i++){
			if(i>=count) break;
			out.println(arr.get(i));
		}
		int totals = count/pagination_divide;
		if(count % pagination_divide > 0) totals++;
		
		out.println("<br/><div class='pagination'>");
		for(int i = 1; i<=totals; i++){
			if(i == pagisearch)
				out.println("<b><strong> " + i + " </strong></b>");
			else
				out.println("<a href='searchResult.jsp?pagisearch="+i+"'> "+ i +" </a>");
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

</body>
</html>
