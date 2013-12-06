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


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			ResultSet rs = statement.executeQuery("select * from pengguna where username ='"+username+"'and password ='"+password+"'"); 
			if(rs.next()){
				session.setAttribute("user", username);
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(1800);
				Cookie userName = new Cookie("user", username);
				userName.setMaxAge(30*60);
				response.addCookie(userName);
				response.sendRedirect("LoginSuccessful.jsp");
			}else{
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
