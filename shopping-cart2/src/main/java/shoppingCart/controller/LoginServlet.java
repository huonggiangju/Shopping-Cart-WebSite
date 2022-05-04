package shoppingCart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingCart.dao.DBConn;
import shoppingCart.dao.UserDao;
import shoppingCart.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			
			try {
				//create userDao
				UserDao userDao = new UserDao(DBConn.getConnection());
				
				//create User object
				User user = new User(email, password);
								
				if(userDao.userLogin(user)) {
//					//set session
					HttpSession session = request.getSession();
					session.setAttribute("email", user);
					
					response.sendRedirect("index.jsp");
				}else {
					out.print("failed");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
