package helloJsp.REST;

import helloJsp.controller.DbConnector;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class GetUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer type = Integer.parseInt(request.getParameter("type").toString());
		if (type == 1) {
			// getuser
			String username = request.getParameter("data");
			DbConnector dbconnector = new DbConnector();
			Connection connection = dbconnector.mySqlConnection();
			PrintWriter out = response.getWriter();
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from pengguna where username ='" + username + "'");
				if (rs.next()) {
					response.getWriter().print("{" + "\"status\": 500" + ",\"detail\": \"OK\"" + ",\"content\":{" + "\"id_pengguna\":" + rs.getInt("id_pengguna") + ",\"nama_pengguna\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"role\":" + rs.getInt("role") + ",\"username\":\"" + username + "\"" + ",\"password\":\"" + rs.getString("password") + "\"" + ",\"email\":\"" + rs.getString("email") + "\"" + ",\"nomor_hp\":\"" + rs.getString("nomor_hp") + "\"" + ",\"alamat\":\"" + rs.getString("alamat") + "\"" + ",\"provinsi\":\"" + rs.getString("provinsi") + "\"" + ",\"kota_kabupaten\":\"" + rs.getString("kota_kabupaten") + "\"" + ",\"kode_pos\":\"" + rs.getString("kode_pos") + "\"" + ",\"total_transaksi\":" + rs.getInt("total_transaksi") + ",\"nomor_credit_card\":\"" + rs.getString("nomor_credit_card") + "\"" + ",\"expired_date\":\"" + rs.getString("expired_date") + "\"" + ",\"nama_on_card\":\"" + rs.getString("nama_on_card") + "\"" + "} }");
				} else {
					response.getWriter().print("{" + "\"status\": 204" + ",\"detail\": \"OK\"" + ",\"content\":\"null\"" + "}");
				}
			} catch (Exception X) {
				response.getWriter().print("{" + "\"status\": 204" + ",\"detail\": \"OK\"" + ",\"content\":\"null\"" + "}");
			}
		} else if (type == 2) {
			// login
			String username = request.getParameter("username"), password = request.getParameter("password");

			DbConnector dbconnector = new DbConnector();
			Connection connection = dbconnector.mySqlConnection();
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from pengguna where username ='" + username + "' and password ='" + password + "'");
				// return("select * from pengguna where username ='" + username
				// +
				// "' and password ='"+password+"'");
				if (rs.next()) {
					response.getWriter().print("{" + "\"status\": 500" + ",\"detail\": \"OK\"" + ",\"content\":{" + "\"id_pengguna\":" + rs.getInt("id_pengguna") + ",\"nama_pengguna\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"role\":" + rs.getInt("role") + ",\"username\":\"" + username + "\"" + ",\"password\":\"" + rs.getString("password") + "\"" + ",\"email\":\"" + rs.getString("email") + "\"" + ",\"nomor_hp\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"alamat\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"provinsi\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"kota_kabupaten\":\"" + rs.getString("kota_kabupaten") + "\"" + ",\"kode_pos\":\"" + rs.getString("kode_pos") + "\"" + ",\"total_transaksi\":" + rs.getInt("total_transaksi") + ",\"nomor_credit_card\":\"" + rs.getString("nomor_credit_card") + "\"" + ",\"expired_date\":\"" + rs.getString("expired_date") + "\"" + ",\"nama_on_card\":\"" + rs.getString("nama_on_card") + "\"" + "} }");
				} else {
					response.getWriter().print("{" + "\"status\": 200" + ",\"detail\": \"OK\"" + ",\"content\":\"null\"" + "}");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print("{" + "\"status\": 200" + ",\"detail\": \"OK\"" + ",\"content\":\"null\"" + "}");
			}
		} else if (type == 3) {
			// check email
			// getuser
			String username = request.getParameter("data");
			DbConnector dbconnector = new DbConnector();
			Connection connection = dbconnector.mySqlConnection();
			PrintWriter out = response.getWriter();
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from pengguna where email ='" + username + "'");
				if (rs.next()) {
					response.getWriter().print("{" + "\"status\": 500" + ",\"detail\": \"OK\"" + ",\"content\":{" + "\"id_pengguna\":" + rs.getInt("id_pengguna") + ",\"nama_pengguna\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"role\":" + rs.getInt("role") + ",\"username\":\"" + username + "\"" + ",\"password\":\"" + rs.getString("password") + "\"" + ",\"email\":\"" + rs.getString("email") + "\"" + ",\"nomor_hp\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"alamat\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"provinsi\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"kota_kabupaten\":\"" + rs.getString("kota_kabupaten") + "\"" + ",\"kode_pos\":\"" + rs.getString("kode_pos") + "\"" + ",\"total_transaksi\":" + rs.getInt("total_transaksi") + ",\"nomor_credit_card\":\"" + rs.getString("nomor_credit_card") + "\"" + ",\"expired_date\":\"" + rs.getString("expired_date") + "\"" + ",\"nama_on_card\":\"" + rs.getString("nama_on_card") + "\"" + "} }");
				} else {
					response.getWriter().print("{" + "\"status\": 204" + ",\"detail\": \"OK\"" + ",\"content\":\"null\"" + "}");
				}
			} catch (Exception X) {
				response.getWriter().print("{" + "\"status\": 204" + ",\"detail\": \"OK\"" + ",\"content\":\"null\"" + "}");
			}
		} else if(type == 4){
			//check if not dummy credit card
			String username = request.getParameter("data");
			DbConnector dbconnector = new DbConnector();
			Connection connection = dbconnector.mySqlConnection();
			PrintWriter out = response.getWriter();
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from pengguna where username ='" + username + "' and nama_on_card='dummy'");
				if (rs.next()) {
					System.out.println(":a");
					response.getWriter().print("{" + "\"status\": 500" + ",\"detail\": \"OK\"" + ",\"content\":{" + "\"id_pengguna\":" + rs.getInt("id_pengguna") + ",\"nama_pengguna\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"role\":" + rs.getInt("role") + ",\"username\":\"" + username + "\"" + ",\"password\":\"" + rs.getString("password") + "\"" + ",\"email\":\"" + rs.getString("email") + "\"" + ",\"nomor_hp\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"alamat\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"provinsi\":\"" + rs.getString("nama_pengguna") + "\"" + ",\"kota_kabupaten\":\"" + rs.getString("kota_kabupaten") + "\"" + ",\"kode_pos\":\"" + rs.getString("kode_pos") + "\"" + ",\"total_transaksi\":" + rs.getInt("total_transaksi") + ",\"nomor_credit_card\":\"" + rs.getString("nomor_credit_card") + "\"" + ",\"expired_date\":\"" + rs.getString("expired_date") + "\"" + ",\"nama_on_card\":\"" + rs.getString("nama_on_card") + "\"" + "} }");
				} else {
					response.getWriter().print("{" + "\"status\": 204" + ",\"detail\": \"OK\"" + ",\"content\":\"null\"" + "}");
				}
			} catch (Exception X) {
				response.getWriter().print("{" + "\"status\": 204" + ",\"detail\": \"OK\"" + ",\"content\":\"null\"" + "}");
			}
		}
	}
}