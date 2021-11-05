<%@page import="com.hotel.model.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.hotel.dao.ConnectionPro"%>
<%@page import="com.hotel.dao.OrderDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.hotel.model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hotel.dao.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
  <%  DecimalFormat dcf=new DecimalFormat("#.##");
      request.setAttribute("dcf",dcf);
  
    User user = (User) request.getSession().getAttribute("login");
    List<Order> orders=null;
    if(user !=null){
    	request.setAttribute("login", user);
     orders=new OrderDao(ConnectionPro.getConnection()).userOrders(user.getId());
    } else{
    	//response.sendRedirect("customer_login.jsp");
    }
    
    ArrayList<Cart> cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
    if(cart_list !=null){
    	request.setAttribute("cart_list", cart_list);
    }

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

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
    <a class="navbar-brand" href="welcome.jsp">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#"></a>
        </li>
      </ul>
      
      <!-- Right side -->
          <ul class="navbar-nav mr-right">
    <li class="nav-item">
     <a class="nav-link" href="#"  data-toggle="modal" data-target="#profile-Modal"><span class="fa fa-user-circle"></span><%=user.getName()%></a>
    </li> 
    
          <li class="nav-item">
        <a  class="nav-link" href="User_LogoutServlet"><span class="fa fa-user-plus"></span>LogOut</a>
      </li>
      
    </ul>
      
      
    </div>
  </div>
</nav>
<!--End of  Navbar -->

 <!-- for product price -->
<div class="container">
<div class="card-header my-3">All Orders</div>
<table class="table table-light">
<thead>
<tr>
<th scope="col">Date</th>
<th scope="col">Name</th>
<th scope="col">Category</th>
<th scope="col">Quantity</th>
<th scope="col">Price</th>
<th scope="col">Cancel</th>

</tr>
</thead>

<tbody>
<%
if(orders!=null){
	for(Order o:orders){%>
	<tr>
	    <td><%=o.getDate() %></td>
		<td><%=o.getName() %></td>
		<td><%=o.getCatagory() %></td>
		<td><%=o.getQuantity()%></td>
		<td><%=dcf.format(o.getPrice())%></td>
		
			<td><a class="btn btn-sm btn-danger" href="CancelOrderServlet?id=<%=o.getOrderId() %>">Cancel Order</a></td>			
				</tr>				
		
	
<%}
}
%>
</tbody>
</table>
</div>
</body>
</html>