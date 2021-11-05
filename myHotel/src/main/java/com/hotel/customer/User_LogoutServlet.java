package com.hotel.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/User_LogoutServlet")
public class User_LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>RegistrationServlet </title>");
			pw.println("</head>");
			pw.println("<body>");
			HttpSession session = request.getSession();
            session.removeAttribute("login");
            response.sendRedirect("customer_Login.jsp");
            
            HttpSession session2=request.getSession();
            session.removeAttribute("admin");
            response.sendRedirect("admin.jsp");
			pw.println("</body>");
			pw.println("</html>");

	}catch (Exception e) {
e.printStackTrace();
}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
