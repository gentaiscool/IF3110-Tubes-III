package helloJsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {

	public ShoppingCart() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			String forward = "/registrasi.jsp";
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher(forward);
			dispatch.forward(request, response);
		} else {
			String forward = "/viewCart.jsp";
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher(forward);
			dispatch.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = "/viewCart.jsp";
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(forward);
		dispatch.forward(request, response);
	}

}
