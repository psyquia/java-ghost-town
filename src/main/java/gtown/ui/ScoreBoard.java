package gtown.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import gtown.state.RunningState;

public class ScoreBoard {
	public final static int max_health=100;
	private int score;
	private int health;
	private int totalRewardCount = 0;
	private int currentRewardCount = 0;
	
	public ScoreBoard() {
		score = 0;
		health = max_health;
	}
	
	public int getScore() {
		return score;
	}
	
	public void reset() {
		setHealth(max_health);
		setScore(0);
	}
	
	public void setHealth(int h) {
		health = h;
	}
	
	public int getHealth() {
		return health;
	}
	
	
	public void setScore(int s) {
		score = s;
		checkState();
	}
	
	public void increaseScore(int x) {
		score += x;
		checkState();
	}
	
	public void decreaseScore(int x) {
		score -= x;
		checkState();
	}
	
	public void increaseHealth(int x) {
		health += x;
		if(health > max_health) health = max_health;
		checkState();
	}
	
	public void decreaseHealth(int x) {
		health -= x;
		if(health < 0) 
			health = 0;
		checkState();
	}
	
	public int getRewardCount() {return currentRewardCount;}
	
	public void setTotalRewardCount(int x) {totalRewardCount = x;}
	
	public void increaseCurrentRewardCount() {currentRewardCount++;}
	
	public void resetRewardCount() {currentRewardCount = 0;}
	
	private void checkState() {
		if (health <= 0) {
			RunningState.instance().resetLevel();
		}
	}
	
	public boolean levelClear() {
		return (currentRewardCount == totalRewardCount);
	}
	
	public void render(Graphics2D g) {							// render scoreboard
		Font bahn = new Font("bahnschrift", Font.PLAIN, 24);
		g.setFont(bahn);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 20, 30);
		g.setColor(Color.GRAY);
		g.drawString("Masks collected: " + currentRewardCount + "/" + totalRewardCount, 685, 672);
		
		g.setColor(Color.GRAY);
		g.fillRect(20, 645, 410, 40);
		g.setColor(Color.RED);
		g.fillRect(25, 650, 400, 30);
		g.setColor(Color.GREEN);
		g.fillRect(25, 650, 400 * health/max_health, 30);
	}
}
