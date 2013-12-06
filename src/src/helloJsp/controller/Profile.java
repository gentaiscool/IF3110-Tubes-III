package helloJsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		try {
			if (session.getAttribute("user") == null) {
				String forward = "/registrasi.jsp";
				RequestDispatcher dispatch = getServletContext().getRequestDispatcher(forward);
				dispatch.forward(request, response);
			} else {
				Statement statement = connection.createStatement();
				int k = 0;
				String data = "";
				String query = "SELECT * FROM pengguna where username='" + session.getAttribute("user") + "'";
				ResultSet rs = statement.executeQuery(query);
				if (rs.next()) {
					UserBean user = new UserBean(rs.getString("nama_pengguna"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("nomor_hp"), rs.getString("alamat"), rs.getString("provinsi"), rs.getString("kota_kabupaten"), rs.getString("kode_pos"), rs.getString("total_transaksi"));
					request.setAttribute("user", user);
				}
				String forward = "/viewProfile.jsp";
				RequestDispatcher dispatch = getServletContext().getRequestDispatcher(forward);
				dispatch.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
