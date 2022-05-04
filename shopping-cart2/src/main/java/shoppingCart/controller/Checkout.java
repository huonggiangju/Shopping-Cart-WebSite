package shoppingCart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingCart.dao.DBConn;
import shoppingCart.dao.OrderDao;
import shoppingCart.model.Cart;
import shoppingCart.model.Order;
import shoppingCart.model.User;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			//retrive user session
			User user = (User) request.getSession().getAttribute("email");
			
			//retrive cart session
		  	ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
		  	
			//date format
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			//check user and cart list
			if(user != null && cart_list != null) {
				for(Cart c: cart_list) {
					//create order object
					Order order = new Order();
					order.setId(c.getId());
					order.setUserId(user.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					//create orderDao
					OrderDao orderDao = new OrderDao(DBConn.getConnection());
					boolean result = orderDao.insertOrder(order);
					if(!result) {
						break;
					}				
				}
				//clear cart list
				cart_list.clear();
				response.sendRedirect("order.jsp");
					
			}else {
				if(user !=null) {
					response.sendRedirect("login.jsp");
				}
				response.sendRedirect("cart.jsp");
			}
			
//			response.sendRedirect("order.jsp");
		}catch(Exception e) {
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
