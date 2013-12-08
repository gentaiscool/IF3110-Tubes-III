package helloJsp.REST;

import helloJsp.controller.DbConnector;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class UpdateCreditCard extends HttpServlet{

	//UpdateBarang
	
	public UpdateCreditCard() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer quantity =  Integer.parseInt(request.getParameter("quantity"));
		Integer idItem =  Integer.parseInt(request.getParameter("idItem"));
		String desc =  request.getParameter("desc");
		Integer stock =  Integer.parseInt(request.getParameter("stock"));
		Integer price =  Integer.parseInt(request.getParameter("price"));
		
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("", ""));
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		PrintWriter out = response.getWriter();
		String[] dataregister = new String[10];
		dataregister[0] = request.getParameter("nama2");
		dataregister[1] = "0";
		dataregister[2] = request.getParameter("uname");
		dataregister[3] = request.getParameter("pwd2");
		dataregister[4] = request.getParameter("email2");
		dataregister[5] = request.getParameter("nohp2");
		dataregister[6] = request.getParameter("alamat2");
		dataregister[7] = request.getParameter("provinsi2");
		dataregister[8] = request.getParameter("kota2");
		dataregister[9] = request.getParameter("kodepos2");
		
		dataregister[0] = dataregister[0].replace("$", " ");
		dataregister[1] = dataregister[1].replace("$", " ");
		dataregister[2] = dataregister[2].replace("$", " ");
		dataregister[3] = dataregister[3].replace("$", " ");
		dataregister[4] = dataregister[4].replace("$", " ");
		dataregister[5] = dataregister[5].replace("$", " ");
		dataregister[6] = dataregister[6].replace("$", " ");
		dataregister[7] = dataregister[7].replace("$", " ");
		dataregister[8] = dataregister[8].replace("$", " ");
		dataregister[9] = dataregister[9].replace("$", " ");
		
		//Integer quantity =  request.getParameter("quantity");
		
		//quantity = Integer.parseInt(request.getParameter("quantity"));
		//idItem = Integer.parseInt(request.getParameter("id"));
		//desc = request.getParameter("desc");
		//stock = Integer.parseInt(request.getParameter("stock"));
		//price = Integer.parseInt(request.getParameter("price"));
		
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("", ""));
		
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from pengguna where username ='"+dataregister[2]+"'");
			rs.next();
			ArrayList<Integer> change = new ArrayList<Integer>();
			int first=0;
			String query="UPDATE pengguna SET ";
			if(!rs.getString("nama_pengguna").equals(dataregister[0])) {if(first==0)first++;else query+=",";query+="nama_pengguna='"+dataregister[0]+"'";}else change.add(0);
			if(!rs.getString("password").equals(dataregister[3])) {if(first==0)first++;else query+=",";query+="password='"+dataregister[3]+"'";}else change.add(3);
			if(!rs.getString("email").equals(dataregister[4])) {if(first==0)first++;else query+=",";query+="email='"+dataregister[4]+"'";}else change.add(4);
			if(!rs.getString("nomor_hp").equals(dataregister[5])) {if(first==0)first++;else query+=",";query+="nomor_hp='"+dataregister[5]+"'";}else change.add(5);
			if(!rs.getString("alamat").equals(dataregister[6])) {if(first==0)first++;else query+=",";query+="alamat='"+dataregister[6]+"'";}else change.add(6);
			if(!rs.getString("provinsi").equals(dataregister[7])){if(first==0)first++;else query+=",";query+="provinsi='"+dataregister[7]+"'";}else change.add(7);
			if(!rs.getString("kota_kabupaten").equals(dataregister[8])){if(first==0)first++;else query+=",";query+="kota_kabupaten='"+dataregister[8]+"'";}else change.add(8);
			if(!rs.getString("kode_pos").equals(dataregister[9])){if(first==0)first++;else query+=",";query+="kode_pos='"+dataregister[9]+"'";}else change.add(9);
			query+=" WHERE username='"+dataregister[2]+"'";
			statement.executeUpdate(query);
			//rs = statement.executeQuery("select * from pengguna where username ='"+dataregister[2]+"'");
			//rs.next();
			String prints="";
			for(int d : change){
				switch(d){
				case 0: prints+="nama_pengguna\n";break;
				case 3: prints+="password\n";break;
				case 4: prints+="email\n";break;
				case 5: prints+="nomor_hp\n";break;
				case 6: prints+="alamat\n";break;
				case 7: prints+="provinsi\n";break;
				case 8: prints+="kota_kabupaten\n";break;
				case 9: prints+="kodepos\n";break;
				}
			}
			//String result = rs.getString("username")+rs.getString("nama_pengguna")+rs.getString("password")+rs.getString("email")+rs.getString("nomor_hp")+rs.getString("alamat")+rs.getString("provinsi")+rs.getString("kota_kabupaten")+rs.getString("kode_pos");
			//out.print(prints);
			response.getWriter().print("{"
					+ "\"status\": 500"
					+ ",\"detail\": \"OK\""
					+ ",\"content\":\""+prints+"null\""
					+ "}");
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().print("{"
					+ "\"status\": 200"
					+ ",\"detail\": \"Error\""
					+ ",\"content\":\"null\""
					+ "}");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
