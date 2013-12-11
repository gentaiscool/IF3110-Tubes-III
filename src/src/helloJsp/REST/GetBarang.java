package helloJsp.REST;

import helloJsp.controller.DbConnector;
import helloJsp.model.ModelInventori;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

@Path("GetBarang")
public class GetBarang {
	@GET
	@Path("/id/{i}")
	@Produces(MediaType.TEXT_XML)
	public String getBarangById(@PathParam("i") int id_inventori) {
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from inventori where id_inventori =" + id_inventori + ";");
			if (rs.next()) {
				return "{"
						+ "\"status\": 200"
						+ ",\"detail\": \"OK\""
						+ ",\"content\":{"
						+ ",\"id_inventori\":" + id_inventori
						+ ",\"id_kategori\":" + rs.getInt("id_kategori")
						+ ",\"nama_inventori\":\"" + rs.getString("nama_inventori") +"\""
						+ ",\"jumlah\":" + rs.getInt("jumlah")
						+ ",\"gambar\":\"" + rs.getString("gambar") +"\""
						+ ",\"description\":\"" + rs.getString("description") +"\""
						+ ",\"harga\":" + rs.getInt("harga")
						+ ",\"count\":" + rs.getInt("count")
						+ "} }";
			} else {
				return "{"
						+ "\"status\": 200"
						+ ",\"detail\": \"OK\""
						+ ",\"content\":\"null\""
						+ "}";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{"
		+ "\"status\": 200"
		+ ",\"detail\": \"OK\""
		+ ",\"content\":\"null\""
		+ "}";
	}

	@GET
	@Path("/kategori/{i}")
	@Produces(MediaType.TEXT_XML)
	public String getBarangByKategori(@PathParam("i") int id_kategori) {
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from inventori where id_kategori = " + id_kategori + ";");
			
			JSONObject jObj = new JSONObject();
			ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
			
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
			}
			
			jObj.put("content", TabelBarang);
			jObj.put("detail", "OK");
			jObj.put("status", 200);
			
			return jObj.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{"
		+ "\"status\": 200"
		+ ",\"detail\": \"OK\""
		+ ",\"content\":\"null\""
		+ "}";
	}

	@GET
	@Path("/sortByPrice/{i}")
	@Produces(MediaType.TEXT_XML)
	public String getBarangSortByPrice(@PathParam("i") int id_kategori) {
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from inventori where id_kategori = " + id_kategori + 
					 " order by harga;");
			
			JSONObject jObj = new JSONObject();
			ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
			
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
			}
			
			jObj.put("content", TabelBarang);
			jObj.put("detail", "OK");
			jObj.put("status", 200);
			
			return jObj.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{"
		+ "\"status\": 200"
		+ ",\"detail\": \"OK\""
		+ ",\"content\":\"null\""
		+ "}";
	}
	
	@GET
	@Path("/sortByName/{i}")
	@Produces(MediaType.TEXT_XML)
	public String getBarangSortByName(@PathParam("i") int id_kategori) {
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from inventori where id_kategori = " + id_kategori + 
					 " order by nama_inventori;");
			
			JSONObject jObj = new JSONObject();
			ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
			
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
			}
			
			jObj.put("content", TabelBarang);
			jObj.put("detail", "OK");
			jObj.put("status", 200);
			
			return jObj.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{"
		+ "\"status\": 200"
		+ ",\"detail\": \"OK\""
		+ ",\"content\":\"null\""
		+ "}";
	}
	
	@GET
	@Path("/Favorit")
	@Produces(MediaType.TEXT_XML)
	public String getFavorit() {
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from inventori order by count desc;");
			
			JSONObject jObj = new JSONObject();
			ArrayList<ModelInventori> TabelBarang = new ArrayList<ModelInventori>();
			
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
			}	
			
			jObj.put("content", TabelBarang);
			jObj.put("detail", "OK");
			jObj.put("status", 200);
			
			return jObj.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{"
		+ "\"status\": 200"
		+ ",\"detail\": \"OK\""
		+ ",\"content\":\"null\""
		+ "}";
	}
}
