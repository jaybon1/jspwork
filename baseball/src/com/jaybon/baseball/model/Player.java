package com.jaybon.baseball.model;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
	
	int id;
	int playerNum;
	String playerName;
	String position;
	String teamName;

}
