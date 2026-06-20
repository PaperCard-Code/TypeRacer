package model;

public class TypingTest {

    private String targetText;
    private String typedText;
    private int correctChars;
    private int incorrectChars;
    private double wpm;
    private double accuracy;

    public TypingTest() {
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public String getTypedText() {
        return typedText;
    }

    public void setTypedText(String typedText) {
        this.typedText = typedText;
    }

    public int getCorrectChars() {
        return correctChars;
    }

    public void setCorrectChars(int correctChars) {
        this.correctChars = correctChars;
    }

    public int getIncorrectChars() {
        return incorrectChars;
    }

    public void setIncorrectChars(int incorrectChars) {
        this.incorrectChars = incorrectChars;
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
}