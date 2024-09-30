package Model;

public class Statistics {
    private int correctAttempts;
    private int wrongAttempts;

    public Statistics() {
        this.correctAttempts = 0;
        this.wrongAttempts = 0;
    }

    public Statistics(int correctAttempts, int wrongAttempts) {
        this.correctAttempts = correctAttempts;
        this.wrongAttempts = wrongAttempts;
    }

    public int getCorrectAttempts() {
        return correctAttempts;
    }

    public int getWrongAttempts() {
        return wrongAttempts;
    }

    public void update(boolean isCorrect) {
        if (isCorrect) {
            correctAttempts++;
        } else {
            wrongAttempts++;
        }
    }

    public String getStatistics() {
        return "Richtig: " + correctAttempts + ", Falsch: " + wrongAttempts;
    }
}
