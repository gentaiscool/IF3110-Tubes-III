package helloJsp.SOAP;

import helloJsp.controller.DbConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddBarang {
	
	public String createBarang(int id_inventori, int kategori, String nama, int jumlah, String gambar, String description, int harga){
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("insert into inventori (id_kategori, nama_inventori, gambar, harga, jumlah,"
					+ "description, count) values ("+kategori+", '" + nama + "', '" + gambar + "', " + harga 
					+ ", " + jumlah + ", '" + description + "', 0);");
			return "1";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
	}

}
