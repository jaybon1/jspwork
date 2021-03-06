package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.model.Users;

public class UsersRepository {
	private static final String TAG = "UserRepository : "; // TAG 생성 (오류 발견시 용이)
	private static UsersRepository instance = new UsersRepository();

	private UsersRepository() {
	}

	public static UsersRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Users findByUsername(String username) {
		final String SQL = "SELECT * FROM users WHERE username=?";
		Users user = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			// 물음표 완성하기
			pstmt.setString(1, username);

			// if 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = Users.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.address(rs.getString("address"))
						.userProfile(rs.getString("userProfile"))
						.userRole(rs.getString("userRole"))
						.createDate(rs.getTimestamp("createDate"))
						.build();
			}
		
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findByUsername : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null; // 실패시
	}
	

	public Users findByUsernameAndPassword(String username, String password) {
		final String SQL = "SELECT id, username, email, address, userProfile, userRole, createDate "
				+ "FROM users WHERE username=? AND password=?";
		Users user = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			// 물음표 완성하기
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			// if 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new Users(); // 무조건 null이 아니라는 의미
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setUserRole(rs.getString("userRole"));
				user.setCreateDate(rs.getTimestamp("createDate"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findByUsernameAndPassword : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}

	// 회원가입
	public int save(Users user) { // object 받기(안에 내용 다 받아야 하니까) // insert하고 싶으면 save
		final String SQL = "INSERT INTO users(id, username, password, email, address, userRole, createDate) "
				+ "VALUES(USERS_SEQ.nextval,?,?,?,?,?,sysdate)"; // userProfile은 나중에 update
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUserRole()); // user권한 (종류는 user, admin)

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
	public int update(Users user) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "UPDATE users SET password = ?, email = ?, address = ? WHERE id = ? ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
		
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getAddress());
			pstmt.setInt(4, user.getId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	// 회원정보 수정
	public int update(int id, String userProfile) {
		final String SQL = "UPDATE users SET userProfile = ? WHERE id = ? ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
		
			pstmt.setString(1, userProfile);
			pstmt.setInt(2, id);
			
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
	public List<Users> findAll() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "";
		List<Users> users = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			// while 돌려서 rs -> java오브젝트에 집어넣기
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}

	// 회원정보 한 건 찾기
	public Users findById(int id) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, username, email, address, userProfile, userRole, createDate "
				+ "FROM users WHERE id = ?";
		Users user = new Users();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			
			// 물음표 완성하기
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			

			// if 돌려서 rs -> java오브젝트에 집어넣기
			if(rs.next()) {
				
				user = new Users(); // 무조건 null이 아니라는 의미
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setUserRole(rs.getString("userRole"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
}
