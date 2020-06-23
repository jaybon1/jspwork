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

public class PracticeTableRepository {
	private static final String TAG = "ClassTableRepository : "; // TAG 생성 (오류 발견시 용이)
	private static PracticeTableRepository instance = new PracticeTableRepository();

	private PracticeTableRepository() {
	}

	public static PracticeTableRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<List<PracticeProgressDto>> findPractice(String classDate) {
		final String SQL = "SELECT room, subject1, subject2, prof, class_time FROM practice_table "
				+ "WHERE class_date = ? and status = 'true' ";
		
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
				
				ppdsList.get(rs.getInt(5)-1).get(classTime).setSubject1(rs.getString(2));
				ppdsList.get(rs.getInt(5)-1).get(classTime).setSubject1(rs.getString(3));
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
}
