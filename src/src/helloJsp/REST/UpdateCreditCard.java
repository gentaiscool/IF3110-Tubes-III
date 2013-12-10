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

public class UpdateCreditCard extends HttpServlet {

	// UpdateBarang

	public UpdateCreditCard() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer quantity = Integer.parseInt(request.getParameter("quantity").toString());
		Integer idItem = Integer.parseInt(request.getParameter("idItem").toString());
		String desc = request.getParameter("desc");
		Integer price = Integer.parseInt(request.getParameter("price").toString());

		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM inventori WHERE id_inventori=" + idItem;
			ResultSet rs = statement.executeQuery(query);

			if (rs.next()) {
				Integer jumlah = Integer.parseInt(rs.getString("jumlah"));
				if (jumlah >= quantity) {
					query = "UPDATE inventori SET jumlah =" + (jumlah - quantity) + " WHERE id_inventori=" + idItem;
					statement.executeUpdate(query);
					response.getWriter().print("{" + "\"status\": 500" + ",\"detail\": \"OK\"" + ",\"content\":\"success\"" + "}");
				} else {
					response.getWriter().print("{" + "\"status\": 200" + ",\"detail\": \"OK\"" + ",\"content\":\"not success\"" + "}");
				}
			} else {
				response.getWriter().print("{" + "\"status\": 200" + ",\"detail\": \"OK\"" + ",\"content\":\"not success\"" + "}");
			}
		} catch (Exception X) {
			response.getWriter().print("{" + "\"status\": 200" + ",\"detail\": \"OK\"" + ",\"content\":\"not success\"" + "}");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
