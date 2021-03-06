package com.love.blog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


// javax는 톰캣이 가지고 있다
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConn {
	public static Connection getConnection() {
		try {
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			Connection conn = ds.getConnection();
			
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBConn : 데이터베이스 연결 실패");
			System.out.println("DBConn : Message" + e.getMessage());
		}
		return null;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(conn != null) {
				conn.close();				
			}
			if(pstmt != null) {			
				pstmt.close();
			}
		} catch (Exception e) {
			System.out.println("DB종료시 오류가 발생 : " +e.getMessage());
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(conn != null) {
				conn.close();				
			}
			if(pstmt != null) {			
				pstmt.close();
			}
			if(rs != null) {			
				rs.close();
			}
		} catch (Exception e) {
			System.out.println("DB종료시 오류가 발생 : " +e.getMessage());
		}
	}
}





