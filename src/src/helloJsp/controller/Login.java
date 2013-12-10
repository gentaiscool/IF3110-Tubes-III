package helloJsp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String REST_URI = "http://localhost:8080/Chintalian";
	static final String GET_USER = "/GetUser";
	static final String LOGIN = "/login";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private static String getResponse(WebResource service) {
		return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
	}

	private static String getOutputAsXML(WebResource service) {
		return service.accept(MediaType.TEXT_XML).get(String.class);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		try {
			Statement statement = connection.createStatement();

			HttpClient client = new DefaultHttpClient();
			HttpGet request2 = new HttpGet("http://127.0.0.1:8080/Chintalian/GetUser?username=" + username + "&password=" + password + "&type=2");
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
			JSONObject content = (JSONObject) jsonObject.get("content");
			Integer status = (Integer) jsonObject.get("status");

			if (status == 500) {
				Integer id_pengguna = Integer.parseInt((content.get("id_pengguna").toString()));
				String nama_pengguna = (String) content.get("nama_pengguna");

				session.setAttribute("user", username);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(1800);
				Cookie userName = new Cookie("user", username);
				// userName.setMaxAge(30*60);
				response.addCookie(userName);
				response.sendRedirect("LoginSuccessful.jsp");
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(request, response);
			}
		} catch (Exception e) {
			out.println("Ch si");
			e.printStackTrace();
		}
	}
}
