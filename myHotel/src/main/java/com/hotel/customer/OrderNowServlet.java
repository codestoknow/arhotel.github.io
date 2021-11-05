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
import com.hotel.model.Product;


@WebServlet("/OrderNowServlet")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	try {
		PrintWriter out=response.getWriter();
		
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		
		Date d=new Date();
           User auth= (User) request.getSession().getAttribute("login");
	            if(auth !=null) {
	            	String productId=request.getParameter("id");
	            	int productQuantity=Integer.parseInt(request.getParameter("quantity"));
	            	String productName=request.getParameter("pname");
	            	
	            	if(productQuantity<=0) {
	            	  productQuantity =1;
	            	}
	            	 
	            	Order orderModel=new Order();
	            	orderModel.setId(Integer.parseInt(productId));
	            	orderModel.setUid(auth.getId());
	            	orderModel.setQuantity(productQuantity);
	            	orderModel.setDate(formatter.format(d));
	            	orderModel.setCustomerName(auth.getName());
	            	orderModel.setMobileNo(auth.getMobileNo());
	            	orderModel.setAddress(auth.getAddress());

	            
	                
	            	OrderDao odao=new OrderDao(ConnectionPro.getConnection());
	            	boolean result =odao.insertOrder(orderModel);
	            	
	            	if (result) {
	            		ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
	        			if(cart_list !=null) {
	        				for(Cart c:cart_list) {
	        					if(c.getId()==Integer.parseInt(productId)) {
	        						cart_list.remove(cart_list.indexOf(c));
	        						break;
	        					}
	        				}
	        			}
						response.sendRedirect("orders.jsp");
					} else {

						out.println("Order failed");
					}

	            }else {
	            	response.sendRedirect("login.jsp");
	            }
	
	}catch (Exception e) {
e.printStackTrace();
}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
