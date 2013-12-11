package helloJsp.controller;

import helloJsp.object.Item;
import helloJsp.object.ShoppingCart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Cart extends HttpServlet {

	private static String DOMAIN = "http://127.0.0.1:8080/Chintalian";
	//private static String DOMAIN = "http://tokokita.ap01.aws.af.cm";

	public Cart() {
		// TODO Auto-generated constructor stub
		super();
	}

	private static String getResponse(WebResource service) {
		return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
	}

	private static String getOutputAsXML(WebResource service) {
		return service.accept(MediaType.TEXT_XML).get(String.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int quantity = 0, idItem = 0, stock = 0, price = 0, type = 0;
		String desc = "";

		type = Integer.parseInt(request.getParameter("type"));
		HttpSession session = request.getSession();

		if (type == 1) {
			quantity = Integer.parseInt(request.getParameter("quantity"));
			idItem = Integer.parseInt(request.getParameter("id"));
			desc = request.getParameter("desc");
			stock = Integer.parseInt(request.getParameter("stock"));
			price = Integer.parseInt(request.getParameter("price"));

			if (stock < quantity) {
				// fail
				out.println(-1);
			} else {
				try {
					ShoppingCart sc = new ShoppingCart();
					if (session.getAttribute("shoppingCart") != null)
						sc = (ShoppingCart) session.getAttribute("shoppingCart");
					sc.getItems().add(new Item(quantity, idItem, price, desc));
					if (session.getAttribute("shoppingCart") != null)
						session.removeAttribute("shoppingCart");
					session.setAttribute("shoppingCart", sc);
					out.println(1);
				} catch (Exception x) {
					out.println(0);
				}
			}
		} else if (type == 2) {
			ShoppingCart sc = new ShoppingCart();
			idItem = Integer.parseInt(request.getParameter("id"));
			if (session.getAttribute("shoppingCart") != null) {
				sc = (ShoppingCart) session.getAttribute("shoppingCart");
				sc.getItems().remove(idItem);
				if (session.getAttribute("shoppingCart") != null)
					session.removeAttribute("shoppingCart");
				session.setAttribute("shoppingCart", sc);
				out.println(idItem);
			} else
				out.println(-1);
		} else if (type == 3) {
			ShoppingCart sc = new ShoppingCart();
			boolean transactionFinished = false;
			if (session.getAttribute("shoppingCart") != null) {
				sc = (ShoppingCart) session.getAttribute("shoppingCart");
				ArrayList<Integer> arr = new ArrayList<Integer>();
				for (int i = 0; i < sc.getItems().size(); i++) {
					// syntax
					HttpClient client = new DefaultHttpClient();
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					System.out.println(String.valueOf(sc.getItems().get(i).getQuantity()));
					list.add(new BasicNameValuePair("quantity", String.valueOf(sc.getItems().get(i).getQuantity())));
					list.add(new BasicNameValuePair("idItem", String.valueOf(sc.getItems().get(i).getIdItem())));
					list.add(new BasicNameValuePair("desc", String.valueOf(sc.getItems().get(i).getDescription())));
					list.add(new BasicNameValuePair("price", String.valueOf(sc.getItems().get(i).getPrice())));

					HttpGet httpGet = new HttpGet(DOMAIN+"/UpdateCreditCard?quantity="+String.valueOf(sc.getItems().get(i).getQuantity())+"&idItem="+String.valueOf(sc.getItems().get(i).getIdItem())+"&desc="+String.valueOf(sc.getItems().get(i).getDescription())+"&price="+String.valueOf(sc.getItems().get(i).getPrice()));
					
					HttpResponse response2 = client.execute(httpGet);

					// Get the response
					BufferedReader rd = new BufferedReader
					  (new InputStreamReader(response2.getEntity().getContent()));
					    
					String line = "";
					StringBuffer textView = new StringBuffer();
					while ((line = rd.readLine()) != null) {
					  textView.append(line);
					}
					
					//parsing json
					System.out.println(textView.toString());
			        JSONTokener jsonTokener = new JSONTokener(textView.toString());
			        JSONObject jsonObject = new JSONObject(jsonTokener);
			        String konten = jsonObject.getString("content");
			        Integer status = (Integer) jsonObject.get("status");
			        if(status == 500){
				        out.print(1);
			        } else{
			        	out.print(0);
			        }
					
					arr.add(i);
					String query = "";
					if (i == sc.getItems().size() - 1)
						transactionFinished = true;
				}
				if (!transactionFinished) {
					for (int j = 0; j < arr.size(); j++) {
						sc.getItems().remove(arr.get(j));
					}
				}
				if (session.getAttribute("shoppingCart") != null)
					session.removeAttribute("shoppingCart");
				if (transactionFinished) {
					HttpClient client = new DefaultHttpClient();
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					
					String username = "genta";
					if(session.getAttribute("user") != null)
						username = session.getAttribute("user").toString();
					HttpGet request2 = new HttpGet(DOMAIN+"/UpdateTotalTransaksi?username="+username);
					HttpResponse response2 = client.execute(request2);

					// Get the response
					BufferedReader rd = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
					
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
				        out.print(1);
			        } else{
			        	out.print(0);
			        }
				} else
					out.println(0);
			} else {
				out.println(-1);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
