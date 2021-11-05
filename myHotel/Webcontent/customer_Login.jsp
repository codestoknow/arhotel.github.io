<%@page import="com.hotel.dao.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  User user = (User) request.getSession().getAttribute("login");
    if(user !=null){
    	request.setAttribute("login", user);
    }%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">


<!-- Links For Registration -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
<!-- End of Registration links -->
</head>

<body>
<header>
<nav>
<div class="logo"><h1 class="animate__animated animate__heartBeat">Login Account </h1></div> 
<div class="menu">
  <b><a href="index.html" class=>Home</a></b>
  <b><a href="index.html"  data-target="#mymodel" data-toggle="modal">Registration</a></b>
  <b><a href="contactUs.jsp" >Contact</a></b>
  <b><a href="admin.jsp">Admin</a></b>
</div>
</nav>

<main>
<section>
<form action="LoginServlet" method="post">
	<p>Enter User name</p> <input type="text" placeholder="Username" name="email" required><br>
  <p>  Enter Password</p><input type="password" placeholder="Password" name="password" required><br>
	
    <center><button type="submit" class="buttonone">Login</button></center><br>
    
 <button><a href="ForgetServlet">Forget Password?</a></button>
</form>
</section>
</main>
</header>



<!-- For Registration -->

<div class="container">
		<form action="RegisterServlet" method="post">
		
		<!-- <button class="btn btn-success" 
			>Open Signup</button> -->

		<div class="modal" id="mymodel">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">

						<h3 class="text-primary">SignUp</h3>
						<button type="button" class="close" data-dismiss="modal">
							&times;</button> 
					</div>

					<div class="modal-body">
<form>
						<div class="form-group">
							<label><i class="fa fa-user fa-2x"></i>User Name: </label> <input
								type="text" name="name" class="
	          form-control" required>
						</div>

						<div class="form-group">
							<label><i class="fa fa-envelope fa-2x"></i>User Email: </label> <input
								type="text" name="email" class="
	          form-control" required>
						</div>

						<div class="form-group">
							<label><i class="fa fa-lock fa-2x"></i>User Password: </label> <input
								type="password" name="password" class="
	          form-control" required>
						</div>
						
						<div class="form-group">
							<label><i class="fas fa-mobile-alt fa-2x"></i>Mobile Number: </label> <input
								type="text" name="mobilenumber" class="
	          form-control" required>
						</div>
						
						<div class="form-group">
							<label><i class="fas fa-map-marker-alt fa-2x"></i>Address: </label> <input
								type="text" name="address" class="
	          form-control" required>
						</div>
</form>
					</div>

					<div class="modal-footer justify-content-center">
						<button class="btn btn-danger"  type="submit">
							SignUp</button>
					</div>

				</div>

			</div>


		</div>
		</form>
	</div>
  
  
  

<!-- End For Registration -->




</body>
</html>