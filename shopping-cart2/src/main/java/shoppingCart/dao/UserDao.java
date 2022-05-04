package shoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shoppingCart.model.User;

public class UserDao {
	private Connection conn;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	public UserDao(Connection conn) {
		this.conn = conn;
	}
	
	public boolean userLogin(User user) {
		
		boolean status = false;
		try {
			query = "Select * from users where email = ? and password = ?;";
			ps = this.conn.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			status = rs.next();
			
			if(status) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
}
