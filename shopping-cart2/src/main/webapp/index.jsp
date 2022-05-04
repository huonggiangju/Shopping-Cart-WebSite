<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@page import="shoppingCart.dao.DBConn" %>  
   <%@page import="shoppingCart.dao.*" %> 
   <%@page import="shoppingCart.model.*" %>  
   <%@page import="java.util.*"%>
   <%
   //access session
   
   User email = (User) request.getSession().getAttribute("email");
   if(email != null){
	   request.setAttribute("email", email);
   }
   
   //create product dao
   ProductDao pDao = new ProductDao(DBConn.getConnection());
   List<Product> products = pDao.getAllProduct();
   
   
   //set session
 	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
  
  List<Cart> cartProduct = null;
  if(cart_list != null){
	  
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
		<div class="card-header my-3">All Products</div>
		<div class="row">
		<%if(products != null){
			for(Product p: products){%>
			
				<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
				  <img class="card-img-top" src="image/<%=p.getImage() %>" alt="Card image cap">
				  <div class="card-body">
				    <h5 class="card-title"><%=p.getName() %></h5>
				    <h6 class="price">Price: $<%=p.getPrice() %></h6>
				    <h6 class="category">Category: <%=p.getCategory() %></h6>
				    <div class="mt-3 d-flex justify-content-between">
				    	<a href="CartServlet?id=<%=p.getId() %>" class="btn btn-dark">Add to Cart</a>
				    	 <a href="OrderNow?quantity=1&id=<%=p.getId() %>" class="btn btn-primary">Buy Now</a> 
				    	
				    </div>
				  </div>
				</div>
			</div>
			
			<%}
		}%>
				
			
		</div>
	</div>



<%@include file="include/footer.jsp" %>
</body>
</html>