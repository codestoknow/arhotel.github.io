package com.hotel.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.dao.ConnectionPro;
import com.hotel.dao.User;
import com.hotel.dao.UserDatabase;


@MultipartConfig
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>Login Servlet </title>");
		pw.println("</head>");
		pw.println("<body>");
		
		String userEmail = request.getParameter("email");
		String userName = request.getParameter("name");
		String userpassword = request.getParameter("password");
		String userMobileno=request.getParameter("mobilenumber");
		String userAddress=request.getParameter("address");
	

		//Session object
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("login");
		
		user.setEmail(userEmail);
		user.setName(userName);
		user.setPassword(userpassword);
		user.setMobileNo(userMobileno);
		user.setAddress(userAddress);

		//update database
		UserDatabase db = new UserDatabase(ConnectionPro.getConnection());
	
	     boolean updateDone=db.updateUser(user);
	     if(updateDone) {
	    	 pw.println("profile updated");
	    	 pw.println("<a href='welcome.jsp'>Back</a>");
	     }else {
	    	 pw.println("Not updated");
	    	 
	     }
		
		pw.println("</body>");
		pw.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
