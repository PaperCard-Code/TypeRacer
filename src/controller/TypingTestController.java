package controller;

import model.Player;
import model.Ranking;
import model.TypingTestResult;

import util.TextGenerator;
import util.WPMCalculator;
import util.AccuracyCalculator;

import java.util.Date;

public class TypingTestController {

	private TextGenerator textGenerator;

	private RankingController rankingController;

	public TypingTestController() {

		textGenerator = new TextGenerator();

		rankingController = new RankingController();
	}

	public String generateTestText() {

		return textGenerator.generateText(100);
	}

	public int countCorrectChars(String target, String typed) {

		int correct = 0;

		int length = Math.min(target.length(), typed.length());

		for (int i = 0; i < length; i++) {

			if (target.charAt(i) == typed.charAt(i)) {

				correct++;
			}
		}

		return correct;
	}

	public int countIncorrectChars(String target, String typed) {

		int incorrect = 0;

		int length = Math.min(target.length(), typed.length());

		for (int i = 0; i < length; i++) {

			if (target.charAt(i) != typed.charAt(i)) {

				incorrect++;
			}
		}

		incorrect += Math.abs(target.length() - typed.length());

		return incorrect;
	}

	public Ranking finishTest(Player player, String targetText, String typedText, int timeSeconds) {

		int correctChars = countCorrectChars(targetText, typedText);

		int incorrectChars = countIncorrectChars(targetText, typedText);

		double accuracy = AccuracyCalculator.calculateAccuracy(correctChars, incorrectChars);

		double wpm = WPMCalculator.calculateWPM(correctChars, timeSeconds);

		Ranking ranking = new Ranking();

		ranking.setPlayerId(player.getId());

		ranking.setPlayerName(player.getName());

		ranking.setAccuracy(accuracy);

		ranking.setWpm(wpm);

		ranking.setTestDate(new Date());

		rankingController.saveRanking(ranking);

		return ranking;
	}

	public TypingTestResult calculateResults(String targetText, String typedText, int timeSeconds) {

		int correctChars = countCorrectChars(targetText, typedText);

		int incorrectChars = countIncorrectChars(targetText, typedText);

		double accuracy = AccuracyCalculator.calculateAccuracy(correctChars, incorrectChars);

		double wpm = WPMCalculator.calculateWPM(correctChars, timeSeconds);

		return new TypingTestResult(correctChars, incorrectChars, wpm, accuracy);
	}
}