package helloJsp.controller;

import helloJsp.object.Item;
import helloJsp.object.ShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class Cart extends HttpServlet {

	static final String REST_URI = "http://localhost:8080/Chintalian";
    static final String INCH_TO_FEET = "/Genta/InchToFeet/";
    static final String FEET_TO_INCH = "/Genta/FeetToInch/";
	
	public Cart() {
		// TODO Auto-generated constructor stub
		super();
	}

	private static String getResponse(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
    }
 
    private static String getOutputAsXML(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(String.class);
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int quantity = 0, idItem = 0, stock = 0, price = 0, type = 0;
		String desc = "";

		type = Integer.parseInt(request.getParameter("type"));
		HttpSession session = request.getSession();

		if (type == 1) {
			quantity = Integer.parseInt(request.getParameter("quantity"));
			idItem = Integer.parseInt(request.getParameter("id"));
			desc = request.getParameter("desc");
			stock = Integer.parseInt(request.getParameter("stock"));
			price = Integer.parseInt(request.getParameter("price"));

			

			
			if (stock < quantity) {
				// fail
				out.println(-1);
			} else {
				try {
					ShoppingCart sc = new ShoppingCart();
					if (session.getAttribute("shoppingCart") != null)
						sc = (ShoppingCart) session.getAttribute("shoppingCart");
					sc.getItems().add(new Item(quantity, idItem, price, desc));
					if (session.getAttribute("shoppingCart") != null)
						session.removeAttribute("shoppingCart");
					session.setAttribute("shoppingCart", sc);
					out.println(1);
				} catch (Exception x) {
					out.println(0);
				}
			}
		} else if (type == 2) {
			ShoppingCart sc = new ShoppingCart();
			idItem = Integer.parseInt(request.getParameter("id"));
			if (session.getAttribute("shoppingCart") != null) {
				sc = (ShoppingCart) session.getAttribute("shoppingCart");
				sc.getItems().remove(idItem);
				if (session.getAttribute("shoppingCart") != null)
					session.removeAttribute("shoppingCart");
				session.setAttribute("shoppingCart", sc);
				out.println(idItem);
			} else
				out.println(-1);
		} else if (type == 3) {
			ShoppingCart sc = new ShoppingCart();
			boolean transactionFinished = false;
			if (session.getAttribute("shoppingCart") != null) {
				sc = (ShoppingCart) session.getAttribute("shoppingCart");
				ArrayList<Integer> arr = new ArrayList<Integer>();
				for (int i = 0; i < sc.getItems().size(); i++) {
					// syntax
					DbConnector dbconnector = new DbConnector();
					Connection connection = dbconnector.mySqlConnection();
					try {
						Statement statement = connection.createStatement();
						String query = "SELECT * FROM inventori WHERE id_inventori=" + sc.getItems().get(i).getIdItem();
						ResultSet rs = statement.executeQuery(query);

						if (rs.next()) {
							Integer jumlah = Integer.parseInt(rs.getString("jumlah"));
							if (jumlah >= sc.getItems().get(i).getQuantity()) {
								query = "UPDATE inventori SET jumlah =" + (jumlah - sc.getItems().get(i).getQuantity()) + " WHERE id_inventori=" + sc.getItems().get(i).getIdItem();
								statement.executeUpdate(query);

								arr.add(i);
							} else {
								break;
							}
						} else {
							break;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String query = "";
					if (i == sc.getItems().size() - 1)
						transactionFinished = true;
				}
				if (!transactionFinished) {
					for (int j = 0; j < arr.size(); j++) {
						sc.getItems().remove(arr.get(j));
					}
				}
				if (session.getAttribute("shoppingCart") != null)
					session.removeAttribute("shoppingCart");
				if (transactionFinished) {
					String query = "";
					DbConnector dbconnector = new DbConnector();
					Connection connection = dbconnector.mySqlConnection();
					Statement statement2;

					String username = "genta";
					query = "SELECT * FROM pengguna WHERE username = '" + username + "';";
					
					ResultSet rs;
					try {
						statement2 = connection.createStatement();
						rs = statement2.executeQuery(query);
						if (rs.next()) {
							Integer total_transaksi = Integer.parseInt(rs.getString("total_transaksi"));
							query = "UPDATE pengguna SET total_transaksi = " + (total_transaksi + 1) + " WHERE username = '" + username + "';";
							statement2.executeUpdate(query);
							out.println(1);
						} else{
							out.println(0);
						}
						out.println(1);
					} catch (Exception X) {
						out.println(-1);
					}
				} else
					out.println(0);
			} else {
				out.println(-1);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
