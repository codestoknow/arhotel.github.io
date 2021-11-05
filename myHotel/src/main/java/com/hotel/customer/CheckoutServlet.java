package com.hotel.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.dao.ConnectionPro;
import com.hotel.dao.OrderDao;
import com.hotel.dao.User;
import com.hotel.model.Cart;
import com.hotel.model.Order;


@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			Date d=new Date();
			
			//retrive all cart products from cart
			ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			//User authentication
			 User user = (User) request.getSession().getAttribute("login");
			 
			 //check user and cartlist
			 if(cart_list !=null && user!=null) {
				 
				 for(Cart c:cart_list) {
					 Order order=new Order();
					 order.setId(c.getId());
					 order.setUid(user.getId());
					 order.setQuantity(c.getQuantity());
					 order.setDate(formatter.format(d));
					 order.setCustomerName(user.getName());
					 order.setMobileNo(user.getMobileNo());
					 order.setAddress(user.getAddress());
					 
					 //instantiate orderdao
					 OrderDao odao=new OrderDao(ConnectionPro.getConnection());
					 //calling the insert method
					boolean result= odao.insertOrder(order);
					 if(!result)
					 break;
				 }
				cart_list.clear();
				 response.sendRedirect("orders.jsp");
			 }else {
				 if(user==null) response.sendRedirect("customer_Login.jsp");
				 response.sendRedirect("orders.jsp");
			 }

		} catch (Exception e) {
           e.printStackTrace();
}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
