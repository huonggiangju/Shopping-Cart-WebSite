<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="shoppingCart.model.*" %>  
     <%@page import="java.util.*" %> 
     <%@page import="shoppingCart.dao.*" %>  
   <%
   //access session
   
   User email = (User) request.getSession().getAttribute("email");
   if(email != null){
	   request.setAttribute("email", email);
   }
   
   //set session
  	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
   
   List<Cart> cartProduct = null;
   if(cart_list != null){
	   ProductDao pDao = new ProductDao(DBConn.getConnection());
	   cartProduct = pDao.getCartProduct(cart_list);
	   request.setAttribute("cart_list", cart_list);
	   
	   //get total price
	  	double total = pDao.getTotalPrice(cart_list);
	   request.setAttribute("total", total);
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

	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container bootdey">
<div class="row bootstrap snippets">
   
    <div class="clearfix visible-sm"></div>

    <!-- Cart -->
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="col-lg-12 col-sm-12">
            <span class="title">SHOPPING CART</span>
        </div>
        <div class="col-lg-12 col-sm-12 hero-feature">
            <div class="table-responsive">
                <table class="table table-bordered tbl-cart">
                    <thead>
                    
                    
                        <tr>
                            <td class="hidden-xs">Image</td>
                            <td>Product Name</td>
                            
                            <td>Category</td>
                            <td class="td-qty">Quantity</td>
                            <td>Unit Price</td>
                            <td>Sub Total</td>
                            <td>Remove</td>
                        </tr>
                        
                    </thead>
                    <tbody>
                    <%if(cart_list != null){
                    	for(Cart c: cartProduct){
                    	%>
                        <tr>
                            <td class="hidden-xs">
                                <a href="#">
                                    <img src="image/<%=c.getImage() %>" alt="Photo" title="" width="47" height="47">
                                </a>
                            </td>
                            
                            <td><a href="#"><%=c.getName() %></a>
                            </td>
                            
                            <td>
                                <%=c.getCategory() %>
                            </td>
                            
                            <td>
                                <div class="input-group bootstrap-touchspin">
	                                <span class="input-group-btn">
	                                	<a class="btn btn-default bootstrap-touchspin-up" href="QuantityIncDec?action=dec&id=<%=c.getId()%>">-</a>
	                                	
	                                </span>
	                                <span class="input-group-addon bootstrap-touchspin-prefix" style="display: none;"></span>
	                                <input type="text" name="" value="<%=c.getQuantity() %>" class="input-qty form-control text-center" style="display: block;">
	                                <span class="input-group-addon bootstrap-touchspin-postfix" style="display: none;"></span>
	                                <span class="input-group-btn">
	                                	
	                                	<a class="btn btn-default bootstrap-touchspin-up" href="QuantityIncDec?action=inc&id=<%=c.getId()%>">+</a>
	                                </span>
                                </div>
                            </td>
                            <td class="price">$ <%=c.getPrice() %></td>
                            <td>$ <%=c.getPrice() * c.getQuantity() %></td>
                            <td class="text-center">
                                <a href="RemoveFromCart?id=<%=c.getId() %>" class="remove_cart" rel="2">
                                    <i class="fa fa-trash-o"></i>
                                </a> 
                            </td>
                        </tr>
                        <%}
                    }
                        %>
                        <tr>
                            <td colspan="6" align="right">Total</td>
                            <td class="total" colspan="2"><b>$ ${ (total>0)?total:0 }</b>
                            </td>
                        </tr>
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
    <!-- End Cart -->
</div>
</div>

<%@include file="include/footer.jsp" %>
</body>
</html>