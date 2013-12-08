package helloJsp.REST;

/**
 * @author Arpit Mandliya
 */

import helloJsp.controller.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("EditBarang")
public class EditBarang {
	
	@GET
	@Path("/GetBarangId/{i}")
	@Produces(MediaType.TEXT_XML)
	public String GetBarang(@PathParam("i") String data) {
		String strIdBarang ="";
		strIdBarang = data.substring(0, data.length());
		
		int idBarang = Integer.parseInt(strIdBarang);
		
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from inventori where id_inventori ="+idBarang+";");
			if (rs.next()) {
				//return 1;
				return "{"
				+ "\"status\": 200"
				+ ",\"detail\": \"OK\""
				+ ",\"hasil\":" + 1
				+ ",\"id_inventori\":" + rs.getInt("id_inventori")
				+ ",\"id_kategori\":" + rs.getInt("id_kategori")
				+ ",\"nama_inventori\":\"" + rs.getString("nama_inventori") + "\""
				+ ",\"jumlah\":" + rs.getInt("jumlah")
				+ ",\"gambar\":\"" + rs.getString("gambar") +"\""
				+ ",\"description\":\"" + rs.getString("description") +"\""
				+ ",\"harga\":" + rs.getInt("harga")
				+ ",\"count\":" + rs.getInt("count")
				+ "}";
			} else {
				//return 0;
				return "{"
				+ "\"status\": 200"
				+ ",\"detail\": \"OK\""
				+ ",\"hasil\":" + 0
				+ "}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{"
			+ "\"status\": 200"
			+ ",\"hasil\":" + 0
			+ "}";
		}
	}
}