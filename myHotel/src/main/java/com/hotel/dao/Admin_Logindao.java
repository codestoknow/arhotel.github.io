package com.hotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin_Logindao {
private String userName;
private String password;

public Admin_Logindao(String userName, String password) {
	this.userName = userName;
	this.password = password;
}

public Admin_Logindao(Connection connection) {

}

public Admin_Logindao() {

}


public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}



		


}
