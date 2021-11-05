package com.hotel.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.dao.AdminDatabase;
import com.hotel.dao.Admin_Logindao;
import com.hotel.dao.ConnectionPro;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	response.setContentType("text/html");
	
	String id=request.getParameter("id");
	String password=request.getParameter("password");
	
	AdminDatabase adb=new AdminDatabase(ConnectionPro.getConnection());
	
	Admin_Logindao adminDao=adb.login(id, password);
	if(adminDao!=null) {
		HttpSession session2=request.getSession();
		session2.setAttribute("admin", adminDao);
		response.sendRedirect("customer_orders_detail.jsp");
	}else {
		out.println("User Not Found "+"<br> Please Contact Admin <a href='home.jsp'>Back</a><br><a href='admin.jsp'>LogIn Again</a>");

	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
