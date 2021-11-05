<%@page import="com.hotel.model.Cart"%>
<%@page import="java.util.*"%>
<%@page import="com.hotel.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.hotel.dao.ConnectionPro"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.hotel.dao.User"%>
<%@page import="com.hotel.dao.ProductDAO"%>
<%@page import="com.mysql.cj.xdevapi.DbDoc"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User user = (User) request.getSession().getAttribute("login");
if(user !=null){
	request.setAttribute("login", user);
}

ProductDAO pdao=new ProductDAO(ConnectionPro.getConnection());
List<Product> products=pdao.getAllProducts();

//for cart
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
</head>
<head>
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

<script>
$(document).ready(function(){
	let editStatus=false;
	$('#edit-profile-button').click(function()
			{
		if(editStatus==false){
			$("#profile-details").hide()
			
			$("#profile-edit").show();
			editStatus=true;
			
			$(this).text("Back")
		}else{
			$("#profile-details").show()
			
			$("#profile-edit").hide();
			editStatus=false;
			$(this).text("Edit")

		}

	
	})
});
</script>
<!-- Java script end -->
</head>
<body>

<!-- Nav bar start -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="welcome.jsp">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="orders.jsp">Orders <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger px-1">${ cart_list.size() }</span></a>
      </li>
      <li class="nav-item dropdown">
       <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="offers.jsp">Offers</a>
          <a class="dropdown-item" href="#"></a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#"></a>
        </div>
      </li>
      
    </ul>
    
    <ul class="navbar-nav mr-right">
    <li class="nav-item">
     <a class="nav-link" href="#"  data-toggle="modal" data-target="#profile-Modal"><span class="fa fa-user-circle"></span><%=user.getName()%></a>
    </li> 
    
          <li class="nav-item">
        <a  class="nav-link" href="User_LogoutServlet"><span class="fa fa-user-minus"></span>LogOut</a>
      </li>
      
    </ul>
  </div>
</nav>

<!-- Nav bar end -->



<!-- Button trigger modal
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#profile-Modal">
  Launch demo modal
</button>
 -->
 
 
 
<!-- Modal -->
<div class="modal fade" id="profile-Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header" >
        <h5 class="modal-title" id="exampleModalLabel">My Profile</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="container text-center" >
                <h5 class="modal-title" id="exampleModalLabel"><%= user.getName() %></h5>
        

<!-- Details of the Loggedin user as table format -->

<div id="profile-details">        
        <table class="table">

  <tbody>
    <tr>
      <th scope="row">ID :</th>
      <td><%= user.getId() %></td>
      
    </tr>
    <tr>
      <th scope="row">NAME :</th>
      <td><%=user.getName() %></td>
      
    </tr>
    <tr>
      <th scope="row">Email :</th>
       <td><%=user.getEmail() %></td>
    </tr>
    
    <tr>
      <th scope="row">Mobile :</th>
       <td><%=user.getMobileNo() %></td>
    </tr>
    
    
  </tbody>
</table>
</div>
<!-- //Details End -->


                                                    <!-- Profile Edit -->

<div id="profile-edit"  style="display: none;">
 <h2 class="mt-2">Please Edit Carefully</h2>
 
 <form action="EditServlet" method="post" enctype="multipart/form-data">
       <table class="table">
           <tr>
             <td>ID :</td>
             <td><%=user.getId() %></td>
           </tr>
           <tr>
             <td>Email : </td>
             <td><input type="email" class="form-control" name="email" value="<%=user.getEmail()%>">
             </td>
          </tr>
          
           <tr>
             <td>Name : </td>
             <td><input type="text" class="form-control" name="name" value="<%=user.getName()%>">
             </td>
           </tr>
           
           <tr>
             <td>Mobile No : </td>
             <td><input type="text" class="form-control" name="mobilenumber" value="<%=user.getMobileNo()%>">
             </td>
             </tr>
             
              <tr>
             <td>Address : </td>
             <td><input type="text" class="form-control" name="address" value="<%=user.getAddress()%>">
             </td>
             </tr>  
           
            
             <tr>
             <td>Password : </td>
             <td><input type="password" class="form-control" name="password" value="<%=user.getPassword()%>">
             </td>
             </tr>  
             
             
       </table>
       
                      <div class="container">
                        <button type="submit" class="btn btn-outline-primary">Save Profile</button>
                      </div>
 </form>
</div>
<!-- Profile edit end -->


        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button id="edit-profile-button" type="button" class="btn btn-primary">Edit</button>
      </div>
    </div>
  </div>
</div>

                                                    <!-- End of Profile Edit -->
                                                    
                                                    
                                                    

                                          <!-- For Food Items -->
                                          
                                          
	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
		<%
		if(!products.isEmpty()){
			for(Product pds:products){%>
			
			<div class="col-md-3">
			
				<div class="card w-100" style="width: 18rem;">
					<img src="css/product images/<%=pds.getImage() %>" class="card-img-top" alt="card-img-cap">
					<div class="card-body">
						<h5 class="card-title"><%=pds.getName() %></h5>
						<h6 class="price">Price: <%=pds.getPrice()%></h6>
						<h6 class="category">Category: <%=pds.getCatagory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="CartServlet?id=<%=pds.getId() %>" class="btn btn-dark" style="background-color: green">Add</a>
						    <a href="OrderNowServlet?quantity=1&id=<%=pds.getId() %>" class="btn btn-primary">Buy</a>
						
						</div>
					</div>
				</div>
				
			</div>
			<%}
		}
		%>
			
		</div>
	</div>

	<!-- End for food items  -->                                       

</body>
</html>



<!--   

<body>
		<a href="MenuServlet"><button>Todays Menu</button></a>



		<a href='User_LogoutServlet'><button>Logout</button></a>
-->

<!--  
	<form action="welcome" method=post>
		Customer Name: <input type="text" name="cname" /><span>style="color: red">${error1}</span><br>
	    Customer Address: <inputtype="text" name="caddress" /> <span style="color: red">${error2}</span><br>
		Contact Number: <input type="text" name="contactnumber" required> <span>style="color: red">${error3}</span><br> 
		Items:<input type="text" name="item"> <span style="color: red">${error4}</span><br>


		<input type="submit" value="Order"> <br>

	</form>
</body>

-->

