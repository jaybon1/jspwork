package com.love.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.love.blog.db.DBConn;
import com.love.blog.model.Board;
import com.love.blog.model.Reply;
import com.love.blog.model.Users;

public class ReplyRepository {

	private static final String TAG = "BoardRepository : ";

	// 싱글톤
	private static ReplyRepository instance = new ReplyRepository();

	private ReplyRepository() {
	}

	public static ReplyRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public int save(Reply reply) {

		final String SQL = "";

		try {

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 여기에 물음표 완성하기

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}

		return -1;
	}

	public int update(Reply reply) {

		final String SQL = "";

		try {

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 여기에 물음표 완성하기

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}

		return -1;
	}

	public int deleteById(int id) {

		final String SQL = "";

		try {

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 여기에 물음표 완성하기

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "deleteById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}

		return -1;
	}
	
	public List<Reply> findAll() {

		final String SQL = "";
		List<Reply> replies = new ArrayList<>();

		try {

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			// 여기에 물음표 완성하기
			// while 돌려서 rs -> java 오브젝트에 집어넣기

			return replies;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}
	
	public Reply findById(int id) {

		final String SQL = "";
		Reply reply = new Reply();

		try {

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			// 여기에 물음표 완성하기
			// if 해서 rs -> java 오브젝트에 집어넣기

			return reply;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null;
	}

}