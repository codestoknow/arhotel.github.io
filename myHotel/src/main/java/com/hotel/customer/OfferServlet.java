package com.hotel.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MenuServlet")
public class OfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement ps;

 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 
		try {
	
			//register driver
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
			ps=con.prepareStatement("select * from ar_menu_veg");
			ResultSet rs=ps.executeQuery();
			
			    PrintWriter out=response.getWriter();
				response.setContentType("text/html");
				
				out.println("<html><body><table border=1><tr><td>No</td><td>Item Name</td><td>Item Price</td>");
				while(rs.next()) {
				
				out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
				}
				  
				out.println("</table></body></html>");
				
				out.println("<br><a href='welcome.jsp'>Lets Order</a>");

		}catch (Exception e) {
          e.printStackTrace();
}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
