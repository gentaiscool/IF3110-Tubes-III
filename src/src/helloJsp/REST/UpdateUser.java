package helloJsp.REST;

/**
 * @author Arpit Mandliya
 */

import helloJsp.controller.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("UpdateUser")
public class UpdateUser {

	@PUT
	@Path("/data/{i}")
	@Produces(MediaType.TEXT_XML)
	public String getUser(@PathParam("username") String username) {
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from pengguna where username ='" + username + "'");
			if (rs.next()) {
				return "{"
						+ "\"status\": 200"
						+ "\"detail\": \"OK\""
						+ ",\"content\":{"
						+ "\"id_pengguna\":" + rs.getInt("id_pengguna")
						+ ",\"nama_pengguna\":\"" + rs.getString("nama_pengguna") +"\""
						+ ",\"role\":" + rs.getInt("role")
						+ ",\"username\":\"" + username + "\""
						+ ",\"password\":\"" + rs.getString("password") +"\""
						+ ",\"email\":\"" + rs.getString("email") +"\""
						+ ",\"nomor_hp\":\"" + rs.getString("nama_pengguna") +"\""
						+ ",\"alamat\":\"" + rs.getString("nama_pengguna") +"\""
						+ ",\"provinsi\":\"" + rs.getString("nama_pengguna") +"\""
						+ ",\"kota_kabupaten\":\"" + rs.getString("kota_kabupaten") +"\""
						+ ",\"kode_pos\":\"" + rs.getString("kode_pos") +"\""
						+ ",\"total_transaksi\":" + rs.getInt("total_transaksi")
						+ ",\"nomor_credit_card\":\"" + rs.getString("nomor_credit_card") +"\""
						+ ",\"expired_date\":\"" + rs.getString("expired_date") +"\""
						+ ",\"nama_on_card\":\"" + rs.getString("nama_on_card") +"\""
						+ "} }";
			} else {
				return "{"
						+ "\"status\": 204"
						+ "\"detail\": \"OK\""
						+ ",\"content\":\"null\""
						+ "}";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{"
		+ "\"status\": 200"
		+ "\"detail\": \"OK\""
		+ ",\"content\":\"null\""
		+ "}";
	}
}