package helloJsp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class Validasi
 */
public class Validasi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String DOMAIN = "http://127.0.0.1:8080/Chintalian";
	//private static String DOMAIN = "http://tokokita.ap01.aws.af.cm";
       
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
					HttpClient client = new DefaultHttpClient();
					HttpGet request2 = new HttpGet(DOMAIN+"/GetUser?data="+data+"&type=1");
					HttpResponse response2 = client.execute(request2);
					regex = "[A-Za-z0-9]{5,20}";
					
					// Get the response
					BufferedReader rd = new BufferedReader
					  (new InputStreamReader(response2.getEntity().getContent()));
					    
					String line = "";
					StringBuffer textView = new StringBuffer();
					while ((line = rd.readLine()) != null) {
					  textView.append(line);
					}
					
					//parsing json
			        JSONTokener jsonTokener = new JSONTokener(textView.toString());
			        JSONObject jsonObject = new JSONObject(jsonTokener);
			        String konten = jsonObject.getString("content");
			        Integer status = (Integer) jsonObject.get("status");
			        if(status != 500){
			        	if(Pattern.matches(regex, data) && data!=pass)out.print(0);
						else if(data.equals(pass))out.print(2);
						else out.print(3);
			        } else{
			        	out.print(1);
			        }
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
					client = new DefaultHttpClient();
					request2 = new HttpGet(DOMAIN+"/GetUser?data="+data+"&type=3");
					HttpResponse response3 = client.execute(request2);
					regex = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})";
					
					// Get the response
					BufferedReader rd2 = new BufferedReader
					  (new InputStreamReader(response3.getEntity().getContent()));
					    
					String line2 = "";
					StringBuffer textView2 = new StringBuffer();
					while ((line = rd2.readLine()) != null) {
					  textView2.append(line);
					}
					
					//parsing json
			        JSONTokener jsonTokener2 = new JSONTokener(textView2.toString());
			        JSONObject jsonObject2 = new JSONObject(jsonTokener2);
			        //String konten2 = jsonObject2.getString("content");
			        Integer status2 = (Integer) jsonObject2.get("status");
			        if(status2 != 500){
			        	if (data.matches(regex) && !data.equals(pass)) {
							out.print(0);
						} else { 
							out.print(1);
						}
			        } else
			        	out.print(1);
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
