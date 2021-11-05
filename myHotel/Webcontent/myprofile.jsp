<%@page import="com.hotel.dao.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%  User user = (User) request.getSession().getAttribute("login");
if(user !=null){
	request.setAttribute("login", user);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table class="table">

  <tbody>
    <tr>
      <th scope="row">ID</th>
      <td><%= user.getId() %></td>
     
    </tr>
    <tr>
      <th scope="row">Email</th>
      <td><%= user.getMobileNo() %></td>
    </tr>
    
    
  </tbody>
</table>

<button type="button" value="Edit"></button>

</body>
</html>