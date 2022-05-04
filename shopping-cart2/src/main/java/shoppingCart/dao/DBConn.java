package shoppingCart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static Connection conn = null;;
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/shopping_cart";
	private static String dbUname = "root";
	private static String dbpw = "giang@99";
	private static String dbDriver = "com.mysql.jdbc.Driver";
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName(dbDriver);
			Connection conn = DriverManager.getConnection(url, dbUname, dbpw);
			
			return conn;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
