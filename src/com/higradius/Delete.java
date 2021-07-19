package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class InsertData
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/schemadatabase";
	private static final String USER = "root";
	private static final String PASSWORD = "shreyA@1999";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
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
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL,USER,PASSWORD);
			// Create a SQL query to insert data into demo table 

			          invoice_id=  request.getParameter("invoice_id");
			       
				         
			        
						PreparedStatement st = con 
							.prepareStatement("delete from mytable where invoice_id='"+invoice_id+"' "); 
						

						// For the first parameter, 
						// get the data using request object 
						// sets the data to st pointer 
						//st.setInt(1, Integer.valueOf(request.getParameter("id"))); 

						// Same for second parameter 
					/*	st.setString(1, request.getParameter("name_customer"));
						st.setString(2, request.getParameter("cust_number")); 
						st.setString(3, request.getParameter("invoice_id")); 
						st.setString(4, request.getParameter("total_open_amount")); 
						st.setString(5, request.getParameter("due_in_date")); 
						st.setString(7, request.getParameter("notes")); */

						// Execute the insert command using executeUpdate() 
						// to make changes in database 
						st.executeUpdate(); 

						// Close all the connections 
						st.close(); 
						con.close(); 

						// Get a writer pointer 
						// to display the successful result 
						PrintWriter out = response.getWriter(); 
						out.println("<html><body><b>Successfully deleted"
									+ "</b></body></html>"); 
		}
		catch(Exception ex){
			PrintWriter out = response.getWriter(); 
			out.println("<html><body><b>Successfully not deleted"
						+ex+ "</b></body></html>"); 
			//ex.printStackTrace();
		}
		
		doGet(request, response);
	}

}
