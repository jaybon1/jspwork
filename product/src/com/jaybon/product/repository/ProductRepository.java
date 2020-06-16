package com.jaybon.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jaybon.product.db.DBConn;
import com.jaybon.product.model.Product;
import com.jaybon.product.model.ProductType;

public class ProductRepository {
	private static final String TAG = "ProductRepository : "; // TAG 생성 (오류 발견시 용이)
	private static ProductRepository instance = new ProductRepository();

	private ProductRepository() {
	}

	public static ProductRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int insertItem(Product product) {
		final String SQL = "INSERT INTO product(id, name, type, price, count) "
				+ "VALUES(PRODUCT_SEQ.nextval,?,?,?,?)";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getType().toString());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getCount());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "insertItem : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}

	
	public int deleteById(int id) {
		final String SQL = "DELETE FROM product WHERE id =?";
		
		List<Product> products = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "deleteById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	
	// product 다찾기
	public List<Product> findAll() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, name, type, price, count FROM product ORDER BY id asc";
		
		List<Product> products = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				products = new ArrayList<>();
				
				Product product = Product.builder()
						.id(rs.getInt(1))
						.name(rs.getString(2))
						.type(ProductType.valueOf(rs.getString(3)))
						.price(rs.getInt(4))
						.count(rs.getInt(5))
						.build();
				
				products.add(product);
				
				while (rs.next()) {
					
					Product product1 = Product.builder()
							.id(rs.getInt(1))
							.name(rs.getString(2))
							.type(ProductType.valueOf(rs.getString(3)))
							.price(rs.getInt(4))
							.count(rs.getInt(5))
							.build();
					
					products.add(product1);
					
				}
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	
	public List<Product> findAllPriceAsc() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, name, type, price, count FROM product ORDER BY Price ASC";
		
		List<Product> products = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				products = new ArrayList<>();
				
				Product product = Product.builder()
						.id(rs.getInt(1))
						.name(rs.getString(2))
						.type(ProductType.valueOf(rs.getString(3)))
						.price(rs.getInt(4))
						.count(rs.getInt(5))
						.build();
				
				products.add(product);
				
				while (rs.next()) {
					
					Product product1 = Product.builder()
							.id(rs.getInt(1))
							.name(rs.getString(2))
							.type(ProductType.valueOf(rs.getString(3)))
							.price(rs.getInt(4))
							.count(rs.getInt(5))
							.build();
					
					products.add(product1);
					
				}
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	
	public List<Product> findAllPriceDesc() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, name, type, price, count FROM product ORDER BY Price DESC";
		
		List<Product> products = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				products = new ArrayList<>();
				
				Product product = Product.builder()
						.id(rs.getInt(1))
						.name(rs.getString(2))
						.type(ProductType.valueOf(rs.getString(3)))
						.price(rs.getInt(4))
						.count(rs.getInt(5))
						.build();
				
				products.add(product);
				
				while (rs.next()) {
					
					Product product1 = Product.builder()
							.id(rs.getInt(1))
							.name(rs.getString(2))
							.type(ProductType.valueOf(rs.getString(3)))
							.price(rs.getInt(4))
							.count(rs.getInt(5))
							.build();
					
					products.add(product1);
					
				}
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	
	public List<Product> findAllCountDesc() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, name, type, price, count FROM product ORDER BY count DESC";
		
		List<Product> products = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				products = new ArrayList<>();
				
				Product product = Product.builder()
						.id(rs.getInt(1))
						.name(rs.getString(2))
						.type(ProductType.valueOf(rs.getString(3)))
						.price(rs.getInt(4))
						.count(rs.getInt(5))
						.build();
				
				products.add(product);
				
				while (rs.next()) {
					
					Product product1 = Product.builder()
							.id(rs.getInt(1))
							.name(rs.getString(2))
							.type(ProductType.valueOf(rs.getString(3)))
							.price(rs.getInt(4))
							.count(rs.getInt(5))
							.build();
					
					products.add(product1);
					
				}
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}

}
