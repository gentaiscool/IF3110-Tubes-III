package helloJsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateProfile
 */

public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
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
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		PrintWriter out = response.getWriter();
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
			out.print(prints);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
