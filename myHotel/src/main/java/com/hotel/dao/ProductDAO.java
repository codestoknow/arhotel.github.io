package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.Cart;
import com.hotel.model.Product;

public class ProductDAO {
  private Connection con;
  private String query;
  private PreparedStatement ps;
  private ResultSet rs;
  
  
public ProductDAO(Connection con) {
	this.con=con;
}

public List<Product> getAllProducts(){
	List<Product> products=new ArrayList<Product>();
	
	try {
		query="select * from products";
		ps=this.con.prepareStatement(query);
		rs=ps.executeQuery();
		
		while(rs.next()) {
			Product p= new Product();
			p.setId(rs.getInt("id"));
			p.setCatagory(rs.getString("catagory"));
			p.setName(rs.getString("name"));
            p.setPrice(rs.getDouble("price"));
            p.setImage(rs.getString("image"));
            
            products.add(p);
		}
	}catch (Exception e) {
       e.printStackTrace();	
}
	return products;
	
}
  
 public List<Cart> getCartProducts(ArrayList<Cart> cartList){
	 ArrayList<Cart> products=new ArrayList<Cart>();
	 
	 try {
		 if(cartList.size()>0) {
			 for(Cart item:cartList) {
				 query="select * from products where id=?";
				 ps=this.con.prepareStatement(query);
				 ps.setInt(1, item.getId());
				 rs=ps.executeQuery();
				 
				 while(rs.next()) {
					 
					 Cart c=new Cart();
					    c.setId(rs.getInt("id"));
					    c.setCatagory(rs.getString("catagory"));
						c.setName(rs.getString("name"));
			            c.setPrice(rs.getDouble("price")*item.getQuantity());
			            c.setQuantity(item.getQuantity());
			            products.add(c);
			            
				 }
			 }
		 }
	 }catch (Exception e) {
e.printStackTrace();
}
	return products;
	 
 }
 public Product getSingleProduct(int id) {
	 Product row=null;
	 try {
		 query="select * from products where id=?";
		 ps=this.con.prepareStatement(query);
		 ps.setInt(1, id);
		 rs=ps.executeQuery();
		 
		 while (rs.next()) {
			 row=new Product();
			 row.setId(rs.getInt("id"));
			 row.setName(rs.getString("name"));
			 row.setCatagory(rs.getString("catagory"));
			 row.setPrice(rs.getDouble("price"));
			 row.setImage(rs.getString("image"));
			
		}
		 
	 }catch (Exception e) {
e.printStackTrace();	}
	 
	return row;
	 
}
 
 
 public double getTotalCartPrice(ArrayList<Cart> cartList) {
	 double sum=0;
	 
	 try {
		 if(cartList.size()>0) {
			 for(Cart item:cartList) {
					query="select price from products where id=?";
                    
					ps=this.con.prepareStatement(query);
					ps.setInt(1, item.getId());
					rs=ps.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
					}
			 }
		 }
	} catch (Exception e) {
e.printStackTrace();
}
	return sum;
	 
 }
 
}
