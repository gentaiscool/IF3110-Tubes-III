package helloJsp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
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
 * Servlet implementation class UpdateProfile
 */

public class UpdateProfile extends HttpServlet {
	static final String REST_URI = "http://localhost:8080/Chintalian";
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
		dataregister[0] = dataregister[0].replace(" ", "$");
		dataregister[1] = "0";
		dataregister[2] = request.getParameter("uname");
		dataregister[2] = dataregister[2].replace(" ", "$");
		dataregister[3] = request.getParameter("pwd2");
		dataregister[3] = dataregister[3].replace(" ", "$");
		dataregister[4] = request.getParameter("email2");
		dataregister[4] = dataregister[4].replace(" ", "$");
		dataregister[5] = request.getParameter("nohp2");
		dataregister[5] = dataregister[5].replace(" ", "$");
		dataregister[6] = request.getParameter("alamat2");
		dataregister[6] = dataregister[6].replace(" ", "$");
		dataregister[7] = request.getParameter("provinsi2");
		dataregister[7] = dataregister[7].replace(" ", "$");
		dataregister[8] = request.getParameter("kota2");
		dataregister[8] = dataregister[8].replace(" ", "$");
		dataregister[9] = request.getParameter("kodepos2");
		dataregister[9] = dataregister[9].replace(" ", "$");
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request2 = new HttpGet("http://127.0.0.1:8080/Chintalian/UpdateUser?nama2="+dataregister[0]+"&uname="+dataregister[2]
		+"&pwd2="+dataregister[3]+"&email2="+dataregister[4]+"&nohp2="+dataregister[5]+"&alamat2="+dataregister[6]
				+"&provinsi2="+dataregister[7]+"&kota2="+dataregister[8]+"&kodepos2="+dataregister[9]);
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
        PrintWriter out = response.getWriter();
        if(status == 200){
	        out.print(konten);
        } else{
        	out.print("");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
