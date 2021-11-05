package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.model.Order;
import com.hotel.model.Product;

public class AdminDatabase {
	  private Connection con ;
	  private String query;
	  private PreparedStatement ps;
	  private ResultSet rs;

	    public  AdminDatabase(Connection con) {
	        this.con = con;
	    }
	    
	    //for register user 
	    public boolean saveAdmin(Admin_Logindao admin){
	        boolean set = false;
	        try{
	            //Insert register data to database
	            String query = "insert into ar_admin(id,password) values(?,?)";
	           
	           PreparedStatement pt = this.con.prepareStatement(query);
	           pt.setString(1, admin.getUserName());
	           pt.setString(3, admin.getPassword());
	           
	           
	           pt.executeUpdate();
	           set = true;
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return set;
	    }
	    
	    //For Admin Login into Website to check orders
	    public Admin_Logindao login(String userName, String password){
	        Admin_Logindao adminLogin=null;
	        try{
	            String query ="select * from ar_admin where id=? and password=?";
	            PreparedStatement pst = this.con.prepareStatement(query);
	            pst.setString(1, userName);
	            pst.setString(2, password);
	            
	            ResultSet rs = pst.executeQuery();
	            
	            if(rs.next()){
	                adminLogin = new Admin_Logindao();
	                adminLogin.setUserName(rs.getString("id"));
	                adminLogin.setPassword(rs.getString("password"));
	                
	            }
	            
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return adminLogin;
	    }
	    


	    }


