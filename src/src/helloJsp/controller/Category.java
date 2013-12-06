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

/**
 * Servlet implementation class Category
 */
@WebServlet("/Category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idKat = Integer.parseInt(request.getParameter("idPage"));
		int pagi = Integer.parseInt(request.getParameter("pagi"));
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
			response.sendRedirect("category.jsp?idPage="+idKat+"&pagi="+pagi);
			
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
		}
	}

}
