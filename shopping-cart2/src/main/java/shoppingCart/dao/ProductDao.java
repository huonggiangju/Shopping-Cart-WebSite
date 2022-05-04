package shoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import shoppingCart.model.Cart;
import shoppingCart.model.Product;

public class ProductDao {

	private Connection conn;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ProductDao(Connection conn) {
		this.conn = conn;
	}
	
	public List<Product> getAllProduct(){
		List<Product> product = new ArrayList<Product>();
		try {
			query = "Select * from products";
			ps = this.conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				product.add(row); //add product in list
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return product;
	}
	
	
	//getCartProduct
	public List<Cart> getCartProduct(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		try {
			if(cartList.size()>0) {
				for(Cart item: cartList) {
					query = "Select * from products where id = ?";
					ps = this.conn.prepareStatement(query);
					ps.setInt(1, item.getId());
					rs = ps.executeQuery();
					
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setImage(rs.getString("image"));
						row.setPrice(rs.getDouble("price"));
						row.setQuantity(item.getQuantity());
						
						products.add(row);
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return products;
	}
	
	
	//get totalPrice
	public double getTotalPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			if(cartList.size() >0) {
				for(Cart c: cartList) {
					query = "Select price from products where id=?";
					ps= this.conn.prepareStatement(query);
					ps.setInt(1, c.getId());
					rs = ps.executeQuery();
					
					while(rs.next()) {
						sum+= rs.getDouble("price") *c.getQuantity();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	//get single product
	public Product getSingleProduct(int id) {
		Product product = new Product();
		try {
			query = "Select * from products where id =?";
			ps = this.conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
				product.setImage(rs.getString("image"));
				
			}
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
}
