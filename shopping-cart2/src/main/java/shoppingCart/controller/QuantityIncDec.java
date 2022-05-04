package shoppingCart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingCart.model.Cart;

/**
 * Servlet implementation class QuantityIncDec
 */
@WebServlet("/QuantityIncDec")
public class QuantityIncDec extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuantityIncDec() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			//set session
		  	ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
		  	
		  	if(action != null & id >=1) {
		  		if(action.equals("inc")) {
		  			for(Cart c: cart_list) {
		  				if(c.getId() == id) {
		  					int quantity = c.getQuantity();
		  					quantity++;
		  					c.setQuantity(quantity);
		  					response.sendRedirect("cart.jsp");
		  				}
		  			}
		  		}
		  		
		  		if(action.equals("dec")) {
		  			for(Cart c: cart_list) {
		  				if(c.getId() == id & c.getQuantity()>1) {
		  					int quantity = c.getQuantity();
		  					quantity--;
		  					c.setQuantity(quantity);
		  					break;
		  				}
		  			}
		  			response.sendRedirect("cart.jsp");
		  		}
		  	}else {
		  		response.sendRedirect("cart.jsp");
		  	}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
