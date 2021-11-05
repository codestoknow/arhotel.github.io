package com.hotel.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.dao.ConnectionPro;
import com.hotel.dao.OrderDao;

@WebServlet("/CancelCustomerOrderServlet")
public class CancelCustomerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	try {
		String id=request.getParameter("id");
		if (id!=null) {
			OrderDao orderDao=new OrderDao(ConnectionPro.getConnection());
			orderDao.adminCancelOrder(Integer.parseInt(id));
		}
		response.sendRedirect("customer_orders_detail.jsp");
		
		
		
	} catch (Exception e) {
 e.printStackTrace();	
 }

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
