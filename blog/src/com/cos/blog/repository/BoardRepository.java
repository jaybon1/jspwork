package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;

public class BoardRepository {
	private static final String TAG = "BoardRepository : "; // TAG 생성 (오류 발견시 용이)
	private static BoardRepository instance = new BoardRepository();

	private BoardRepository() {
	}

	public static BoardRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 글쓰기
	public int save(Board board) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "INSERT INTO board(id, userid, title, content, createDate) "
				+ "VALUES(BOARD_SEQ.nextval,?,?,?,sysdate)";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			// 물음표 완성하기
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}

	// 회원정보 수정
	public int update(Board board) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "Update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}

	// 회원정보 삭제
	public int deleteById(int id) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "Delete : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}

	// 회원정보 다 찾기
	public List<Board> findAll() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, userid, title, content, readcount, createdate FROM board ORDER BY id DESC";
		List<Board> boards = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			// while 돌려서 rs -> java오브젝트에 집어넣기
			while (rs.next()) {
				
				Board board = Board.builder()
						.id(rs.getInt(1))
						.userId(rs.getInt(2))
						.title(rs.getString(3))
						.content(rs.getString(4))
						.readCount(rs.getInt(5))
						.createDate(rs.getTimestamp(6))
						.build();
				
				boards.add(board);		
				
			}
			
			return boards;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}

	
	// 회원정보 한 건 찾기
	public Board findById(int id) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "";
		Board board = new Board();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			// if 돌려서 rs -> java오브젝트에 집어넣기
			return board;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	
}
