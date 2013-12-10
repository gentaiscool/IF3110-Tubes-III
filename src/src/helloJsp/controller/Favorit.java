package helloJsp.controller;

import helloJsp.model.ModelInventori;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Favorit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Favorit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		//HttpClient client = new DefaultHttpClient();
		//HttpGet request2 = new HttpGet("http://127.0.0.1:8080/Chintalian/GetFavorit");
		//HttpResponse response2 = client.execute(request2);
		
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from inventori order by count desc;");
			JSONObject jObj = new JSONObject();
			ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
			
			while(rs.next()){
				ModelInventori barang = new ModelInventori();
				barang.setId_inventori(rs.getInt("id_inventori"));
				barang.setId_kategori(rs.getInt("id_kategori"));
				barang.setNama_inventori(rs.getString("nama_inventori"));
				barang.setJumlah(rs.getInt("jumlah"));
				barang.setGambar(rs.getString("gambar"));
				barang.setDescription(rs.getString("description"));
				barang.setHarga(rs.getInt("harga"));
				TabelBarang.add(barang);
			}	
			session.removeAttribute("favorit");
			session.setAttribute("favorit", TabelBarang);
			
			response.getWriter().print("{" + "\"status\": 500" + ",\"detail\": \"OK\"" + ",\"content\":\"success\"" + "}");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("{" + "\"status\": 200" + ",\"detail\": \"OK\"" + ",\"content\":\"not success\"" + "}");
		}
		
		// setting session to expiry in 30 mins
		session.setMaxInactiveInterval(10);
		//System.out.println("lol");
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs;
			rs = statement.executeQuery("select * from inventori order by count desc;");
			while (rs.next()) {
				ModelInventori barang = new ModelInventori();
				barang.setId_inventori(rs.getInt("id_inventori"));
				barang.setId_kategori(rs.getInt("id_kategori"));
				barang.setNama_inventori(rs.getString("nama_inventori"));
				barang.setJumlah(rs.getInt("jumlah"));
				barang.setGambar(rs.getString("gambar"));
				barang.setDescription(rs.getString("description"));
				barang.setHarga(rs.getInt("harga"));
				TabelBarang.add(barang);
			}
			session.removeAttribute("favorit");
			session.setAttribute("favorit", TabelBarang);
			// setting session to expiry in 30 mins
			// session.setMaxInactiveInterval(10);
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			out.println("Ch si");
			e.printStackTrace();
		}
	}

	private static String getResponse(WebResource service) {
		return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
	}

	private static String getOutputAsXML(WebResource service) {
		return service.accept(MediaType.TEXT_XML).get(String.class);
	}
}
