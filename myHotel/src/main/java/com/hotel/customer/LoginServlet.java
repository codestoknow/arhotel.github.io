


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
import com.mysql.cj.log.Log;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>Login Servlet </title>");
		pw.println("</head>");
		pw.println("<body>");
		
		String log_email = request.getParameter("email");
		String log_password = request.getParameter("password");

		//create a database model
		UserDatabase db = new UserDatabase(ConnectionPro.getConnection());
	
		User user=db.login(log_email, log_password);
		
		if(user!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("login",user);
			response.sendRedirect("welcome.jsp");

		}else {
			pw.println("User  Found "+"<br> Please register <a href='home.jsp'>Register</a><br><a href='customer_Login.jsp'>LogIn Again</a>");
		}
		pw.println("</body>");
		pw.println("</html>");


	}catch(Exception e) {
e.printStackTrace();	
}
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
