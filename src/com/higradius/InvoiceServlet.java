package com.higradius;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class InvoiceServlet
 */
@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/schemadatabase";
	private static final String USER = "root";
	private static final String PASSWORD = "shreyA@1999";
	
	
	
	
	
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;
		String name_customer;
		String cust_number;
		String invoice_id;
		String total_open_amount;
		String due_in_date;
		String predicted_payment_date;
		String notes;
		List<InvoicePojo> responseList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
			stmt = conn.createStatement();
			int pageno = Integer.valueOf(request.getParameter("page"));
			sql = "select name_customer,cust_number,invoice_id,total_open_amount,due_in_date,predicted_payment_date,notes from mytable order by invoice_id limit "+(pageno-1)*11+","+11;
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				InvoicePojo pojoResponse = new InvoicePojo();
				name_customer = rs.getString("name_customer");
				cust_number = rs.getString("cust_number");
				invoice_id = rs.getString("invoice_id");
				total_open_amount = rs.getString("total_open_amount");
				due_in_date = rs.getString("due_in_date");
				predicted_payment_date = rs.getString("predicted_payment_date");
				notes = rs.getString("notes");
				pojoResponse.setName_customer(name_customer);
				pojoResponse.setCust_number(cust_number);
				pojoResponse.setInvoice_id(invoice_id);
				pojoResponse.setTotal_open_amount(total_open_amount);
				pojoResponse.setDue_in_date(due_in_date);
				pojoResponse.setPredicted_payment_date(predicted_payment_date);
				pojoResponse.setNotes(notes);
				responseList.add(pojoResponse);
			}
			//Gson is a Java library that can be used to convert Java Objects into their JSON representation.
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(responseList);
			System.out.println(json);
			response.setContentType("application/json");
			response.getWriter().write(json);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
//http://localhost:3000
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
