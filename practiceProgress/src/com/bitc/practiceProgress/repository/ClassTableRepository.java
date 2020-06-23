package com.bitc.practiceProgress.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitc.practiceProgress.db.DBConn;
import com.bitc.practiceProgress.dto.ProgressInputDto;
import com.bitc.practiceProgress.dto.PracticeProgressDto;
import com.bitc.practiceProgress.model.ClassTable;

public class ClassTableRepository {
	private static final String TAG = "ClassTableRepository : "; // TAG 생성 (오류 발견시 용이)
	private static ClassTableRepository instance = new ClassTableRepository();

	private ClassTableRepository() {
	}

	public static ClassTableRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int delete(int id) {
		final String SQL = "DELETE FROM class_table WHERE id = ?";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	
	public int Update(ClassTable classTable) {
		final String SQL = "UPDATE class_table "
				+ "SET class_name = ?, class_part = ?, class_open = ?, class_close = ?, homeroom_prof = ?, status = ? "
				+ "WHERE id = ?";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, classTable.getClassName());
			pstmt.setString(2, classTable.getClassPart());
			pstmt.setString(3, classTable.getClassOpen());
			pstmt.setString(4, classTable.getClassClose());
			pstmt.setString(5, classTable.getHomeroomProf());
			pstmt.setString(6, classTable.getStatus());
			pstmt.setInt(7, classTable.getId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	
	public int save(ClassTable classTable) {
		final String SQL = "INSERT INTO class_table(room, class_name, class_part, class_open, class_close, homeroom_prof, status) "
				+ "VALUES(?, ?, ?, ?, ?, ?, 'false') ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, classTable.getRoom());
			pstmt.setString(2, classTable.getClassName());
			pstmt.setString(3, classTable.getClassPart());
			pstmt.setString(4, classTable.getClassOpen());
			pstmt.setString(5, classTable.getClassClose());
			pstmt.setString(6, classTable.getHomeroomProf());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	
	public ClassTable findByRoom(int room) {
		final String SQL = "SELECT id, room, class_name, class_part, class_open, class_close, homeroom_prof, status "
				+ "FROM class_table WHERE room = ? and status = 'true' ";
		
		ClassTable classTable = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, room);

			rs = pstmt.executeQuery();
			
			classTable = new ClassTable();
			
			if (rs.next()) {
				classTable = ClassTable.builder()
						.id(rs.getInt(1))
						.room(rs.getInt(2))
						.className(rs.getString(3))
						.classPart(rs.getString(4))
						.classOpen(rs.getString(5))
						.classClose(rs.getString(6))
						.homeroomProf(rs.getString(7))
						.status(rs.getString(8))
						.build();
			}
		
			return classTable;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findByRoom : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	// 호실, 훈련명, 담임선생님을 불러옴
	public List<ProgressInputDto> findClassNameHomeroomProf() {
		final String SQL = "SELECT room, class_name, homeroom_prof FROM class_table WHERE status = 'true' ";
		List<ProgressInputDto> pids = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			pids = new ArrayList<>();
			
			for (int i = 0; i < 12; i++) {
				
				pids.add(new ProgressInputDto(0,"",""));
				
			}
			
			while (rs.next()) {

				if(rs.getInt(1) == 402) {
					pids.get(0).setRoom(rs.getInt(1));
					pids.get(0).setClassName(rs.getString(2));
					pids.get(0).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 403) {
					pids.get(1).setRoom(rs.getInt(1));
					pids.get(1).setClassName(rs.getString(2));
					pids.get(1).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 404) {
					pids.get(2).setRoom(rs.getInt(1));
					pids.get(2).setClassName(rs.getString(2));
					pids.get(2).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 405) {
					pids.get(3).setRoom(rs.getInt(1));
					pids.get(3).setClassName(rs.getString(2));
					pids.get(3).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 501) {
					pids.get(4).setRoom(rs.getInt(1));
					pids.get(4).setClassName(rs.getString(2));
					pids.get(4).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 502) {
					pids.get(5).setRoom(rs.getInt(1));
					pids.get(5).setClassName(rs.getString(2));
					pids.get(5).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 503) {
					pids.get(6).setRoom(rs.getInt(1));
					pids.get(6).setClassName(rs.getString(2));
					pids.get(6).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 504) {
					pids.get(7).setRoom(rs.getInt(1));
					pids.get(7).setClassName(rs.getString(2));
					pids.get(7).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 505) {
					pids.get(8).setRoom(rs.getInt(1));
					pids.get(8).setClassName(rs.getString(2));
					pids.get(8).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 506) {
					pids.get(9).setRoom(rs.getInt(1));
					pids.get(9).setClassName(rs.getString(2));
					pids.get(9).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 507) {
					pids.get(10).setRoom(rs.getInt(1));
					pids.get(10).setClassName(rs.getString(2));
					pids.get(10).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 508) {
					pids.get(11).setRoom(rs.getInt(1));
					pids.get(11).setClassName(rs.getString(2));
					pids.get(11).setHomeroomProf(rs.getString(3));
				}
				
			}
		
			return pids;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findClassNameHomeroomProf : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null; // 실패시
	}
	
	
	public List<PracticeProgressDto> findPracticeNow(int classTime, String classDate) {
		final String SQL = "SELECT room, subject1, subject2, prof FROM practice_table "
				+ "WHERE class_time = ? and class_date = ? and status = 'true' ";
		
		List<PracticeProgressDto> ppds = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, classTime);
			pstmt.setString(2, classDate);
			
			rs = pstmt.executeQuery();
			
			ppds = new ArrayList<>();
			
			for (int i = 0; i < 12; i++) {
				
				ppds.add(new PracticeProgressDto("","",""));
				
			}
			
			while (rs.next()) {

				if(rs.getInt(1) == 402) {
					ppds.get(0).setSubject1(rs.getString(2));
					ppds.get(0).setSubject2(rs.getString(3));
					ppds.get(0).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 403) {
					ppds.get(1).setSubject1(rs.getString(2));
					ppds.get(1).setSubject2(rs.getString(3));
					ppds.get(1).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 404) {
					ppds.get(2).setSubject1(rs.getString(2));
					ppds.get(2).setSubject2(rs.getString(3));
					ppds.get(2).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 405) {
					ppds.get(3).setSubject1(rs.getString(2));
					ppds.get(3).setSubject2(rs.getString(3));
					ppds.get(3).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 501) {
					ppds.get(4).setSubject1(rs.getString(2));
					ppds.get(4).setSubject2(rs.getString(3));
					ppds.get(4).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 502) {
					ppds.get(5).setSubject1(rs.getString(2));
					ppds.get(5).setSubject2(rs.getString(3));
					ppds.get(5).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 503) {
					ppds.get(6).setSubject1(rs.getString(2));
					ppds.get(6).setSubject2(rs.getString(3));
					ppds.get(6).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 504) {
					ppds.get(7).setSubject1(rs.getString(2));
					ppds.get(7).setSubject2(rs.getString(3));
					ppds.get(7).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 505) {
					ppds.get(8).setSubject1(rs.getString(2));
					ppds.get(8).setSubject2(rs.getString(3));
					ppds.get(8).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 506) {
					ppds.get(9).setSubject1(rs.getString(2));
					ppds.get(9).setSubject2(rs.getString(3));
					ppds.get(9).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 507) {
					ppds.get(10).setSubject1(rs.getString(2));
					ppds.get(10).setSubject2(rs.getString(3));
					ppds.get(10).setProf(rs.getString(4));
				} else if (rs.getInt(1) == 508) {
					ppds.get(11).setSubject1(rs.getString(2));
					ppds.get(11).setSubject2(rs.getString(3));
					ppds.get(11).setProf(rs.getString(4));
				}
				
			}
		
			return ppds;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findPracticeNow : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null; // 실패시
	}
}
