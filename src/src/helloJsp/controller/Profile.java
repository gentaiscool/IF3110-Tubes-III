package helloJsp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class Profile
 */

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
				//String query = "SELECT * FROM pengguna where username='" + session.getAttribute("user") + "'";
				
				HttpClient client = new DefaultHttpClient();
				HttpGet request2 = new HttpGet("http://127.0.0.1:8080/Chintalian/GetUser?data="+session.getAttribute("user")+"&type=1");
				HttpResponse response2 = client.execute(request2);
				
				// Get the response
				BufferedReader rd = new BufferedReader
				  (new InputStreamReader(response2.getEntity().getContent()));
				    
				String line = "";
				StringBuffer textView = new StringBuffer();
				while ((line = rd.readLine()) != null) {
				  textView.append(line);
				}
				System.out.print(textView.toString());
				//parsing json
		        JSONTokener jsonTokener = new JSONTokener(textView.toString());
		        JSONObject jsonObject = new JSONObject(jsonTokener);
		        JSONObject konten = (JSONObject) jsonObject.get("content");
		        Integer status = (Integer) jsonObject.get("status");
		        
		        if (status == 500) {
					UserBean user = new UserBean(konten.getString("nama_pengguna"), konten.getString("username"), konten.getString("password"), konten.getString("email"), konten.getString("nomor_hp"), konten.getString("alamat"), konten.getString("provinsi"), konten.getString("kota_kabupaten"), konten.getString("kode_pos"), konten.getInt("total_transaksi"));
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
