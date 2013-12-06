package helloJsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CardValidation
 */
@WebServlet("/CardValidation")
public class CardValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String names, regex, query, expired;
		int cardnum;
		boolean bcardnum = false, bname = false;
		cardnum = Integer.parseInt(request.getParameter("cardnumber"));
		names = request.getParameter("names");
		expired = request.getParameter("expired");
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			DbConnector dbconnector = new DbConnector();
			Connection connection = dbconnector.mySqlConnection();
			try {
				Statement statement = connection.createStatement();
				bcardnum = (((cardnum%1000)%123)==1 && (cardnum/10000)>0);
				query = "SELECT * FROM pengguna WHERE username='"
						+ session.getAttribute("user") + "'";
				ResultSet rs = statement.executeQuery(query);
				rs.next();
				regex = "([A-Za-z]{1,10})+(\\s[A-Za-z]{1,20})+";
				bname = Pattern.matches(regex, names);
				if (bname && bcardnum) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					String nokartu = ""+cardnum;
					session.setAttribute("card",nokartu);
					query = "UPDATE pengguna SET nomor_credit_card='"+cardnum+"',expired_date='"+expired+"',nama_on_card='"+names+"' WHERE username='"+ session.getAttribute("user") + "'";
					statement.executeUpdate(query);
					out.print("valid");
				} else {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					out.print("invalid");
				}
			} catch (Exception e) {
				out.print("EXCEPTION BROH");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
