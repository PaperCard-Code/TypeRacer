package util;

public class AccuracyCalculator {

    public static double calculateAccuracy(
            int correctChars,
            int incorrectChars) {

        int total = correctChars + incorrectChars;

        if(total == 0) {
            return 0;
        }

        return ((double) correctChars / total) * 100;
    }
}