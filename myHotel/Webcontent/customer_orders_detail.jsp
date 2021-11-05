
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hotel.dao.User"%>
<%@page import="com.hotel.model.Product"%>
<%@page import="com.hotel.dao.ProductDAO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.hotel.dao.ConnectionPro"%>
<%@page import="com.hotel.dao.AdminDatabase"%>
<%@page import="com.hotel.dao.OrderDao"%>
<%@page import="com.hotel.model.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.hotel.dao.Admin_Logindao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%OrderDao oDao=new OrderDao(ConnectionPro.getConnection()) ;
    List<Order> allOrders=oDao.receivedOrders();
    
    ProductDAO pDao=new ProductDAO(ConnectionPro.getConnection());
    List<Product> products=pDao.getAllProducts();
    
    
    %>
      
<!DOCTYPE html>
<html>
<head>
<title>All Customer orders</title>
<!-- Scripts -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


<!-- Java script -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<!--End of  Scripts -->
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="welcome.jsp">Dashboard</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="All_Orders.jsp">All Orders</a>
        </li>
      </ul>
      
      <!-- Right side -->
          <ul class="navbar-nav mr-right">
    <li class="nav-item">
     <a class="nav-link" href="#"  data-toggle="modal" data-target="#profile-Modal"><span class="fa fa-user-circle"></span></a>
    </li> 
    
          <li class="nav-item">
        <a  class="nav-link" href="User_LogoutServlet"><span class="fa fa-user-plus"></span>LogOut</a>
      </li>
      
    </ul>
      
      
    </div>
  </div>
</nav>
<!--End of  Navbar -->
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<!-- All orders from customers -->
<div class="container">
<div class="card-header my-3">All Orders</div>
<table class="table table-light">
<thead>
<tr>
<th scope="col">Order ID</th>
<th scope="col">Product ID</th>
<th scope="col">User ID</th>
<th scope="col">Quantity</th>
<th scope="col">Date</th>
<th scope="col">Customer Name</th>
<th scope="col">Customer Mobile</th>
<th scope="col">Customer Address</th>
<th scope="col">Item name</th>

</tr>
</thead>
<tbody>
<%
		if(!allOrders.isEmpty()){
			for(Order o:allOrders){%>
			<tr>
	    <td><%=o.getOrderId()%></td>
		<td><%=o.getId()%></td>
		<td><%=o.getUid() %></td>
		<td><%=o.getQuantity()%></td>
		<td><%=o.getDate()%></td>
		<td><%=o.getCustomerName()%></td>
		<td><%=o.getMobileNo()%></td>
		<td><%=o.getAddress()%></td>
		<td><%=o.getName() %>
		
		
			<td><a class="btn btn-sm btn-danger" href="CancelCustomerOrderServlet?id=<%=o.getOrderId() %>">Cancel Order</a></td>			
				</tr>				
		
			
			<%}
		}
		%>
			
		</div>
	</div>


</tbody>
</body>
</html>