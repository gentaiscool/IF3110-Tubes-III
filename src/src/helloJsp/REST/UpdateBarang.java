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

@Path("UpdateBarang")
public class UpdateBarang {
	
	@GET
	@Path("/GetBarangId/{i}")
	@Produces(MediaType.TEXT_XML)
	public String GetBarangId(@PathParam("i") String data) {
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
				+ ",\"nama_inventori\":\""+ rs.getString("nama_inventori") + "\""
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
			+ ",\"detail\": \"OK\""
			+ ",\"hasil\":" + 0
			+ "}";
		}
	}
	
	@GET
	@Path("/GetBarang/{i}")
	@Produces(MediaType.TEXT_XML)
	public String GetBarang(@PathParam("i") String data) {
		String nama ="";
		nama = data.substring(0, data.length());
		
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from inventori where nama_inventori ='" + nama + "';");
			if (rs.next()) {
				//return 1;
				return "{"
				+ "\"status\": 200"
				+ ",\"detail\": \"OK\""
				+ ",\"hasil\":" + 1
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
			+ ",\"detail\": \"OK\""
			+ ",\"hasil\":" + 0
			+ "}";
		}
	}
		
	@GET
	@Path("/UpdateAll/{i}")
	@Produces(MediaType.TEXT_XML)
	public String UpdateAll(@PathParam("i") String data) {
		String nama ="";
		String gambar = "";
		String strHarga = "";
		String strJumlah = "";
		String description = "";
		String strIdBarang = "";
		
		int i, j, k, l, m,n;
		for(i=0; i<data.length(); i++){
			if(data.charAt(i) == '&'){
				nama = data.substring(0, i);
				break;
			}
		}
		j = i+1;
		while ( j < data.length()){
			if(data.charAt(j) == '&'){
				gambar = data.substring(i+1, j);
				break;
			}
			j++;
		}
		k = j+1;
		
		while ( k < data.length()){
			if(data.charAt(k) == '&'){
				strIdBarang = data.substring(j+1, k);
				break;
			}
			k++;
		}
		l = k+1;
		while ( l < data.length()){
			if(data.charAt(l) == '&'){
				strHarga = data.substring(k+1, l);
				break;
			}
			l++;
		}
		m = l+1;
		
		while ( m < data.length()){
			if(data.charAt(m) == '&'){
				strJumlah = data.substring(l+1, m);
				break;
			}
			m++;
		}
		
		n = m+1;
		while ( n < data.length()){
			description = data.substring(m+1, data.length()-1);
			break;
		}
		
		int harga = Integer.parseInt(strHarga);
		int jumlah = Integer.parseInt(strJumlah);
		int idBarang = Integer.parseInt(strIdBarang);
		
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			System.out.print("update inventori set nama_inventori = '" + nama + "', gambar = '" + gambar + "', harga = " + harga + ", jumlah = " + jumlah + ", description = '" + description + "' where id_inventori =" + idBarang + ";");
			statement.executeUpdate("update inventori set nama_inventori = '" + nama + "', gambar = '" + gambar + "', harga = " + harga + ", jumlah = " + jumlah + ", description = '" + description + "' where id_inventori =" + idBarang + ";");
			return "{"
			+ "\"status\": 200"
			+ ",\"detail\": \"OK\""
			+ ",\"hasil\":" + 1
			+ "}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{"
			+ "\"status\": 200"
			+ ",\"detail\": \"OK\""
			+ ",\"hasil\":" + 0
			+ "}";
		}
	}
	
	@GET
	@Path("/Delete/{i}")
	@Produces(MediaType.TEXT_XML)
	public String Delete(@PathParam("i") String data) {
		String strIdBarang ="";
		strIdBarang = data.substring(0, data.length());
		int idBarang = Integer.parseInt(strIdBarang);
		
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("delete from inventori where id_inventori =" + idBarang + ";");
			return "{"
			+ "\"status\": 200"
			+ ",\"detail\": \"OK\""
			+ ",\"hasil\":" + 1
			+ "}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{"
			+ "\"status\": 200"
			+ ",\"detail\": \"OK\""
			+ ",\"hasil\":" + 0
			+ "}";
		}
	}
	
	@GET
	@Path("/AddBarang/{i}")
	@Produces(MediaType.TEXT_XML)
	public String AddBarang(@PathParam("i") String data) {
		int h,i,j,k,l,m;
		String strKategori ="";
		String nama = "";
		String gambar = "";
		String strHarga = "";
		String strJumlah = "";
		String description = "";
		String strIdBarang = "";
		
		for(h=0; h<data.length(); h++){
			if(data.charAt(h) == '&'){
				strKategori = data.substring(0, h);
				break;
			}
		}
		i = h+1;
		while ( i < data.length()){
			if(data.charAt(i) == '&'){
				gambar = data.substring(h+1, i);
				break;
			}
			i++;
		}
		j = i+1;
		while ( j < data.length()){
			if(data.charAt(j) == '&'){
				gambar = data.substring(i+1, j);
				break;
			}
			j++;
		}
		k = j+1;
		while ( k < data.length()){
			if(data.charAt(k) == '&'){
				strHarga = data.substring(j+1, k);
				break;
			}
			k++;
		}
		l = k+1;
		while ( l < data.length()){
			if(data.charAt(l) == '&'){
				strJumlah = data.substring(k+1, l);
				break;
			}
			l++;
		}
		m = l+1;
		while ( m < data.length()){
			if(data.charAt(m) == '&'){
				description = data.substring(l+1, m);
				break;
			}
			m++;
		}
		m = l+1;
		while ( m < data.length()){
			if(data.charAt(m) == '&'){
				strIdBarang = data.substring(l+1, m);
				break;
			}
			m++;
		}
		
		int kategori = Integer.parseInt(strKategori);
		int harga = Integer.parseInt(strHarga);
		int jumlah = Integer.parseInt(strJumlah);
		int idBarang = Integer.parseInt(strIdBarang);
		
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into inventori (id_kategori,nama_inventori,gambar,harga,jumlah,description, count) values ("+kategori+", '" + nama + "', '" + gambar + "', " + harga + ", " + jumlah + ", '" + description + "', 0);");
			return "{"
			+ "\"status\": 200"
			+ ",\"detail\": \"OK\""
			+ ",\"hasil\":" + 1
			+ "}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{"
			+ "\"status\": 200"
			+ ",\"detail\": \"OK\""
			+ ",\"hasil\":" + 0
			+ "}";
		}
	}
	
}