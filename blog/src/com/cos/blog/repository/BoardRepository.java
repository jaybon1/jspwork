package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.dto.DetailResponseDto;
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
		final String SQL = "UPDATE board SET title = ?, content = ? WHERE id = ?";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "Update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	// 리드카운트 수정
	public int readCountUp(int id) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "UPDATE board SET readCount = readCount+1 WHERE id = ? ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}

	// 회원정보 삭제
	public int deleteById(int id) { // object 받기(안에 내용 다 받아야 하니까)
		
		final String SQL = "DELETE FROM board WHERE id = ?";
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "Delete : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return -1; // 실패시
		
	}

	// 게시글 다 찾기
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
	
	// 검색한 게시글 다 찾기
	public List<Board> findAll(int page, String keyword) { // object 받기(안에 내용 다 받아야 하니까)
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT /*+ INDEX_DESC(BOARD SYS_C008628)*/id, userId, title, content, readCount, createDate ");
		sb.append("FROM board ");
		sb.append("WHERE title like ? OR content like ?");
		sb.append("OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY");
		
		System.out.println(sb.toString());
		
		final String SQL = sb.toString();
		List<Board> boards = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, page * 3);
			
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
			System.out.println(TAG + "findAll(page, keyword) : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null; // 실패시
	}
	
	// 게시글 숫자 검색
	public int count() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT count(id) FROM board";
		List<Board> boards = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			int count = -1;
			
			if (rs.next()) {
				
				count = rs.getInt(1);
				
			}
			
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "count : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return -1; // 실패시
	}
	
	// 게시글 숫자 검색
	public int count(String keyword) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT count(id) FROM board WHERE title like ? OR content like ? ";
		List<Board> boards = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			
			rs = pstmt.executeQuery();
			
			int count = -1;
			
			if (rs.next()) {
				
				count = rs.getInt(1);
				
			}
			
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "count(keyword) : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return -1; // 실패시
	}
	
	// 회원정보 3건찾기
	public List<Board> findThree(int page) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT /*+ INDEX_DESC(BOARD SYS_C008628)*/id, userId, title, content, readCount, createDate ");
		sb.append("FROM board ");
		sb.append("OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY");
		
		final String SQL = sb.toString();
		List<Board> boards = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, page*3);
			
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
	public BoardResponseDto findById(int id) { // object 받기(안에 내용 다 받아야 하니까)
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT b.id, b.userid, b.title, b.content, b.readcount, b.createdate, u.username ");
		sb.append("FROM board b INNER JOIN users u ");
		sb.append("ON b.userId = u.id ");
		sb.append("AND b.id = ?");
		
		final String SQL = sb.toString();
		BoardResponseDto boardDto = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			// if 돌려서 rs -> java오브젝트에 집어넣기
			if(rs.next()) {
				
				boardDto = new BoardResponseDto();	
				Board board = Board.builder()
						.id(rs.getInt(1))
						.userId(rs.getInt(2))
						.title(rs.getString(3))
						.content(rs.getString(4))
						.readCount(rs.getInt(5))
						.createDate(rs.getTimestamp(6))
						.build();
				boardDto.setBoard(board);
				boardDto.setUsername(rs.getString(7));
				
			}
			
			return boardDto;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
			
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null; // 실패시
		
	}
}


