package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.Order;
import com.hotel.model.Product;


public class OrderDao {
private Connection con;
private String query;
private PreparedStatement ps;
private ResultSet rs;


public OrderDao(Connection con) {
	this.con=con;
}

//insert order data into orders table from cart.jsp once checkout or buy now button clicked
public boolean insertOrder(Order model) {
	boolean result=false;
	
	try {
		query="insert into orders(p_id,u_id,o_quantity,o_date,customer_name,customer_mobile,customer_address) values(?,?,?,?,?,?,?)";
		
		ps=this.con.prepareStatement(query);
		
		ps.setInt(1, model.getId());
		ps.setInt(2, model.getUid());
		ps.setInt(3, model.getQuantity());
		ps.setString(4, model.getDate());
		ps.setString(5, model.getCustomerName());
		ps.setString(6, model.getMobileNo());
		ps.setString(7, model.getAddress());
		ps.executeUpdate();
		
		result=true;
		
	}catch (Exception e) {
e.printStackTrace();	
}
	return result;
}


//Show  detailed order form after checkout
public List<Order> userOrders(int id){
	
	List<Order> list=new ArrayList<Order>();
	
	try {
		query="select * from orders where u_id=? order by orders.o_id desc";
		ps=this.con.prepareStatement(query);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		
		while(rs.next()) {
			Order order=new Order();
			ProductDAO productDao=new ProductDAO(this.con);
			int pid=rs.getInt("p_id");
			Product product =productDao.getSingleProduct(pid);
			order.setOrderId(rs.getInt("o_id"));
			order.setId(pid);
			order.setName(product.getName());
			order.setCatagory(product.getCatagory());
			order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
			order.setQuantity(rs.getInt("o_quantity"));
			order.setDate(rs.getString("o_date"));
			
			list.add(order);
		}
	}catch (Exception e) {
e.printStackTrace();	}
	return list;
	
}

//Cancel order
public void cancelOrder(int id) {
	try {
		query="delete from orders where o_id=?";
		ps=this.con.prepareStatement(query);
		ps.setInt(1, id);
		ps.execute();
		
	}catch (Exception e) {
e.printStackTrace();
}
}


/////////////////////////////////////////////////     FOR ADMIN     //////////////////

//show all delivery orders to admin
public List<Order> receivedOrders(){
	List<Order> allOrders=new ArrayList<Order>();
	
	try {
		query="select * from orders";
		ps=this.con.prepareStatement(query);

		rs=ps.executeQuery();
		
		while(rs.next()) {
			Order o= new Order();
			ProductDAO productDao=new ProductDAO(this.con);
			int pid=rs.getInt("p_id");

			Product product = productDao.getSingleProduct(pid);
			o.setOrderId(rs.getInt("o_id"));
			o.setId(pid);
			o.setUid(rs.getInt("u_id"));
			o.setQuantity(rs.getInt("o_quantity"));
			o.setDate(rs.getString("o_date"));
			o.setCustomerName(rs.getString("customer_name"));
			o.setMobileNo(rs.getString("customer_mobile"));
			o.setAddress(rs.getString("customer_address"));
			o.setName(product.getName());
            
            allOrders.add(o);
		}
	}catch (Exception e) {
       e.printStackTrace();	
}
	return allOrders;
	
}

//Cancel order
public void adminCancelOrder(int id) {
	try {
		query="delete from orders where o_id=?";
		ps=this.con.prepareStatement(query);
		ps.setInt(1, id);
		ps.execute();
		
	}catch (Exception e) {
e.printStackTrace();
}
}


}
