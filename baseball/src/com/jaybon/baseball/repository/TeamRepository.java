package com.jaybon.baseball.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jaybon.baseball.db.DBConn;
import com.jaybon.baseball.model.Team;

public class TeamRepository {
	private static final String TAG = "TeamListRepository : "; // TAG 생성 (오류 발견시 용이)
	private static TeamRepository instance = new TeamRepository();

	private TeamRepository() {
	}

	public static TeamRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public List<Team> findAll() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, teamname FROM teamlist ORDER BY id asc";
		
		List<Team> teamList = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				teamList = new ArrayList<>();
				
				Team team = Team.builder()
						.id(rs.getInt(1))
						.teamName(rs.getString(2))
						.build();
				
				teamList.add(team);
				
				while (rs.next()) {
					
					Team team1 = Team.builder()
							.id(rs.getInt(1))
							.teamName(rs.getString(2))
							.build();
					
					teamList.add(team1);
					
				}
			}
			return teamList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
}
