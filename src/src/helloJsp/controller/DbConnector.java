package helloJsp.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.json.JSONArray;
import org.json.JSONObject;

public class DbConnector {
	Connection connection = null;
	public String URLSQL = "";
	public String SQLUser = "";
	public String SQLPass = "";
	public String Path = "";
/*
	public Connection mySqlConnection() {
		try {
			if (java.lang.System.getenv("VCAP_SERVICES") != null) {
				JSONObject jsonVCAP = new JSONObject(java.lang.System.getenv("VCAP_SERVICES"));
				JSONArray mysql = jsonVCAP.getJSONArray("mysql-5.1");
				String table = mysql.getJSONObject(0).getJSONObject("credentials").getString("name");
				URLSQL = "jdbc:mysql://" + mysql.getJSONObject(0).getJSONObject("credentials").getString("hostname") + ":3306/" + table;
				SQLUser = mysql.getJSONObject(0).getJSONObject("credentials").getString("username");
				SQLPass = mysql.getJSONObject(0).getJSONObject("credentials").getString("password");

				String url = URLSQL;
				String username = SQLUser;
				String password = SQLPass;
				// String url = "jdbc:mysql://localhost:3306/chintalian";
				String driver = "com.mysql.jdbc.Driver";

				try {
					Class.forName(driver).newInstance();
					connection = DriverManager.getConnection(url, username, password);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
		}
		return connection;
	}*/
	
	  public Connection mySqlConnection() { 
		  try { 
			  //String url = URLSQL; 
			  String username = "root"; String password = ""; 
			  String url = "jdbc:mysql://localhost:3306/chintalian"; 
			  String driver = "com.mysql.jdbc.Driver";
			  // String username = "root"; // String password = "";
	  
	  try { Class.forName(driver).newInstance(); connection =
	  DriverManager.getConnection(url, username, password); } catch (Exception
	  e) { e.printStackTrace(); } } catch (Exception e) { } return connection;
	  }
	 
}
