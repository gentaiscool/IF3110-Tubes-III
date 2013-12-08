package helloJsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String REST_URI = "http://localhost:8080/Chintalian";
    static final String GET_USER = "/GetUser";
    static final String LOGIN = "/login";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private static String getResponse(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
    }
 
    private static String getOutputAsXML(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(String.class);
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DbConnector dbconnector = new DbConnector();
		Connection connection = dbconnector.mySqlConnection();
		HttpSession session = request.getSession();
		PrintWriter out= response.getWriter();
		try{
			Statement statement = connection.createStatement();
		    
			ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        WebResource service = client.resource(REST_URI);
	        WebResource addService = service.path("rest").path(GET_USER+LOGIN+"/"+username+"&"+password);
	        
	        //parsing json
	        out.println("0");
	        JSONTokener jsonTokener = new JSONTokener(getOutputAsXML(addService));
	        out.println("haha");
	        JSONObject jsonObject = new JSONObject(jsonTokener);
	        out.println("1");
	        JSONObject content = (JSONObject) jsonObject.get("content");
	        Integer status = (Integer) jsonObject.get("status");
	        
	        if(status == 200){
		        Integer id_pengguna = Integer.parseInt((content.get("id_pengguna").toString()));
		        String nama_pengguna = (String) content.get("nama_pengguna");
	        
		        session.setAttribute("user", username);
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(1800);
				Cookie userName = new Cookie("user", username);
				//userName.setMaxAge(30*60);
				response.addCookie(userName);
				response.sendRedirect("LoginSuccessful.jsp");
	        } else{
	        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(request, response);
	        }
		}catch(Exception e){
			out.println("Ch si");
			e.printStackTrace();
		}
	}
}
