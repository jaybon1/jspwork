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
import com.bitc.practiceProgress.model.PracticeTable;

public class PracticeTableRepository {
	private static final String TAG = "PracticeTableRepository : "; // TAG 생성 (오류 발견시 용이)
	private static PracticeTableRepository instance = new PracticeTableRepository();

	private PracticeTableRepository() {
	}

	public static PracticeTableRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int delete(int classId) {
		final String SQL = "DELETE FROM practice_table WHERE class_id = ?";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, classId);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "delete : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	
	public int save(PracticeTable practiceTable,int classId) {
		final String SQL = "INSERT INTO practice_table(class_name, class_date, day_week, class_time, start_time, end_time, subject1, subject2, prof, room, class_id) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, practiceTable.getClassName());
			pstmt.setString(2, practiceTable.getClassDate());
			pstmt.setString(3, practiceTable.getDayWeek());
			pstmt.setInt(4, practiceTable.getClassTime());
			pstmt.setString(5, practiceTable.getStartTime());
			pstmt.setString(6, practiceTable.getEndTime());
			pstmt.setString(7, practiceTable.getSubject1());
			pstmt.setString(8, practiceTable.getSubject2());
			pstmt.setString(9, practiceTable.getProf());
			pstmt.setInt(10, practiceTable.getRoom());
			pstmt.setInt(11, classId);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1; // 실패시
	}
	
	public int saveList(List<PracticeTable> practiceTables,int classId) {
		
		final String SQL = "INSERT INTO practice_table(class_name, class_date, day_week, class_time, start_time, end_time, subject1, subject2, prof, room, class_id) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int finalResult = 1;
		
		try {
			
			for (PracticeTable practiceTable : practiceTables) {
				
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, practiceTable.getClassName());
				pstmt.setString(2, practiceTable.getClassDate());
				pstmt.setString(3, practiceTable.getDayWeek());
				pstmt.setInt(4, practiceTable.getClassTime());
				pstmt.setString(5, practiceTable.getStartTime());
				pstmt.setString(6, practiceTable.getEndTime());
				pstmt.setString(7, practiceTable.getSubject1());
				pstmt.setString(8, practiceTable.getSubject2());
				pstmt.setString(9, practiceTable.getProf());
				pstmt.setInt(10, practiceTable.getRoom());
				pstmt.setInt(11, classId);
				
				int result = pstmt.executeUpdate();
				
				if(result != 1) {
					finalResult = 0;
					break;
				}	
			}
			if(finalResult == 1) {
				conn.commit();
				System.out.println("커밋");
			} else {
				conn.rollback();
				System.out.println("롤백");
			}
			
			return finalResult;
			
		} catch (Exception e) {
			System.out.println(TAG + "saveList : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return -1;
	}
	
	
	public List<List<PracticeProgressDto>> findPractice(String classDate, List<Integer> idList) {
		
		StringBuilder sb = new StringBuilder();
		if(idList.size()>0) {
			sb.append("and class_id in (");
			
			for (int i = 0; i < idList.size(); i++) {
				
				sb.append(idList.get(i));
				
				if(i<idList.size()-1) {
					sb.append(", ");	
				}
			}
		}
		sb.append(") ");
		
		System.out.println(sb.toString());
		
		final String SQL = "SELECT room, subject1, subject2, prof, class_time FROM practice_table "
				+ "WHERE class_date = ? "
				+ sb.toString();
		
		List<List<PracticeProgressDto>> ppdsList = null;
		
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, classDate);
			
			rs = pstmt.executeQuery();
			
			ppdsList = new ArrayList<>();
			
			for (int i = 0; i < 8; i++) {
				
				List<PracticeProgressDto> ppds = new ArrayList<>();
				
				for (int j = 0; j < 12; j++) {
					
					ppds.add(new PracticeProgressDto("","",""));
					
				}
				
				
				ppdsList.add(ppds);
				
			}

			
			while (rs.next()) {
				
				if(rs.getInt(5) < 1) {
					break;
				}
				
				int classTime = -1;
				
				if(rs.getInt(1) == 402) {
					classTime = 0;
				} else if (rs.getInt(1) == 403) {
					classTime = 1;
				} else if (rs.getInt(1) == 404) {
					classTime = 2;
				} else if (rs.getInt(1) == 405) {
					classTime = 3;
				} else if (rs.getInt(1) == 501) {
					classTime = 4;
				} else if (rs.getInt(1) == 502) {
					classTime = 5;
				} else if (rs.getInt(1) == 503) {
					classTime = 6;
				} else if (rs.getInt(1) == 504) {
					classTime = 7;
				} else if (rs.getInt(1) == 505) {
					classTime = 8;
				} else if (rs.getInt(1) == 506) {
					classTime = 9;
				} else if (rs.getInt(1) == 507) {
					classTime = 10;
				} else if (rs.getInt(1) == 508) {
					classTime = 11;
				}
				
				if(classTime == -1) {
					break;
				}
				
				ppdsList.get(rs.getInt(5)-1).get(classTime).setSubject1(rs.getString(2));
				ppdsList.get(rs.getInt(5)-1).get(classTime).setSubject2(rs.getString(3));
				ppdsList.get(rs.getInt(5)-1).get(classTime).setProf(rs.getString(4));
				
			}
					
		
			return ppdsList;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findPractice : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null; // 실패시
	}
	
	public List<PracticeProgressDto> findPracticeNow(int classTime, String classDate, List<Integer> idList) {
		
		StringBuilder sb = new StringBuilder();
		if(idList.size()>0) {
			sb.append("and class_id in (");
			
			for (int i = 0; i < idList.size(); i++) {
				
				sb.append(idList.get(i));
				
				if(i<idList.size()-1) {
					sb.append(", ");	
				}
			}
		}
		sb.append(") ");
		
		System.out.println(sb.toString());
		
		
		final String SQL = "SELECT room, subject1, subject2, prof FROM practice_table "
				+ "WHERE class_date = ? and  class_time = ? "
				+ sb.toString();
		
		List<PracticeProgressDto> ppds = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, classDate);
			pstmt.setInt(2, classTime);
			
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
