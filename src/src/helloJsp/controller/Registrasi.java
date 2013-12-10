package helloJsp.controller;

import helloJsp.AddUser.AddUserProxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Registrasi
 */

public class Registrasi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrasi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] dataregister = new String[10];
		dataregister[0] = request.getParameter("nama");
		dataregister[1] = "0";
		dataregister[2] = request.getParameter("username");
		dataregister[3] = request.getParameter("password");
		dataregister[4] = request.getParameter("email");
		dataregister[5] = request.getParameter("nohp");
		dataregister[6] = request.getParameter("alamat");
		dataregister[7] = request.getParameter("provinsi");
		dataregister[8] = request.getParameter("kota");
		dataregister[9] = request.getParameter("kodepos");
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		try {
			Statement statement = connection.createStatement();
			int k = 0;
			
			AddUserProxy p = new AddUserProxy();
			String result = p.createUser(dataregister[0], dataregister[2], dataregister[3], dataregister[4], dataregister[5], dataregister[6], dataregister[7], dataregister[8], dataregister[9]);
			out.println("deng : " + result);
			/*String query = "INSERT INTO pengguna(nama_pengguna,role,username,password,email,nomor_hp,alamat,provinsi,kota_kabupaten,kode_pos,total_transaksi)values(";
			for (String s : dataregister) {
				if (!s.equals(null) && !s.equals("")) {
					if (k != 1) {
						query += "'" + s + "',";
					} else
						query += s + ",";
				} else
					query += null + ",";
				k++;
			}
			query += 0 + ")";
			out.println("1a");
			out.println(query);
			statement.executeUpdate(query);*/
			
			if(result.equals("1")){
				out.println("lol1");
				session.setAttribute("user", dataregister[0]);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(1800);
				Cookie userName = new Cookie("user", dataregister[2]);
				userName.setMaxAge(30 * 60);

				response.addCookie(userName);
				response.sendRedirect("registerCardForm.jsp");
			} else {
				out.println("lol2");
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/login.jsp");
				out.println("<font color=red>Proses registrasi gagal</font>");
				rd.include(request, response);
			}
		} catch (Exception e) {
			out.println("ex");
			e.printStackTrace();
		}
	}

}
