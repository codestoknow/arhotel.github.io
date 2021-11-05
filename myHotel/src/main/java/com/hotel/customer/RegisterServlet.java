package com.hotel.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.dao.ConnectionPro;
import com.hotel.dao.User;
import com.hotel.dao.UserDatabase;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			PrintWriter pw=response.getWriter();
			
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>RegistrationServlet </title>");
			pw.println("</head>");
			pw.println("<body>");
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String mobileNo=request.getParameter("mobilenumber");
			String address=request.getParameter("address");
			//make user object
			User userModel = new User(name, email, password,mobileNo,address);

			//create a database model
			UserDatabase regUser = new UserDatabase(ConnectionPro.getConnection());
			if (regUser.saveUser(userModel)) {
			   response.sendRedirect("customer_Login.jsp");
			} else {
			    String errorMessage = "User not Available";
			    HttpSession regSession = request.getSession();
			    regSession.setAttribute("RegError", errorMessage);
			    response.sendRedirect("home.jsp");
			    }
			pw.println("</body>");
			pw.println("</html>");

		}catch (Exception e) {
e.printStackTrace();
}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
