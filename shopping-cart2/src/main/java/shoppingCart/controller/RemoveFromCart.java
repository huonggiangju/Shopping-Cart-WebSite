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
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		try {
			String id = request.getParameter("id");
				
			if(id != null) {
				//set session
			  	ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			  	
			  	if(cart_list != null) {
			  		for(Cart c: cart_list) {
			  			if(c.getId() == Integer.parseInt(id)) {
			  				cart_list.remove(cart_list.indexOf(c));
			  				break;
			  			}
			  		}
			  		response.sendRedirect("cart.jsp");
				
			  	}
			}
			response.sendRedirect("cart.jsp");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
