package com.hotel.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.model.Cart;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try {
			PrintWriter pw=response.getWriter();
			
			ArrayList<Cart> cartList= new ArrayList<Cart>();
			int id=Integer.parseInt(request.getParameter("id"));
			
			//object creation and set data into it
			Cart crt=new Cart();
			crt.setId(id);
			crt.setQuantity(1);
			
			//session object
			HttpSession session=request.getSession();
			ArrayList<Cart> cart_list= (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list==null) {
				cartList.add(crt);
				session.setAttribute("cart-list", cartList);
                response.sendRedirect("welcome.jsp");
			}else {
                cartList=cart_list;
                boolean exist=false;
                
                
				  for(Cart c:cart_list) { 
                   if(c.getId()==id) {
	                  exist=true;
	                  pw.println("<h3 style='color:crimson; text-align:center'>Item Already added <a href='cart.jsp'>Add other Items</a></h3>");
                   }
			   }
				  if(!exist) {
                	  cartList.add(crt);
                      response.sendRedirect("welcome.jsp");
                  }
			}
			for(Cart c:cart_list) {
				pw.println(c.getId());
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
