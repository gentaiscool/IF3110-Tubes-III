package helloJsp.controller;

import helloJsp.model.ModelInventori;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class Category
 */

public class EditBarang extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String DOMAIN = "http://127.0.0.1:8080/Chintalian";
	//private static String DOMAIN = "http://tokokita.ap01.aws.af.cm";
    static final String EDIT_BARANG = "/EditBarang";
	static final String GET_BARANG_ID = "/GetBarangId";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBarang() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	private static String getResponse(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
    }
 
    private static String getOutputAsXML(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(String.class);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idBarang = Integer.parseInt(request.getParameter("idBarang"));
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out= response.getWriter();
		//ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
		out.println(idBarang);
		try{
			Statement statement = connection.createStatement();
		
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(DOMAIN);
			WebResource addService1 = service.path("rest").path(EDIT_BARANG+GET_BARANG_ID+"/"+idBarang);
			out.println(getOutputAsXML(addService1));
			
			//PARSING
			String JSONHasil = getOutputAsXML(addService1);
			JSONObject obj = new JSONObject(JSONHasil);
			int hasil = obj.getInt("hasil");
			
			
			int id_inventori = obj.getInt("id_inventori");
			int id_kategori = obj.getInt("id_kategori");
			String nama_inventori = obj.getString("nama_inventori");
			int jumlah = obj.getInt("jumlah");
			String gambar = obj.getString("gambar");
			String description = obj.getString("description");
			int harga = obj.getInt("harga");
			
			if(hasil == 1){
				out.println("OKEEE");
				ModelInventori barang = new ModelInventori();
				barang.setId_inventori(id_inventori);
				barang.setId_kategori(id_kategori);
				barang.setNama_inventori(nama_inventori);
				barang.setJumlah(jumlah);
				barang.setGambar(gambar);
				barang.setDescription(description);
				barang.setHarga(harga);
				
				session.setAttribute("barang", barang);
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(10);
				response.sendRedirect("editBarang.jsp?idBarang="+idBarang);
			}
			
		}catch(Exception e){
			out.println("Ch si");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idBarang = Integer.parseInt(request.getParameter("idBarang"));
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out= response.getWriter();
		//ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
		out.println(idBarang);
		try{
			Statement statement = connection.createStatement();
			
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(DOMAIN);
			WebResource addService1 = service.path("rest").path(EDIT_BARANG+GET_BARANG_ID+"/"+idBarang);
			
			//PARSING
			String JSONHasil = getOutputAsXML(addService1);
			JSONObject obj = new JSONObject(JSONHasil);
			int hasil = obj.getInt("hasil");
			
			int id_inventori = obj.getInt("id_inventori");
			int id_kategori = obj.getInt("id_kategori");
			String nama_inventori = obj.getString("nama_inventori");
			int jumlah = obj.getInt("jumlah");
			String gambar = obj.getString("gambar");
			String description = obj.getString("description");
			int harga = obj.getInt("harga");
			
			if(hasil == 1){
				ModelInventori barang = new ModelInventori();
				barang.setId_inventori(id_inventori);
				barang.setId_kategori(id_kategori);
				barang.setNama_inventori(nama_inventori);
				barang.setJumlah(jumlah);
				barang.setGambar(gambar);
				barang.setDescription(description);
				barang.setHarga(harga);
				
				session.setAttribute("barang", barang);
				response.sendRedirect("editBarang.jsp?idBarang="+idBarang);
			}
			
		}catch(Exception e){
			out.println("Ch si");
			e.printStackTrace();
		}
	}

}
