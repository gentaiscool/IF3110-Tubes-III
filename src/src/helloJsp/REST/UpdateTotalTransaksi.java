package helloJsp.REST;

import helloJsp.controller.DbConnector;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class UpdateTotalTransaksi extends HttpServlet {

	// UpdateBarang

	public UpdateTotalTransaksi() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String query = "";
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		Statement statement2;
		
		query = "SELECT * FROM pengguna WHERE username = '" + username + "';";

		ResultSet rs;
		try {
			statement2 = connection.createStatement();
			rs = statement2.executeQuery(query);
			if (rs.next()) {
				Integer total_transaksi = Integer.parseInt(rs.getString("total_transaksi"));
				query = "UPDATE pengguna SET total_transaksi = " + (total_transaksi + 1) + " WHERE username = '" + username + "';";
				statement2.executeUpdate(query);
				response.getWriter().print("{" + "\"status\": 500" + ",\"detail\": \"OK\"" + ",\"content\":\"success\"" + "}");
			} else {
				response.getWriter().print("{" + "\"status\": 200" + ",\"detail\": \"Error\"" + ",\"content\":\"not success\"" + "}");
			}
			response.getWriter().print("{" + "\"status\": 500" + ",\"detail\": \"OK\"" + ",\"content\":\"success\"" + "}");
		} catch (Exception X) {
			response.getWriter().print("{" + "\"status\": 200" + ",\"detail\": \"Error\"" + ",\"content\":\"not success\"" + "}");
		}

	}
}
