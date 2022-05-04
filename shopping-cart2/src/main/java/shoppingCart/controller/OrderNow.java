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
 * Servlet implementation class OrderNow
 */
@WebServlet("/OrderNow")
public class OrderNow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderNow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			//access session
			User email = (User) request.getSession().getAttribute("email");
			
			//date format
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			if(email != null) {
				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				
				if(productQuantity<=0) {
					productQuantity =1;
				}else {
					//create order object
					Order order = new Order();
					order.setId(Integer.parseInt(productId));
					order.setUserId(email.getId());
					order.setQuantity(productQuantity);
					order.setDate(formatter.format(date));
					
					OrderDao orderDao = new OrderDao(DBConn.getConnection());
					boolean result = orderDao.insertOrder(order);
					
					if(result) {
						//set session
					  	ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					  	
					  	if(cart_list != null) {
					  		for(Cart c: cart_list) {
					  			if(c.getId() == Integer.parseInt(productId)) {
					  				cart_list.remove(cart_list.indexOf(c));
					  				break;
					  			}
					  		}
						
					  	}
						
						response.sendRedirect("order.jsp");
					}else {
						out.print("order failed");
					}
					
					
				}
				
				
			}else {
				response.sendRedirect("login.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
