package com.bitc.practiceProgress.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitc.practiceProgress.db.DBConn;
import com.bitc.practiceProgress.dto.ProgressInputDto;

import oracle.net.aso.c;

public class ProgressRepository {
	private static final String TAG = "UserRepository : "; // TAG 생성 (오류 발견시 용이)
	private static ProgressRepository instance = new ProgressRepository();

	private ProgressRepository() {
	}

	public static ProgressRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public List<ProgressInputDto> findClassNameHomeroomProf() {
		final String SQL = "SELECT room, class_name, homeroom_prof FROM class_table WHERE status = 'true' ";
		List<ProgressInputDto> pids = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);

			rs = pstmt.executeQuery();
			
			pids = new ArrayList<>();
			
			for (int i = 0; i < 12; i++) {
				
				pids.add(new ProgressInputDto("",""));
				
			}
			
			while (rs.next()) {

				if(rs.getInt(1) == 402) {
					pids.get(0).setClassName(rs.getString(2));
					pids.get(0).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 403) {
					pids.get(1).setClassName(rs.getString(2));
					pids.get(1).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 404) {
					pids.get(2).setClassName(rs.getString(2));
					pids.get(2).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 405) {
					pids.get(3).setClassName(rs.getString(2));
					pids.get(3).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 501) {
					pids.get(4).setClassName(rs.getString(2));
					pids.get(4).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 502) {
					pids.get(5).setClassName(rs.getString(2));
					pids.get(5).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 503) {
					pids.get(6).setClassName(rs.getString(2));
					pids.get(6).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 504) {
					pids.get(7).setClassName(rs.getString(2));
					pids.get(7).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 505) {
					pids.get(8).setClassName(rs.getString(2));
					pids.get(8).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 506) {
					pids.get(9).setClassName(rs.getString(2));
					pids.get(9).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 507) {
					pids.get(10).setClassName(rs.getString(2));
					pids.get(10).setHomeroomProf(rs.getString(3));
				} else if (rs.getInt(1) == 508) {
					pids.get(11).setClassName(rs.getString(2));
					pids.get(11).setHomeroomProf(rs.getString(3));
				}
				
			}
		
			return pids;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findIdClassName : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return null; // 실패시
	}
}
