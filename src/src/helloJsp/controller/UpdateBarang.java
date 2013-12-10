package helloJsp.controller;

import helloJsp.model.ModelInventori;
import helloJsp.SOAP.AddBarang;

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

public class UpdateBarang extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String REST_URI = "http://localhost:8080/Chintalian";
    static final String UPDATE_BARANG = "/UpdateBarang";
	static final String GET_BARANG = "/GetBarang";
	static final String GET_BARANG_ID = "/GetBarangId";
	static final String UPDATE_ALL = "/UpdateAll";
	static final String DELETE = "/Delete";
	static final String ADD_BARANG = "/AddBarang";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateBarang() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	private static String getResponse(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
    }
 
    private static String getOutputAsXML(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(String.class);
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean ok = true;
		int idBarang = 0, type = 0, harga = 0, jumlah = 0, kategori = 0;
		String nama = null, gambar = null, description = null;
		
		if (request.getParameter("kategori") != null)
			kategori = Integer.parseInt(request.getParameter("kategori"));
		else
			ok = false;

		if (request.getParameter("idBarang") != null)
			idBarang = Integer.parseInt(request.getParameter("idBarang"));
		else
			ok = false;
		if (request.getParameter("type") != null){
			type = Integer.parseInt(request.getParameter("type"));
			//System.out.println(type);
		}
		else
			ok = false;
		if (request.getParameter("nama") != null)
			nama = request.getParameter("nama");
		else
			ok = false;
		if (request.getParameter("gambar") != null)
			gambar = request.getParameter("gambar");
		else
			ok = false;
		if (request.getParameter("harga") != null)
			harga = Integer.parseInt(request.getParameter("harga"));
		else
			ok = false;
		if (request.getParameter("jumlah") != null)
			jumlah = Integer.parseInt(request.getParameter("jumlah"));
		else
			ok = false;
		if (request.getParameter("description") != null)
			description = request.getParameter("description");
		else
			ok = false;

		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		ModelInventori barang = (ModelInventori) session.getAttribute("barang");
		//out.print("nama1:"+barang.getNama_inventori());
		//out.print("nama2"+nama);
		// ArrayList<ModelInventori> TabelBarang = new
		// ArrayList<ModelInventori>();
		try {
			Statement statement = connection.createStatement();
			
			
			ResultSet rs = null;
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(REST_URI);
			if (type == 0) { // update
				if (ok) {
					if (!barang.getNama_inventori().equals(nama)) {
						WebResource addService1 = service.path("rest").path(UPDATE_BARANG+GET_BARANG+"/"+nama);
						
						//PARSING
						String JSONHasil = getOutputAsXML(addService1);
						JSONObject obj = new JSONObject(JSONHasil);
						int hasil = obj.getInt("hasil");
						
						if (hasil == 1){
							// nama udah ada
							response.sendRedirect("editBarang.jsp?idBarang=" + idBarang + "&msg='Nama sudah digunakan'");
						} else {
							WebResource addService2 = service.path("rest").path(UPDATE_BARANG+UPDATE_ALL+"/"+nama+"&"+gambar+"&"+idBarang+"&"+harga+"&"+ jumlah+"&"+description);
							
							//PARSING
							String JSONHasil2 = getOutputAsXML(addService2);
							JSONObject obj2 = new JSONObject(JSONHasil2);
							int hasil2 = obj2.getInt("hasil");
							out.println(getOutputAsXML(addService2) + " " + idBarang);
							if(hasil2 == 1)
								response.sendRedirect("index.jsp?msg='Update sukses!'");
						}
					} else {
						WebResource addService3 = service.path("rest").path(UPDATE_BARANG+UPDATE_ALL+"/"+nama+"&"+gambar+"&"+idBarang+"&"+harga+"&"+ jumlah+"&"+description);
						out.println(getOutputAsXML(addService3));
						
						//PARSING
						String JSONHasil3 = getOutputAsXML(addService3);
						JSONObject obj3 = new JSONObject(JSONHasil3);
						int hasil3 = obj3.getInt("hasil");
						
						if(hasil3 == 1)
							response.sendRedirect("index.jsp?msg='Update sukses!'");
					}
				} else {
					response.sendRedirect("index.jsp?msg='Masukan salah'");
				}
			} else if (type == 1) { // delete
				WebResource addService4 = service.path("rest").path(UPDATE_BARANG+DELETE+"/"+idBarang);
				//out.println(getOutputAsXML(addService4));
				
				//PARSING
				String JSONHasil4 = getOutputAsXML(addService4);
				JSONObject obj4 = new JSONObject(JSONHasil4);
				int hasil4 = obj4.getInt("hasil");
				
				if(hasil4 == 1)
					response.sendRedirect("index.jsp?msg='Delete sukses!'");
			} else if (type == 2) { // add ???		
				AddBarang add = new AddBarang();
				add.createBarang(idBarang, kategori, nama, jumlah, gambar, description, harga);
				/*Statement statement2 = connection.createStatement();
				if (ok) {
					if (request.getParameter("kategori") != null){
						int kategori = Integer.parseInt(request.getParameter("kategori"));
						if (kategori > 0 && kategori <= 5){
							WebResource addService5 = service.path("rest").path(UPDATE_BARANG+GET_BARANG+"/"+nama);
							out.println(getOutputAsXML(addService5));
							
							//PARSING
							String JSONHasil5 = getOutputAsXML(addService5);
							JSONObject obj5 = new JSONObject(JSONHasil5);
							int hasil5 = obj5.getInt("hasil");
							
							if (hasil5 == 1){
								// nama udah ada
								response.sendRedirect("newBarang.jsp?msg='Nama sudah digunakan'");
							} else {
								WebResource addService6 = service.path("rest").path(UPDATE_BARANG+ADD_BARANG+"/"+kategori+"&"+nama+"&"+gambar+"&"+harga+"&"+ jumlah+"&"+description+"&"+0);
								//out.println(getOutputAsXML(addService6));
								
								//PARSING
								String JSONHasil6 = getOutputAsXML(addService6);
								JSONObject obj6 = new JSONObject(JSONHasil6);
								int hasil6 = obj6.getInt("hasil");
							
								if (hasil6 == 1)
									response.sendRedirect("index.jsp?msg='Update sukses!'");
							}
						} else {
							response.sendRedirect("newBarang.jsp?msg='Kategori tidak terdaftar'");
						}
					} else {
						response.sendRedirect("newBarang.jsp?msg='Masukan salah'");
					}
				} else {
					response.sendRedirect("newBarang.jsp?msg='Masukan salah'");
				}*/
			}
		} catch (Exception e) {
			out.println("Ch si");
			e.printStackTrace();
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idBarang = Integer.parseInt(request.getParameter("idBarang"));
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		// ArrayList<ModelInventori> TabelBarang = new
		// ArrayList<ModelInventori>();
		out.println(idBarang);
		try {
		
			Statement statement = connection.createStatement();
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(REST_URI);
			WebResource addService7 = service.path("rest").path(UPDATE_BARANG+GET_BARANG_ID+"/"+idBarang);
						
			//PARSING
			String JSONHasil7 = getOutputAsXML(addService7);
			JSONObject obj7 = new JSONObject(JSONHasil7);
			int hasil7 = obj7.getInt("hasil");
			
			int id_inventori = obj7.getInt("id_inventori");
			int id_kategori = obj7.getInt("id_kategori");
			String nama_inventori = obj7.getString("nama_inventori");
			int jumlah = obj7.getInt("jumlah");
			String gambar = obj7.getString("gambar");
			String description = obj7.getString("description");
			int harga = obj7.getInt("harga");
						
			if (hasil7 == 1){
				ModelInventori barang = new ModelInventori();
				barang.setId_inventori(id_inventori);
				barang.setId_kategori(id_kategori);
				barang.setNama_inventori(nama_inventori);
				barang.setJumlah(jumlah);
				barang.setGambar(gambar);
				barang.setDescription(description);
				barang.setHarga(harga);
				// TabelBarang.add(barang);

				session.setAttribute("barang", barang);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(10);
				response.sendRedirect("editBarang.jsp?idBarang=" + idBarang);
			}

		} catch (Exception e) {
			out.println("Ch si");
			e.printStackTrace();
		}
	}

}
