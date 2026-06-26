package model;

public class TypingTestResult {

    private int correctChars;
    private int incorrectChars;
    private double wpm;
    private double accuracy;

    public TypingTestResult(
            int correctChars,
            int incorrectChars,
            double wpm,
            double accuracy) {

        this.correctChars = correctChars;
        this.incorrectChars = incorrectChars;
        this.wpm = wpm;
        this.accuracy = accuracy;
    }

    public int getCorrectChars() {
        return correctChars;
    }

    public int getIncorrectChars() {
        return incorrectChars;
    }

    public double getWpm() {
        return wpm;
    }

    public double getAccuracy() {
        return accuracy;
    }
}