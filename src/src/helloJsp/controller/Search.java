package helloJsp.controller;

import helloJsp.model.ModelInventori;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class Search
 */

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String REST_URI = "http://localhost:8080/Chintalian";
    static final String GET_BARANG = "/GetBarang";
    static final String SEARCH = "/search";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private static String getOutputAsXML(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(String.class);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String item = request.getParameter("item");
		//int pagisearch = Integer.parseInt(request.getParameter("pagisearch"));
		
		if (item != null){
			ArrayList<String> listNama = new ArrayList<String>();
			ArrayList<Integer> listId = new ArrayList<Integer>();
			ArrayList<Integer> listKat = new ArrayList<Integer>();
			ArrayList<Integer> listHarga = new ArrayList<Integer>();
			DbConnector dbconnector = new DbConnector();
			Connection connection = dbconnector.mySqlConnection();
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			ArrayList<String> groups = new ArrayList<String>();
			try {
				ClientConfig config = new DefaultClientConfig();
		        Client client = Client.create(config);
		        WebResource service = client.resource(REST_URI);
		        WebResource addServiceSearch = service.path("rest").path(GET_BARANG+SEARCH);
		        
		        // Parsing
				String JSONBarang = getOutputAsXML(addServiceSearch);
				JSONObject obj = new JSONObject(JSONBarang);
		        JSONArray arr = obj.getJSONArray("content");
		        
				for (int i = 0; i < arr.length(); i++) {
					listNama.add(arr.getJSONObject(i).getString("nama_inventori").toLowerCase());
					listId.add(arr.getJSONObject(i).getInt("id_inventori"));
				}
				
				ArrayList<String> result = new ArrayList<String>();
				for (int i=0; i<listNama.size(); i++){
					if (listNama.get(i).contains(item.toLowerCase())){
						result.add("<li><a href='detailBarang.jsp?idBarang="+listId.get(i)+"'>"+listNama.get(i)+"</a></li>");
					}
				}
				
				listNama = new ArrayList<String>();
				listId = new ArrayList<Integer>();
				
				for (int i = 0; i < arr.length(); i++) {
					listNama.add(arr.getJSONObject(i).getString("nama_inventori").toLowerCase());
					listId.add(arr.getJSONObject(i).getInt("id_inventori"));
					listKat.add(arr.getJSONObject(i).getInt("id_kategori"));
				}
				
				String[] cat = {" ","appetizer","pizza","pasta","desserts","beverages"};
				boolean[] tes = {true,true,true,true,true,true};
				for (int i=0; i<listNama.size(); i++){
					if (tes[listKat.get(i)]){
						//out.print(i + " " + listKat.get(i)+" "+cat[listKat.get(i)]);
						if (cat[listKat.get(i)].contains(item.toLowerCase())){
							tes[listKat.get(i)] = false;
							result.add("<li><a href='Category?idPage="+listKat.get(i)+"&pagi=1'>"+cat[listKat.get(i)]+"</a></li>");
							/* ??
							for($i=0; $i<count($a); $i++)
							{	
								if ((strtolower($q)==strtolower(substr($x[$i],0,strlen($q))))&&($x[$i]==$groups[$j]))
								{
									$temp = '<li><a href="detailbarang.php?id='.($i+1).'">'.$a[$i].'</a></li>';
									$hint=$hint.$temp;
								}
							}
							*/
						}
					}
				}
				
				listNama = new ArrayList<String>();
				listId = new ArrayList<Integer>();
				
				for (int i = 0; i < arr.length(); i++) {
					listNama.add(arr.getJSONObject(i).getString("nama_inventori").toLowerCase());
					listId.add(arr.getJSONObject(i).getInt("id_inventori"));
					listHarga.add(arr.getJSONObject(i).getInt("harga"));
				}
				//out.print(result);
				for (int i=0; i<listNama.size(); i++){
					if (listHarga.get(i).toString().contains(item.toLowerCase())){
						result.add("<li><a href='detailBarang.jsp?idBarang="+listId.get(i)+"'>"+listNama.get(i)+"</a></li>");
					}
				}
				//out.print("ch ga si");
				if (result.equals("")) result.add("<li>No suggestion</li>");
				
				out.print(result);
				
				session.setAttribute("result", result);
				// setting session to expiry in 30 mins
				// session.setMaxInactiveInterval(10);
				response.sendRedirect("searchResult.jsp?pagisearch=1");
			} catch (Exception e) {
				out.println("Ch si");
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String item = request.getParameter("item");
		
		if (item != null){
			ArrayList<String> listNama = new ArrayList<String>();
			ArrayList<Integer> listId = new ArrayList<Integer>();
			ArrayList<Integer> listKat = new ArrayList<Integer>();
			ArrayList<Integer> listHarga = new ArrayList<Integer>();
			DbConnector dbconnector = new DbConnector();
			Connection connection = dbconnector.mySqlConnection();
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			ArrayList<String> groups = new ArrayList<String>();
			try {
				ClientConfig config = new DefaultClientConfig();
		        Client client = Client.create(config);
		        WebResource service = client.resource(REST_URI);
		        WebResource addServiceSearch = service.path("rest").path(GET_BARANG+SEARCH);
		        
		        // Parsing
				String JSONBarang = getOutputAsXML(addServiceSearch);
				JSONObject obj = new JSONObject(JSONBarang);
		        JSONArray arr = obj.getJSONArray("content");
		        
				for (int i = 0; i < arr.length(); i++) {
					listNama.add(arr.getJSONObject(i).getString("nama_inventori").toLowerCase());
					listId.add(arr.getJSONObject(i).getInt("id_inventori"));
				}
				
				ArrayList<String> result = new ArrayList<String>();
				for (int i=0; i<listNama.size(); i++){
					if (listNama.get(i).contains(item.toLowerCase())){
						result.add("<li><a href='detailBarang.jsp?idBarang="+listId.get(i)+"'>"+listNama.get(i)+"</a></li>");
					}
				}
				
				listNama = new ArrayList<String>();
				listId = new ArrayList<Integer>();
				
				for (int i = 0; i < arr.length(); i++) {
					listNama.add(arr.getJSONObject(i).getString("nama_inventori").toLowerCase());
					listId.add(arr.getJSONObject(i).getInt("id_inventori"));
					listKat.add(arr.getJSONObject(i).getInt("id_kategori"));
				}
				
				String[] cat = {" ","appetizer","pizza","pasta","desserts","beverages"};
				boolean[] tes = {true,true,true,true,true,true};
				for (int i=0; i<listNama.size(); i++){
					if (tes[listKat.get(i)]){
						//out.print(i + " " + listKat.get(i)+" "+cat[listKat.get(i)]);
						if (cat[listKat.get(i)].contains(item.toLowerCase())){
							tes[listKat.get(i)] = false;
							result.add("<li><a href='Category?idPage="+listKat.get(i)+"&pagi=1'>"+cat[listKat.get(i)]+"</a></li>");
							/* ??
							for($i=0; $i<count($a); $i++)
							{	
								if ((strtolower($q)==strtolower(substr($x[$i],0,strlen($q))))&&($x[$i]==$groups[$j]))
								{
									$temp = '<li><a href="detailbarang.php?id='.($i+1).'">'.$a[$i].'</a></li>';
									$hint=$hint.$temp;
								}
							}
							*/
						}
					}
				}
				
				listNama = new ArrayList<String>();
				listId = new ArrayList<Integer>();
				
				for (int i = 0; i < arr.length(); i++) {
					listNama.add(arr.getJSONObject(i).getString("nama_inventori").toLowerCase());
					listId.add(arr.getJSONObject(i).getInt("id_inventori"));
					listHarga.add(arr.getJSONObject(i).getInt("harga"));
				}
				//out.print(result);
				for (int i=0; i<listNama.size(); i++){
					if (listHarga.get(i).toString().contains(item.toLowerCase())){
						result.add("<li><a href='detailBarang.jsp?idBarang="+listId.get(i)+"'>"+listNama.get(i)+"</a></li>");
					}
				}
				//out.print("ch ga si");
				if (result.equals("")) result.add("<li>No suggestion</li>");
				
				out.print(result);
				
				session.setAttribute("result", result);
				// setting session to expiry in 30 mins
				// session.setMaxInactiveInterval(10);
				response.sendRedirect("searchResult.jsp");
			} catch (Exception e) {
				out.println("Ch si");
				e.printStackTrace();
			}
		}
	}
}
