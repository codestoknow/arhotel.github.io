<%@page import="java.text.DecimalFormat"%>
<%@page import="com.hotel.dao.ConnectionPro"%>
<%@page import="com.hotel.dao.ProductDAO"%>
<%@page import="java.util.*"%>
<%@page import="com.hotel.model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hotel.dao.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    DecimalFormat dcf=new DecimalFormat("#.##");
    request.setAttribute("dcf",dcf);
    
    
    User user = (User) request.getSession().getAttribute("login");
    if(user !=null){
    	request.setAttribute("login", user);
    }
    
    ArrayList<Cart> cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
    List<Cart> cartProduct=null;
    if(cart_list !=null){
    	ProductDAO pdao=new ProductDAO(ConnectionPro.getConnection());
    	cartProduct=pdao.getCartProducts(cart_list);
    	double total=pdao.getTotalCartPrice(cart_list);
    	request.setAttribute("cart_list", cart_list);
    	request.setAttribute("total", total);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
      </ul>
      
      <!-- Right side -->
          <ul class="navbar-nav mr-right">
    <li class="nav-item">
     <a class="nav-link" href="#"  data-toggle="modal" data-target="#profile-Modal"><span class="fa fa-user-circle"></span><%=user.getName()%></a>
    </li> 
    
          <li class="nav-item">
        <a  class="nav-link" href="User_LogoutServlet"><span class="fa fa-user-minus"></span>LogOut</a>
      </li>
      
    </ul>
      
      
    </div>
  </div>
</nav>
<!--End of  Navbar -->

 <!-- for product price -->
<div class="container">
<div class="d-flex py-3"><h3>Total Price: ${ (total > 0)?dcf.format(total): 0}</h3>
<a class="mx-3 btn btn-primary" href="CheckoutServlet">Check Out</a></div>


<table class="table table-light">
<thead>
<tr>
<th scope="col">Name</th>
<th scope="col">Category</th>
<th scope="col">Price</th>
<th scope="col">Buy</th>
<th scope="col">Remove</th>

</tr>
</thead>



<tbody>

<%
if(cart_list !=null){
	for(Cart c:cartProduct){  %>
		<tr>
		<td><%=c.getName() %></td>
		<td><%=c.getCatagory()%></td>
		<td><%=dcf.format(c.getPrice()) %></td>
		
		<td>
		<form action="OrderNowServlet" class="form-inline" method="post">
		<input type="hidden" name="id" value="1" class="form-input">
				
		<div class="form-group d-flex justify-content-between w-50">
		<a class="btn btn-sm btn-decre" href="QuantityIncrDecrServlet?action=dec&id=<%= c.getId() %>"><i class="fas fa-minus-square"></i></a>
		<input type="text" name="quantity" class="form-control w-50" value="<%=c.getQuantity() %>" writeonly>
		<a class="btn btn-sm btn-incre" href="QuantityIncrDecrServlet?action=inc&id=<%= c.getId() %>"><i class="fas fa-plus-square"></i></a> 
		
		</div>
		
		<button type="submit" class="btn btn-primary btn-sm " style="background-color: green"><font color="white"><b>Buy</b></font></button>
		</form>
		</td>
  s  
		<td><a class="btn btn-sm btn-danger" href="RemoveFromCartServlet?id=<%=c.getId()%>"><font color="white">Remove</font></a></td>
	    						
		</tr>
		
	<%}
}

%>
				
				

</tbody>
</table>
</div>
</body>
</html>