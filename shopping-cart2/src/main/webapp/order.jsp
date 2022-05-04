<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="shoppingCart.model.User" %>  
     <%@page import="shoppingCart.model.*" %>  
     <%@page import="java.util.*" %> 
     <%@page import="shoppingCart.dao.*" %>  
   <%
   //access session
   
   User email = (User) request.getSession().getAttribute("email");
 //create list order
   List<Order> orders = null;
   if(email != null){
	   request.setAttribute("email", email);
	   
	   //connect DB
	   OrderDao orderDao = new OrderDao(DBConn.getConnection());
	   
	   orders = orderDao.userOrder(email.getId());
	   
	   
	   
   }else{
	   response.sendRedirect("login.jsp"); 
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

<div class="col-lg-12 col-md-12 col-sm-12">
        <div class="col-lg-12 col-sm-12">
            <span class="title">All Order</span>
        </div>
        <div class="col-lg-12 col-sm-12 hero-feature">
            <div class="table-responsive">
                <table class="table table-bordered tbl-cart">
                    <thead>
                    
                    
                        <tr>
                            <td class="hidden-xs">Date</td>
                            <td>Product Name</td>
                            
                            <td>Category</td>
                            <td class="td-qty">Quantity</td>
                            <td>Price</td>
                            <td>Remove</td>
                        </tr>
                        
                    </thead>
                    <tbody>
                   <%
                   if(orders != null){
                   		for(Order o: orders){%>
                   		<tr>
                            <td ><%=o.getDate() %></td>
                            <td><%=o.getName() %></td>                           
                            <td><%=o.getCategory() %></td>
                            <td><%=o.getQuantity() %></td>
                            <td><%=o.getPrice() %></td>
                            <td>
                                <a href="RemoveOrder?id=<%=o.getOrderId() %>" class="btn btn-sm btn-danger">Cancel</a>                     
                            </td> 
                            
                        </tr>
                   		
               		<%}
                   		}%>

                    </tbody>
                </table>
            </div>
            <div class="btn-group btns-cart">
                <button type="button" class="btn btn-primary"><i class="fa fa-arrow-circle-left"></i> Continue Shopping</button>
                <button type="button" class="btn btn-primary">Update Cart</button>
                <a class="btn btn-primary" href = "Checkout">Checkout</a>
            </div>

        </div>
    </div>

<%@include file="include/footer.jsp" %>
</body>
</html>