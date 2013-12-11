package helloJsp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ShoppingCart extends HttpServlet {
	private static String DOMAIN = "http://127.0.0.1:8080/Chintalian";
	//private static String DOMAIN = "http://tokokita.ap01.aws.af.cm";
	public ShoppingCart() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		PrintWriter out = response.getWriter();

		if (session.getAttribute("user") != null) {
			String username = (String) session.getAttribute("user");

			HttpClient client = new DefaultHttpClient();
			HttpGet request2 = new HttpGet(DOMAIN+"/GetUser?data=" + username + "&type=4");
			System.out.println(DOMAIN+"/GetUser?data=" + username + "&type=4");
			HttpResponse response2 = client.execute(request2);

			// Get the response
			BufferedReader rds = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));

			String line = "";
			StringBuffer textView = new StringBuffer();
			while ((line = rds.readLine()) != null) {
				textView.append(line);
			}
			System.out.print(textView.toString());
			// parsing json
			JSONTokener jsonTokener = new JSONTokener(textView.toString());
			JSONObject jsonObject = new JSONObject(jsonTokener);
			Integer status = (Integer) jsonObject.get("status");

			if (status != 204) {
				String forward = "/registerCardForm.jsp";
				RequestDispatcher dispatch = getServletContext().getRequestDispatcher(forward);
				dispatch.forward(request, response);
			} else{
				String forward = "/viewCart.jsp";
				RequestDispatcher dispatch = getServletContext().getRequestDispatcher(forward);
				dispatch.forward(request, response);
			}
		} else{
			String forward = "/registrasi.jsp";
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
