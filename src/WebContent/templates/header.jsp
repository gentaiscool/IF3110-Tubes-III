<div class="header">
	<div class="logo">
		<a href="./index.jsp"><img src="public/img/canopy.png" width="100%"></a>
	</div>
	<div class="navigation">
		<a href="Category?idPage=1&pagi=1">
			<div id="navigator">APPETIZER</div>
		</a>
		<a href="Category?idPage=2&pagi=1">
			<div id="navigator">PIZZA</div>
		</a>
		<a href="Category?idPage=3&pagi=1">
			<div id="navigator">PASTA</div>
		</a>
		<a href="Category?idPage=4&pagi=1">
			<div id="navigator">DESSERTS</div>
		</a>
		<a href="Category?idPage=5&pagi=1">
			<div id="navigator">BEVERAGES</div>
		</a>
	</div>
	<div class="sidebar">
		<div class="picture">
			<a href=ShoppingCart><img src="public/img/cart.png" width="90"></a>
		</div>
		<div class="picture">
			<a href="Profile"><img src="public/img/user.png" width="90"></a>
		</div>
		<div class="picture">
			<a href="registerCardForm.jsp"><img src="public/img/card.png" width="90"></a>
		</div>
	</div>
	<%
		//allow access only if session exists
		String user = null;
		String userName = null;
		String sessionID = null;
		if (session.getAttribute("user") == null) {
			//response.sendRedirect("index.jsp");
			%>
			<div class="words">
				<h3>
				Ciao! Welcome to Chintalian! :3
				</h3>
				<button onClick="location.href='login.jsp'">Login</button>
				<button onClick="location.href='registrasi.jsp'">Registrasi user</button>
			</div>
		<%
		} else{
			user = (String) session.getAttribute("user");		
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user"))
						userName = cookie.getValue();
					if (cookie.getName().equals("JSESSIONID"))
						sessionID = cookie.getValue();
				}
			}
			%>
			<div class="words">
				<h3>
				Hi
				<a class="light" href="./Profile"><%=userName%></a>, Welcome to Chintalian! :3
				</h3>
				<button onClick="location.href='Logout'">Logout</button>
			</div>
			<%
		}
	%>
	<script>
		function autoComplete(){
			
		}
	</script>
</div>