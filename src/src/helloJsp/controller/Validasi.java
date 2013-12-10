package helloJsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class Validasi
 */

public class Validasi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validasi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String pass,regex,query;
		String data = request.getParameter("q");
		int type =  Integer.parseInt(request.getParameter("num"));
		pass =request.getParameter("pass");
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null){
			DbConnector dbconnector = new DbConnector();
			Connection connection = dbconnector.mySqlConnection();
			try{
				Statement statement = connection.createStatement();
				switch(type){
				case 1://Full name validator 
					try {
					    Thread.sleep(2000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					regex = "([A-Za-z]{1,10})+(\\s[A-Za-z]{1,20})+";
					if(Pattern.matches(regex, data))out.print(0);
					else out.print(1);
				break;
				case 2://username validator
					try {
					    Thread.sleep(2000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					query = "SELECT * FROM pengguna WHERE username = '"+data+"'";
					regex = "[A-Za-z0-9]{5,20}";
					ResultSet rs = statement.executeQuery(query);
					if(!rs.next()){
						if(Pattern.matches(regex, data) && data!=pass)out.print(0);
						else if(data.equals(pass))out.print(2);
						else out.print(3);}
					else out.print(1);
				break;
				case 3://password validator
					try {
					    Thread.sleep(1000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					if(data.length()>7  && !data.equals(pass)){out.print(0);}
					else 
						{	
							if(data.equals(pass)){out.print(1);}
							else out.print(2);
						}
				break;
				case 4://copassword validator
					try {
					    Thread.sleep(2000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					if(pass.equals(data)){out.print(0);}
					else out.print(1);
				break;
				case 5://email validator
					try {
					    Thread.sleep(2000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					query = "SELECT * FROM pengguna WHERE email = '"+data+"'";
					ResultSet rss = statement.executeQuery(query);
					regex = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})";
					if (data.matches(regex) && !data.equals(pass)&&!rss.next()) {
						out.print(0);
					} else { 
						out.print(1);
					}
				break;
				default:
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
