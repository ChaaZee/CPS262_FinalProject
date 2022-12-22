package game_of_pig;

import java.util.Date;

public class GameRecord {
	public boolean win;
	public String player;
	public int score;
	public Date date;
	
	public GameRecord(boolean win, String player, int score) {
		this.win = win;
		this.player = player;
		this.score = score;
		date = new Date();
	}
	
	public String getPlayer() {
		return player;
	}
	
	public int getScore() {
		return score;
	}
	
	@SuppressWarnings("deprecation")
	public String getDate() {
		return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
	}
	
	public String getWin() {
		if(win)
			return "Win";
		else
			return "Loss";
	}
}
