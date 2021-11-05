package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDatabase {
  private Connection con ;

    public UserDatabase(Connection con) {
        this.con = con;
    }
    
    //for register user 
    public boolean saveUser(User user){
        boolean set = false;
        try{
            //Insert register data to database
            String query = "insert into customer_registration(name,email,password,mobile_no,address) values(?,?,?,?,?)";
           
           PreparedStatement pt = this.con.prepareStatement(query);
           pt.setString(1, user.getName());
           pt.setString(2, user.getEmail());
           pt.setString(3, user.getPassword());
           pt.setString(4, user.getMobileNo());
           pt.setString(5, user.getAddress());
           
           pt.executeUpdate();
           set = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return set;
    }
    
  //user id password login to the website
    public User login(String email, String pass){
        User usr=null;
        try{
            String query ="select * from customer_registration where email=? and password=?";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, pass);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                usr = new User();
                usr.setId(rs.getInt("id"));
                usr.setName(rs.getString("name"));  
                usr.setEmail(rs.getString("email"));
                usr.setPassword(rs.getString("password"));
                usr.setMobileNo(rs.getString("mobile_no"));
                usr.setAddress(rs.getString("address"));
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return usr;
    }
    
    //UPDATE CURRENT USERs DETAILS WHEN HE/SHE WANTS TO EDITS PROFILE DETAILS IN MODAL
    
    public boolean updateUser(User user) {
    	
    	boolean b=false;
    	try {
    		String query="update customer_registration set name=? , email=? , password=? , mobile_no=? , address=?  where id=?";
    		PreparedStatement ps=con.prepareStatement(query);
    		ps.setString(1, user.getName());
    		ps.setString(2, user.getEmail());
    		ps.setString(3, user.getPassword());
    		ps.setString(4, user.getMobileNo());
    		ps.setString(5, user.getAddress());
    		ps.setInt(6  , user.getId());
    		
    		ps.executeUpdate();
    		b=true;
    	}catch (Exception e){
            e.printStackTrace();
}
		return b;
    }
}
