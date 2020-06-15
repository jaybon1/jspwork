package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.ReplyResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.Users;

public class ReplyRepository {
	private static final String TAG = "ReplyRepository : "; // TAG 생성 (오류 발견시 용이)
	private static ReplyRepository instance = new ReplyRepository();

	private ReplyRepository() {
	}

	public static ReplyRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 글쓰기
	public int save(Reply reply) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "INSERT INTO reply(id, userid, boardid, content, createDate) "
				+ "VALUES(REPLY_SEQ.nextval,?,?,?,sysdate)";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, reply.getUserId());
			pstmt.setInt(2, reply.getBoardId());
			pstmt.setString(3, reply.getContent());
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

	
	// 댓글 삭제
	public int deleteById(int replyId) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "DELETE FROM reply WHERE id = ?";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, replyId);
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

	
	public List<ReplyResponseDto> findAll(int boardId) {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT r.id, r.userid, r.boardId, r.content, r.createDate, u.username, u.userProfile ");
		sb.append("FROM reply r INNER JOIN users u ");
		sb.append("ON r.userid = u.id ");
		sb.append("WHERE boardId = ? ");
		sb.append("ORDER BY r.id DESC ");

		final String SQL = sb.toString();
		List<ReplyResponseDto> replyDtos = new ArrayList<>();

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				Reply reply = Reply.builder()
						.id(rs.getInt(1))
						.userId(rs.getInt(2))
						.boardId(rs.getInt(3))
						.content(rs.getString(4))
						.createDate(rs.getTimestamp(5))
						.build();

				ReplyResponseDto replyDto = ReplyResponseDto.builder().reply(reply).username(rs.getString(6))
						.userProfile(rs.getString(7)).build();

				replyDtos.add(replyDto);

			}

			return replyDtos;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll(boardId) : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	

	// 회원정보 다 찾기
	public List<Reply> findAll() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "";
		List<Reply> replys = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			// while 돌려서 rs -> java오브젝트에 집어넣기
			return replys;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}

	// 회원정보 한 건 찾기
	public Reply findById(int id) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "";
		Reply reply = new Reply();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			// if 돌려서 rs -> java오브젝트에 집어넣기
			return reply;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}

}
