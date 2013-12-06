package helloJsp.controller;

import helloJsp.model.ModelInventori;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Category
 */
@WebServlet("/UpdateBarang")
public class UpdateBarang extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean ok = true;
		int idBarang = 0, type = 0, harga = 0, jumlah = 0;
		String nama = null, gambar = null, description = null;

		if (request.getParameter("idBarang") != null)
			idBarang = Integer.parseInt(request.getParameter("idBarang"));
		else
			ok = false;
		if (request.getParameter("type") != null)
			type = Integer.parseInt(request.getParameter("type"));
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
		// ArrayList<ModelInventori> TabelBarang = new
		// ArrayList<ModelInventori>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = null;
			if (type == 0) { // update
				if (ok) {
					if (!barang.getNama_inventori().equals(nama)) {
						rs = statement.executeQuery("select * from inventori where nama_inventori ='" + nama + "';");
						if (rs.next()){
							// nama udah ada
							response.sendRedirect("editBarang.jsp?idBarang=" + idBarang + "&msg='Nama sudah digunakan'");
						} else {
							statement.executeUpdate("update inventori set nama_inventori = '" + nama + "', gambar = '" + gambar + "', harga = " + harga + ", jumlah = " + jumlah + ", description = '" + description + "' where id_inventori =" + idBarang + ";");
							response.sendRedirect("index.jsp?msg='Update sukses!'");
						}
					} else {
						statement.executeUpdate("update inventori set nama_inventori = '" + nama + "', gambar = '" + gambar + "', harga = " + harga + ", jumlah = " + jumlah + ", description = '" + description + "' where id_inventori =" + idBarang + ";");
						response.sendRedirect("index.jsp?msg='Update sukses!'");
					}
				} else {
					response.sendRedirect("index.jsp?msg='Masukan salah'");
				}
			} else if (type == 1) { // deleete
				statement.executeUpdate("delete from inventori where id_inventori =" + idBarang + ";");
				response.sendRedirect("index.jsp?msg='Delete sukses!'");
			} else if (type == 2) { // add ???		
				Statement statement2 = connection.createStatement();
				if (ok) {
					if (request.getParameter("kategori") != null){
						int kategori = Integer.parseInt(request.getParameter("kategori"));
						if (kategori > 0 && kategori <= 5){
							rs = statement.executeQuery("select * from inventori where nama_inventori ='" + nama + "';");
							out.println("a1");
							if (rs.next()){
								// nama udah ada
								response.sendRedirect("newBarang.jsp?msg='Nama sudah digunakan'");
							} else {
								out.println("a22");
								out.println("insert into inventori (id_kategori,nama_inventori,gambar,harga,jumlah,description) values ("+kategori+", '" + nama + "', '" + gambar + "', " + harga + ", " + jumlah + ", '" + description + "');");
								statement2.executeUpdate("insert into inventori (id_kategori,nama_inventori,gambar,harga,jumlah,description, count) values ("+kategori+", '" + nama + "', '" + gambar + "', " + harga + ", " + jumlah + ", '" + description + "', 0);");
								out.println("a3");
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
				}
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
			ResultSet rs = statement.executeQuery("select * from inventori where id_inventori =" + idBarang + ";");
			if (rs.next()) {
				ModelInventori barang = new ModelInventori();
				barang.setId_inventori(rs.getInt("id_inventori"));
				barang.setId_kategori(rs.getInt("id_kategori"));
				barang.setNama_inventori(rs.getString("nama_inventori"));
				barang.setJumlah(rs.getInt("jumlah"));
				barang.setGambar(rs.getString("gambar"));
				barang.setDescription(rs.getString("description"));
				barang.setHarga(rs.getInt("harga"));
				// TabelBarang.add(barang);
				out.println("he");

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
