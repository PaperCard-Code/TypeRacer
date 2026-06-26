package util;

public class WPMCalculator {

	public static double calculateWPM(int correctChars, int timeSeconds) {

		if (timeSeconds <= 0) {
			return 0;
		}

		double minutes = timeSeconds / 60.0;

		return (correctChars / 5.0) / minutes;
	}
}