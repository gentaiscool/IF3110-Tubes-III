package helloJsp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

/**
 * Servlet implementation class CardValidation
 */

public class CardValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String DOMAIN = "http://127.0.0.1:8080/Chintalian";
	//private static String DOMAIN = "http://tokokita.ap01.aws.af.cm";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String names, regex, query, expired;
		String cardnum;
		boolean bcardnum = false, bname = false;
		cardnum = request.getParameter("cardnumber");
		names = request.getParameter("names");
		names = names.replace(' ', '*');
		expired = request.getParameter("expired");

		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			String user = (String) session.getAttribute("user");
			user = user.replace(' ', '*');
			HttpClient client = new DefaultHttpClient();
			HttpGet request2 = new HttpGet(DOMAIN+"/CheckCardValidation?user="+user+"&names="+names+"&expired="+expired+"&cardnum="+cardnum);
			System.out.println(DOMAIN+"/CheckCardValidation?user="+user+"&names="+names+"&expired="+expired+"&cardnum="+cardnum);
			HttpResponse response2 = client.execute(request2);
		
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
	        if(status == 500){
	        	out.print("valid");
	        } else{
	        	out.print("invalid");
	        }
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
