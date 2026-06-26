package model;

public class WordCategory {

	private String wordCategory;
	private double rarity;

	public WordCategory() {
	}

	public WordCategory(String wordCategory, double rarity) {

		this.wordCategory = wordCategory;
		this.rarity = rarity;
	}

	public String getWordCategory() {
		return wordCategory;
	}

	public void setWordCategory(String wordCategory) {
		this.wordCategory = wordCategory;
	}

	public double getRarity() {
		return rarity;
	}

	public void setRarity(double rarity) {
		this.rarity = rarity;
	}
}