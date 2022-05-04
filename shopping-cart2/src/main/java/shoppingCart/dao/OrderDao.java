package shoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shoppingCart.model.Order;
import shoppingCart.model.Product;

public class OrderDao {
	private Connection conn;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public OrderDao(Connection conn) {
		this.conn = conn;
	}
	
	public boolean insertOrder(Order order) {
		
		boolean result = false;
		try {
			query = "Insert into orders( p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
			ps = this.conn.prepareStatement(query);
			ps.setInt(1, order.getId());
			ps.setInt(2, order.getUserId());
			ps.setInt(3, order.getQuantity());
			ps.setString(4, order.getDate());
			
			ps.executeUpdate();
			result = true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//retrive all order
	public List<Order> userOrder(int id){
		List<Order> list = new ArrayList<>();
		try {
			
			query = "Select * from orders where u_id=? order by o_id desc";
			ps = this.conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				//create new order object
				Order order = new Order();
				ProductDao pDao = new ProductDao(this.conn);
				int pId = rs.getInt("p_id");
				
				//create new product object
				Product product = pDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("o_id"));
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				order.setQuantity(rs.getInt("o_quantity"));
				
				list.add(order);
				
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//cancel order
	public void cancelOrder(int id) {
		try {
			query = "delete from orders where o_id =?";
			ps=this.conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
