<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="shoppingCart.model.User" %>  
     <%@page import="shoppingCart.model.*" %>  
     <%@page import="java.util.*" %> 
     <%@page import="shoppingCart.dao.*" %> 
   <%
   //access session
   
   User email = (User) request.getSession().getAttribute("email");
   if(email != null){
	   request.setAttribute("email", email);
	   response.sendRedirect("index.jsp");
   }
 //set session
  	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
   
   List<Cart> cartProduct = null;
   if(cart_list != null){
 	   ProductDao pDao = new ProductDao(DBConn.getConnection());
 	   cartProduct = pDao.getCartProduct(cart_list);
 	   request.setAttribute("cart_list", cart_list);
   }
   %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="include/header.jsp" %>

</head>
<body>
<%@include file="include/navbar.jsp" %>

<div class="container">
	 	<div class="card w-50 mx-auto my-5">
	 		<div class="card-header text-center">Login Form</div>
	 		<div class="card-body">
	 			<form action="Login" method="post">
	 				<div class="form-group">
	 				<label>Email</label>
	 				<input type = email class="form-control" name="email" placeholder="Enter your email" required>
	 				</div>
	 				
	 				<div class="form-group">
	 				<label>Email</label>
	 				<input type = password class="form-control" name="password" placeholder="*******" required>
	 				</div>
	 				
	 				<div class="text-center">
	 				<button type="submit" class="btn btn-primary">Submit</button>
	 				</div>
	 			</form>
	 		</div>
	 	</div>
 	</div>

<%@include file="include/footer.jsp" %>
</body>
</html>