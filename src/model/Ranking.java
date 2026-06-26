package model;

import java.util.Date;

public class Ranking {

	private int id;

	private String playerName;

	private int playerId;

	private double wpm;

	private double accuracy;

	private Date testDate;

	public Ranking() {
	}

	public Ranking(int id, String playerName, int playerId, double wpm, double accuracy, Date testDate) {

		this.id = id;
		this.playerName = playerName;
		this.playerId = playerId;
		this.wpm = wpm;
		this.accuracy = accuracy;
		this.testDate = testDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public double getWpm() {
		return wpm;
	}

	public void setWpm(double wpm) {
		this.wpm = wpm;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
}