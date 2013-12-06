package helloJsp.controller;

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

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from inventori;");
				while (rs.next()) {
					listNama.add(rs.getString("nama_inventori").toLowerCase());
					listId.add(rs.getInt("id_inventori"));
				}
				String result = "";
				for (int i=0; i<listNama.size(); i++){
					if (listNama.get(i).contains(item.toLowerCase())){
						result+=("<li><a href='detailBarang.jsp?idBarang="+listId.get(i)+"'>"+listNama.get(i)+"</a></li>");
					}
				}
				
				listNama = new ArrayList<String>();
				listId = new ArrayList<Integer>();
				statement = connection.createStatement();
				rs = statement.executeQuery("select * from inventori;");
				while (rs.next()) {
					listNama.add(rs.getString("nama_inventori").toLowerCase());
					listId.add(rs.getInt("id_inventori"));
					listKat.add(rs.getInt("id_kategori"));
				}
				String[] cat = {" ","appetizer","pizza","pasta","desserts","beverages"};
				boolean[] tes = {true,true,true,true,true,true};
				for (int i=0; i<listNama.size(); i++){
					if (tes[listKat.get(i)]){
						//out.print(i + " " + listKat.get(i)+" "+cat[listKat.get(i)]);
						if (cat[listKat.get(i)].contains(item.toLowerCase())){
							tes[listKat.get(i)] = false;
							result+=("<li><a href='Category?idPage="+listKat.get(i)+"&pagi=1'>"+cat[listKat.get(i)]+"</a></li>");
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
				statement = connection.createStatement();
				rs = statement.executeQuery("select * from inventori;");
				while (rs.next()) {
					listNama.add(rs.getString("nama_inventori").toLowerCase());
					listId.add(rs.getInt("id_inventori"));
					listHarga.add(rs.getInt("harga"));
				}
				//out.print(result);
				for (int i=0; i<listNama.size(); i++){
					if (listHarga.get(i).toString().contains(item.toLowerCase())){
						result+=("<li><a href='detailBarang.jsp?idBarang="+listId.get(i)+"'>"+listNama.get(i)+"</a></li>");
					}
				}
				//out.print("ch ga si");
				if (result.equals("")) result = "<li>No suggestion</li>";
				
				out.print(result);
				
				// session.setAttribute("result", result);
				// setting session to expiry in 30 mins
				// session.setMaxInactiveInterval(10);
				// response.sendRedirect("index.jsp");
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
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from inventori;");
				while (rs.next()) {
					listNama.add(rs.getString("nama_inventori").toLowerCase());
					listId.add(rs.getInt("id_inventori"));
				}
				String result = "";
				for (int i=0; i<listNama.size(); i++){
					if (item.toLowerCase().equals(listNama.get(i).substring(0,item.length()))){
						result+=("<li><a href='detailBarang.jsp?idBarang="+listId.get(i)+"'>"+listNama.get(i)+"</a></li>");
					}
				}
				
				listNama = new ArrayList<String>();
				listId = new ArrayList<Integer>();
				statement = connection.createStatement();
				rs = statement.executeQuery("select * from inventori;");
				while (rs.next()) {
					listNama.add(rs.getString("nama_inventori").toLowerCase());
					listId.add(rs.getInt("id_inventori"));
					listKat.add(rs.getInt("id_kategori"));
				}
				String[] cat = {" ","Appetizer","Pizza","Pasta","Desserts","Beverages"};
				boolean[] tes = {true,true,true,true,true,true};
				for (int i=0; i<listNama.size(); i++){
					if (tes[listKat.get(i)]){
						if (item.toLowerCase().equals(cat[listKat.get(i)].substring(0,item.length()))){
							tes[listKat.get(i)] = false;
							result+=("<li><a href='Category?idPage="+listKat.get(i)+"'>"+cat[listKat.get(i)]+"</a></li>");
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
				statement = connection.createStatement();
				rs = statement.executeQuery("select * from inventori;");
				while (rs.next()) {
					listNama.add(rs.getString("nama_inventori").toLowerCase());
					listId.add(rs.getInt("id_inventori"));
					listHarga.add(rs.getInt("harga"));
				}
				for (int i=0; i<listNama.size(); i++){
					if (item.toLowerCase().equals(listHarga.get(i).toString().substring(0,item.length()))){
						result+=("<li><a href='detailBarang.jsp?idBarang="+listId.get(i)+"'>"+listNama.get(i)+"</a></li>");
					}
				}
				
				if (result == null) result = "<li>No suggestion</li>";
				
				out.print(result);
				
				// session.setAttribute("result", result);
				// setting session to expiry in 30 mins
				// session.setMaxInactiveInterval(10);
				// response.sendRedirect("index.jsp");
			} catch (Exception e) {
				out.println("Ch si");
				e.printStackTrace();
			}
		}
	}

}
