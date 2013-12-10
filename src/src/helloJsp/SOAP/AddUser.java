package helloJsp.SOAP;

import helloJsp.controller.DbConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddUser {
	public String createUser(String nama_pengguna, String username, String password, String email, String nomor_hp, String alamat, String provinsi, String kota_kabupaten, String kode_pos){
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("insert into pengguna (nama_pengguna, role, username, password, email, nomor_hp,"
					+ "alamat, provinsi, kota_kabupaten, kode_pos, total_transaksi, expired_date, nama_on_card) values ('"
					+ nama_pengguna + "', 0, '" + username + "', '" + password + "', '" + email + "', '" 
					+ nomor_hp + "', '" + alamat + "', '" + provinsi + "', '" + kota_kabupaten + "', '" + kode_pos + "', 0, '00-00-00', 'dummy');");
			return "1";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
	}

}