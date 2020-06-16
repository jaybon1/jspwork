package com.jaybon.baseball.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jaybon.baseball.db.DBConn;
import com.jaybon.baseball.model.Player;

public class PlayerRepository {
	private static final String TAG = "PlayerListRepository : "; // TAG 생성 (오류 발견시 용이)
	private static PlayerRepository instance = new PlayerRepository();

	private PlayerRepository() {
	}

	public static PlayerRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<Player> findPlayerNumNameFromTeam(String teamName) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT playernum, playername  FROM playerlist WHERE teamname like ? ORDER BY playernum asc";
		
		List<Player> playerList = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, teamName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				playerList = new ArrayList<>();
				
				Player player = Player.builder()
						.playerNum(rs.getInt(1))
						.playerName(rs.getString(2))
						.build();
				
				playerList.add(player);
				
				while (rs.next()) {
					
					Player player1 = Player.builder()
							.playerNum(rs.getInt(1))
							.playerName(rs.getString(2))
							.build();
					
					playerList.add(player1);
					
				}
			}
			return playerList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findPlayerNumNameFromTeam : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	
	public Player findPlayerDesc(String teamName, int playerNum) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT playernum, playername, position  FROM playerlist WHERE teamname like ? and playernum = ?";
		
		Player player = null;
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, teamName);
			pstmt.setInt(2, playerNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				player = Player.builder()
						.playerNum(rs.getInt(1))
						.playerName(rs.getString(2))
						.position(rs.getString(3))
						.build();
			}
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findDesc : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
}
