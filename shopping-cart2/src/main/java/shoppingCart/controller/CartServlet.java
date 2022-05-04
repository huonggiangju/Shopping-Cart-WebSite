package shoppingCart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingCart.model.Cart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		try {
			//create arraylist cart
			ArrayList<Cart> cartList = new ArrayList<Cart>();
			
			int id = Integer.parseInt(request.getParameter("id")); //set id in cart
			Cart cart = new Cart(); //create new cart object
			cart.setId(id);
			cart.setQuantity(1);
			
			//set session
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list == null) {
				cartList.add(cart);
				session.setAttribute("cart-list", cartList); //set attribute cart-list into cartList array
				response.sendRedirect("index.jsp");
			}else { //cartlist has product
				cartList = cart_list; //cart list == session
				
				boolean exist = false; //check product exist
				for(Cart c: cart_list) { //loop through session
					if(c.getId() == id) {
						exist = true;
						out.print("product exist");
					}				
				}
				if(!exist) {
					cartList.add(cart);
					response.sendRedirect("index.jsp");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
