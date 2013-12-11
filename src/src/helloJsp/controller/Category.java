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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Servlet implementation class Category
 */

public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String DOMAIN = "http://127.0.0.1:8080/Chintalian";
	//private static String DOMAIN = "http://tokokita.ap01.aws.af.cm";
    static final String GET_BARANG = "/GetBarang";
    static final String KATEGORI = "/kategori";
    static final String PRICE = "/sortByPrice";
    static final String NAME = "/sortByName";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static String getOutputAsXML(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(String.class);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idKat = Integer.parseInt(request.getParameter("idPage"));
		int pagi = Integer.parseInt(request.getParameter("pagi"));
		
		int type = 0;
		if (request.getParameter("type") != null) 
			type = Integer.parseInt(request.getParameter("type"));
		
		HttpSession session = request.getSession();
		PrintWriter out= response.getWriter();
		ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
		out.println(idKat);
		try {
			ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        WebResource service = client.resource(DOMAIN);
	        WebResource addServiceKategori = service.path("rest").path(GET_BARANG+KATEGORI+"/"+idKat);
	        WebResource addServiceSortHarga = service.path("rest").path(GET_BARANG+PRICE+"/"+idKat);
	        WebResource addServiceSortNama = service.path("rest").path(GET_BARANG+NAME+"/"+idKat);

	        //out.println(getOutputAsXML(addServiceSortHarga));
	        //out.println(getOutputAsXML(addServiceSortNama));
			
	        // Parsing
	        String JSONBarang = " ";
	        if (type == 0) JSONBarang = getOutputAsXML(addServiceKategori);
	        else if (type == 1) JSONBarang = getOutputAsXML(addServiceSortNama);
	        else if (type == 2) JSONBarang = getOutputAsXML(addServiceSortHarga);
	        
	        JSONObject obj = new JSONObject(JSONBarang);
	        JSONArray arr = obj.getJSONArray("content");
	        for (int i = 0; i < arr.length(); i++) {
	        	ModelInventori barang = new ModelInventori();
	        	barang.setId_inventori(arr.getJSONObject(i).getInt("id_inventori"));
				barang.setId_kategori(arr.getJSONObject(i).getInt("id_kategori"));
				barang.setNama_inventori(arr.getJSONObject(i).getString("nama_inventori"));
				barang.setJumlah(arr.getJSONObject(i).getInt("jumlah"));
				barang.setGambar(arr.getJSONObject(i).getString("gambar"));
				barang.setDescription(arr.getJSONObject(i).getString("description"));
				barang.setHarga(arr.getJSONObject(i).getInt("harga"));
				TabelBarang.add(barang);
	        }
	        
	        session.removeAttribute("tabel");
			session.setAttribute("tabel", TabelBarang);
			
			if (type == 0) response.sendRedirect("category.jsp?idPage="+idKat+"&pagi="+pagi);
			else response.sendRedirect("category.jsp?idPage="+idKat+"&pagi="+pagi+"&type="+type);
		} catch(Exception e) {
			out.println("Ch si");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int idKat = Integer.parseInt(request.getParameter("idPage"));
		
		int type = 0;
		if (request.getParameter("type") != null) 
			type = Integer.parseInt(request.getParameter("type"));
		
		HttpSession session = request.getSession();
		PrintWriter out= response.getWriter();
		ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
		out.println(idKat);
		try {
			ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        WebResource service = client.resource(DOMAIN);
	        WebResource addServiceKategori = service.path("rest").path(GET_BARANG+KATEGORI+"/"+idKat);
	        WebResource addServiceSortHarga = service.path("rest").path(GET_BARANG+PRICE+"/"+idKat);
	        WebResource addServiceSortNama = service.path("rest").path(GET_BARANG+NAME+"/"+idKat);

	        //out.println(getOutputAsXML(addServiceSortHarga));
	        //out.println(getOutputAsXML(addServiceSortNama));
			
	        // Parsing
	        String JSONBarang = " ";
	        if (type == 0) JSONBarang = getOutputAsXML(addServiceKategori);
	        else if (type == 1) JSONBarang = getOutputAsXML(addServiceSortNama);
	        else if (type == 2) JSONBarang = getOutputAsXML(addServiceSortHarga);
	        
	        JSONObject obj = new JSONObject(JSONBarang);
	        JSONArray arr = obj.getJSONArray("content");
	        for (int i = 0; i < arr.length(); i++) {
	        	ModelInventori barang = new ModelInventori();
	        	barang.setId_inventori(arr.getJSONObject(i).getInt("id_inventori"));
				barang.setId_kategori(arr.getJSONObject(i).getInt("id_kategori"));
				barang.setNama_inventori(arr.getJSONObject(i).getString("nama_inventori"));
				barang.setJumlah(arr.getJSONObject(i).getInt("jumlah"));
				barang.setGambar(arr.getJSONObject(i).getString("gambar"));
				barang.setDescription(arr.getJSONObject(i).getString("description"));
				barang.setHarga(arr.getJSONObject(i).getInt("harga"));
				TabelBarang.add(barang);
	        }
	        
	        session.removeAttribute("tabel");
			session.setAttribute("tabel", TabelBarang);
			
			if (type == 0) response.sendRedirect("category.jsp?idPage="+idKat);
			else response.sendRedirect("category.jsp?idPage="+idKat+"&type="+type);
		} catch(Exception e) {
			out.println("Ch si");
			e.printStackTrace();
		}
		
		/* INI YANG VERSI LAMA, MAU NGEHAPUS TAKUT SALAH >.< DIJADIIN KOMEN DULU :3
		int idKat = Integer.parseInt(request.getParameter("idPage"));
		int type = 0;
		if (request.getParameter("type") != null) type = Integer.parseInt(request.getParameter("type"));

		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out= response.getWriter();
		ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
		out.println(idKat);
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = null;
			out.println("typeku: " + type);
			if (type == 0) rs = statement.executeQuery("select * from inventori;");
			else if (type == 1) rs = statement.executeQuery("select * from inventori order by nama_inventori;");
			else if (type == 2) rs = statement.executeQuery("select * from inventori order by harga;");
			out.println("typeku: " + type);
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
				out.println("he");
			}	
			out.println("ch ga si");
			session.removeAttribute("tabel");
			session.setAttribute("tabel", TabelBarang);
			//setting session to expiry in 30 mins
			//session.setMaxInactiveInterval(10);
			response.sendRedirect("category.jsp?idPage="+idKat);
			
		}catch(Exception e){
			out.println("Ch si");
			e.printStackTrace();
		}*/
	}

}
