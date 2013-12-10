package helloJsp.REST;

import helloJsp.controller.DbConnector;
import helloJsp.model.ModelInventori;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class CheckCardValidation extends HttpServlet {

	// UpdateBarang

	public CheckCardValidation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean bcardnum, bname;
		int cardnum = Integer.parseInt(request.getParameter("cardnum").toString());
		String user = request.getParameter("user");
		String query, regex, names = request.getParameter("names");
		names = names.replace('*', ' ');
		String expired = request.getParameter("expired");
		
		DbConnector dbconnector = new DbConnector();
		HttpSession session = request.getSession();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			bcardnum = (((cardnum%1000)%123)==1 && (cardnum/10000)>0);
			query = "SELECT * FROM pengguna WHERE username='"
					+ user + "'";
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
				response.getWriter().print("{"
						+ "\"status\": 500"
						+ ",\"detail\": \"OK\""
						+ ",\"content\":\"success\""
						+ "}");
			} else {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				response.getWriter().print("{"
						+ "\"status\": 200"
						+ ",\"detail\": \"Error\""
						+ ",\"content\":\"not success\""
						+ "}");
			}
		} catch (Exception e) {
			response.getWriter().print("{"
					+ "\"status\": 200"
					+ ",\"detail\": \"Error\""
					+ ",\"content\":\"not success\""
					+ "}");
		}
	}
}
